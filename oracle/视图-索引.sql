-- 视图，索引

-- 1， 视图是一种数据库对象，是从一个或多个表或视图中导出的虚表，需要知道的是，视图所对应的的数据并不存储在视图中
--而是存储在所引用的数据表中。

-- 使用视图的优点
--1，视图可以简化用户处理数据的方式
--2，不必要的数据，或敏感的数据不必要出现在视图表中
--3，视图可以提供一个简单而有效的安全机制，可以定制不同用户对数据的访问权限


-- 1，创建视图
create or replace view view_owners as
select * from t_owners where ownertypeid = 1;

select * from view_owners;

-- 2 删除视图
drop view view_owners;

-- 3 修改视图 会修改原表的数据
update view_owners set name='王刚' where id = 2;

--4 带检查约束的视图
create or replace view view_address as
select * from t_address where areaid = 2
with check option;

select * from view_address;

update view_address set areaid = 1 where id = 4;

select * from t_address;

-- 5,只读视图的创建与使用
create or replace view view_owners2 as 
select * from t_owners where ownertypeid = 1
with read only;


-- 6 复杂视图的创建 多表关联
create or replace view v_owners as
select o.id as 业主编号, o.name as 业主名称,ot.name as 业主类型
from t_owners o, t_ownertype ot
where o.ownertypeid = ot.id;

select * from v_owners;

update v_owners set 业主名称  = '范小宁' where 业主编号 = 1;

update v_owners set 业主类型 = '普通居名' where 业主编号 = 1;

-- 无法修改与非键值保存表对应的列

-- 简单说就是这个键值列是原表的主键列，也是视图表中的主键列，这个主键列是连接其他表的列，无法修改

select * from t_owners;

-- 分组聚合统计查询
create or replace view v_accountsum as 
select year ,month,sum(money) as moneysum 
from t_account 
group by year,month
order by year, month;

select * from v_accountsum;

-- 创建一个自动刷新的物化视图
create materialized view mv_address
refresh
on commit
as
select ad.id, ad.name adname, ar.name ar_name 
from t_address ad,t_area ar
where ad.areaid = ar.id;

select * from mv_address;  -- 这是一个物化视图，相当于一个新表，当原表的数据发生改变，这个表的数据也会自动刷新，有自己的数据存储空间，


-- 序列
-- 创建序列  
-- 有最大值的非循环序列
create sequence seq_test1
increment by 10
start with 10
maxvalue 99999
minvalue 1
nocycle;

-- 获取序列
select seq_test3.nextval from dual;

-- 修改序列
alter sequence seq_test3 increment by 1;

create sequence seq_test2 increment by 1 start with 1 maxvalue 99999 minvalue 1 nocycle;

create sequence seq_test3 increment by 1 start with 1 maxvalue 99999 minvalue 1 nocycle; 

-- 删除序列
drop sequence seq_test2;

-- 同义词

-- 为表创建（私有）同义词
create synonym owners for t_owners;

select * from owners;

-- 为表创建公有同义词  其他用户登录也可以使用公有同义词
create public synonym owners2 for t_owners;

select * from owners2;


--- 索引
-- 索引是加速数据存储的数据对象。合理的使用索引可以大大降低i/o的次数，从而提高数据的访问性能
-- 普通索引

create index index_owners_name on t_owners(name);

create table t_indextest
(
id number,
name varchar2(30)
);

--begin 
  --for i in 1..1000000
    --loop
      --insert into t_indextest values(i,'AA'|| i);
   -- end loop
    --commit;
--end;

select count(0) from t_indextest;

-- 根据name创建列索引
create index index_testindex on t_indextest(name);

select * from t_indextest where id = 765432;
select * from t_indextest where name = 'AA765432';


-- 唯一索引

-- 如果我们需要在某个表的某个列创建索引，而这个列的值不会重复的，这时我们可以创建一个唯一索引
create unique index index_owners_watermeter on t_owners(watermeter);


-- 符合索引
-- 我们经常需对某几列进行查询，比如我们需要根据学历和性别对学员进行搜索
create index owners_index_ah on t_owners(addressid,housenumber);

select * from t_owners t where t.addressid = 2 and t.housenumber like '%-4';

-- 反向键索引 是指在创建的索引是一个连续增长的值，如果创建普通索引，会形成一个歪脖子树，降低性能查询
-- 反向键索引会使索引趋于均匀分布，
create index index_ on t_owners(id) reverse;

-- 位图索引（略）



















