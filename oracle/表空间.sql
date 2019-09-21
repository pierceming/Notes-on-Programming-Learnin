create tablespace softwater_back   -- softwater 是表空间名字，
datafile '/data/tablespace/softwater_back.dbf'  -- softwater下的数据文件，都存在这里
size 100m   -- 默认表空间大小
autoextend on -- 开启自动增长模式
next 50m ;  -- 如果满100M,则自动增长


-- 创建用户
create user wateruser2  -- 登录账户
identified by 123456  -- 密码
default tablespace softwater_back;

-- 给用户赋权
grant dba to wateruser2;


-- 创建表结构，认识oracle的数据结构  drop table t_owners;
create table t_owners
(
id number primary key,
name varchar2(30),
addressid number(5),
housenumber varchar2(30),
adddate date,
ownertypeid number(5)
);

-- 数据类型
-- 1， char 类型，固定长度字符类型，2000字节，一般很少用
-- 2， varchar2 类型， 可变长度字符类型， 4000字节。
-- 3， long 类型， 大文本类型， 可存储2G；
-- 4，number(5) 表示可以最大存储5位数的数字即最大到99999
-- 5，number(3,2) 表示可以存小数，最大的值为999.99
-- 6， date 日期类型 
-- 7, timestamp 可以精确到秒的小数点后9位，好像没用到过
-- 二进制型（大数据类型）
-- 8， clob, 存储字符，最大可存储4g
-- 9, blob, 存储图像，声音，视频等二进制文件，可以存储4g

-- 表结构操作
-- 1）修改表字段
-- 新增表字段
alter table t_owners add (remark varchar2(20),outdate date,ismale number(1) default 0);

-- 修改字段类型(注意，将varchar2类型转化为二进制大文本类型时，不能简单的像下面的方式进行修改)
alter table t_owners modify (ismale number(1) default 0);

-- 修改表字段名字
alter table t_owners rename column isfemale to ismale;

select * from t_owners;
-- 删除表字段
alter table t_owners drop (outdate,ismale);
alter table t_owners drop column remark;

-- 删除表
drop table t_owners;
commit;

-- 清空表数据，恢复原始表结构
truncate table test;

----------------------------------------------------

-- 1， 新增表数据

insert into t_owners values(1,'张三丰',1,'2-2',sysdate,1,'劳模户', sysdate, 1);
insert into t_owners values(2,'赵大侃',2,'2-3',sysdate,3,'优秀会员', sysdate, 0);
commit;

-- 2, 修改数据
update t_owners set adddate = adddate - 3 where id = 2;
commit; 

-- 3, 删除数据
delete from t_owners where id = '2'; -- 注意commit不要写在后面，要单独起一行
commit;

--------------------数据导入导出------------------------

--1 整库数据导出
exp pierce/123456 file = wateruserdata.dmp full = y; -- 注意，这些不是sql语句，而是跟oracle相关的linux命令

--2 整库导入
imp pierce/123456 full = y file = wateruserdata.dmp; -- 注意，如果不指定导入文件则默认导入备份文件expdat.dmp

--3 按用户导出
exp pierce/123456 owner = softwater file = softwater.dmp;

--4, 按用户导入
imp pierce/123456 file = softwater.dmp fromuser = wateruser;

--5, 按表导出
exp wateruser/123456 file=d.dmp tables = t_owners,t_user;

--6, 按表导入
imp wateruser/123456 file=delete.dmp tables =  t_owners,t_user;







