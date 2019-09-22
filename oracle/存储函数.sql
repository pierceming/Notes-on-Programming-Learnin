-- 存储函数
create function fn_getaddress(v_id number)
return varchar2
is 
v_name varchar2(30);
begin
  select name into v_name from t_address where id = v_id;
  return v_name;
end;

select fn_getaddress(3) from dual;


select id 编号, name 业主名称, fn_getaddress(addressid) 地址 from t_owners;


-- 存储过程
-- 存储过程是被命名的pl/sql模块，存储于是数据库中。
-- 存储函数必须要有返回值，存储过程没有返回值，可以通过传出参数返回多个值
-- 存储函数可以做直接在select字句中使用，而存储过程不能，存储过程一般封装一段事务逻辑代码

-- 1, 创建不带传出参数的存储过程
create sequence seq_owners start with 11;

-- 增加业主信息的存储过程
create or replace procedure pro_owners_add
(
v_name varchar2,
v_addressid number,
v_housenumber varchar2,
v_watermeter varchar2,
v_type number
)
is
begin
  insert into t_owners values(
  seq_owners.nextval,v_name,v_addressid,v_housenumber,v_watermeter,sysdate,v_type
  );
  commit;
end;

--调用存储过程
call pro_owners_add('赵薇',1,'999-3','132-7',1);

select * from t_owners;

-- 创建带传出参数的存储过程
create or replace procedure pro_owners_add2
(
v_name varchar2,
v_addressid number,
v_housenumber varchar2,
v_watermeter varchar2,
v_type number,
v_id out number
)
is
begin
  select seq_owners.nextval into v_id from dual;
    insert into t_owners values(
  v_id,v_name,v_addressid,v_housenumber,v_watermeter,sysdate,v_type
  );
  commit;
end;

-- 调用存储过程
declare 
v_id number; --定义传出参数的变量
begin
  pro_owners_add2('王小旺',1,'922-3','133-7',1,v_id);
  DBMS_OUTPUT.put_line('新增用户成功，id:' || v_id); 
  end;
  
  
-- 触发器
-- 数据库触发器是一个与表相关联的，存储的pl/sql程序。每当一个特定的数据操作在指定的表上发出时，oracle自动的执行触发器中定义的语句序列

-- 触发器可用于
--1 数据确认
--2，实施复杂的安全性检查
--3，做审计，跟踪表上所做的数据操作等
--4，数据的备份和同步等

-- 前置触发器， 后置触发器
create or replace trigger tri_account_updatenum1
before 
update of num1
on t_account
for each row
  declare
  :new.usenum := :new.num1 - :new.num0;
 end;

select * from t_account;

-- 后置触发器

-- 创建业主名称修改日志表：用于记录业主更改前后的名称
create table t_owners_log
(
updatetime date,
ownerid number,
oldname varchar2(30),
newname varchar2(30)
);

-- 创建后置触发器，自动记录业主更改前后的日志
create or replace trigger tri_owners_log
after update of name on t_owners
for each row
  declare
  begin 
    insert into t_owners_log
    values(sysdate,:old.id, :old.name,:new.name);
  end;
  
  update t_owners set name = '杨晓华' where id = 3;
  
  select * from t_owners_log;
  
  
  -- 存储函数综合案例
  
create function fn_calmoney(v_ownertypeid number,v_usenum2 number)
return number
is
v_pricetable t_pricetable%rowtype;
v_money number(10,2);
cursor cur_pricetable(v_type number) is select * from t_pricetable where ownertypeid = v_type order by minnum; --游标
begin
  v_money := 0;
  for v_pricetable in cur_pricetable(v_ownertypeid)
    loop
      if v_usenum2 <= v_pricetable.maxnum or v_pricetable.maxnum is null then
        v_money := v_money + v_pricetable.price*(v_usenum2 - v_pricetable.minnum);
        exit;
      else
        v_money := v_money + v_pricetable.price*(v_pricetable.maxnum - v_pricetable.minnum);
      end if;
    end loop;
   return v_money;
end;


select fn_calmoney(1,12) from dual;

-- 触发器综合案例 
-- 当用户自动输入本月日期后，就会自动计算本月的阶梯水费
create or replace trigger tri_account_updatenums
before
update of num1
on t_account
for each row
  declare
  v_usenum2, number(10,2);
  begin
    --赋值
    :new.usenum := :new.num1 - :new.num0;
    v_usenum2 := round(:new.usenum/1000,3); --计算吨数
    :new.money := fn_calmoney(:new.ownertype,v_usenum2 ); --对应金额列赋值
end;


-- 存储过程综合案例
create or replace procedure pro_owner_adds
(
v_name varchar2,
v_addressid number,
v_housenumber varchar2,
v_watermeter varchar2,
v_type number,
v_ownersuuid out number
)
is
v_area number;
v_year char(4);
v_month char(2);
begin
  select seq_owners.nextval into v_ownersuuid from dual;
  select areaid into v_area from t_address where id = v_addressid;
  v_year := to_char(sysdate,'yyyy');
  v_month := to_char(sysdate,'mm');
  -- 增加业主信息
  insert into t_owners
  values(v_ownersuuid,v_name,v_addressid,v_housenumber,v_watermeter,sysdate,v_type);
  --增加财务表信息
  insert into t_account
  (id,owneruuid,ownertype,areaid,year,month,num0)
  values
  (seq_account.nextval,v_ownersuuid,v_type,v_area,v_year,v_month,0);
  commit;
exception
  when NO_DATA_FOUND THEN
    v_ownersuuid := -1;
    rollback;
end;





  
