-- oracle查询语法
-- 1，精确查询

select * from t_owners t where t.watermeter = '30408';

-- 2 模糊查询

select * from t_owners t where t.name like '%刘%';

-- 3 and 多条件运算符

select * from t_owners t where t.name like '%刘%' and t.housenumber like '%-2';

-- 4, or 运算符

select * from t_owners t where t.name like '%刘%' or t.housenumber like '%-2';

-- 5, ()括号具有优先级
select * from t_owners t where (t.name like '%刘%' or t.housenumber like '%-2') and t.addressid = 4;

-- 6, >= <= 

select * from t_account t where t.usenum >= 10000 and t.usenum <= 20000 ;

-- 7, 使用between .. and ..


select * from t_account t where t.usenum between 10000 and  20000 ;

-- 8, 空值查询 null
select * from t_pricetable t where t.maxnum is null;

select * from t_pricetable t where t.maxnum is not null;
-- 9, 去重 distinct关键字
select distinct addressid from t_owners;

-- 10, 排序查询
select * from t_account t order by usenum desc;  -- desc 降序查询， 默认是升序查询

-- 11，基于伪列查询 表中的每一行数据文件第一个物理地址，这个伪列就是数据行信息的物理地址
select rowid, t.* from t_area t;

select * from t_area t where rowid = 'AAARk6AAGAAAAF+AAC';

-- 12 rownum 查询 rownum是数据表中每一行数据的行记录
select rownum, t.* from t_owners t where rownum < 5;

-- 13, 聚合函数 oracle的聚合统计是通过分组函数来实现的，与mysql一致
--1， 求和

select sum(usenum) from t_account where year = '2012';

--2, 求平均值
select avg(usenum) from t_account where year = '2012';

--3, 最大值
select max(usenum) from t_account where year = '2012';

--4. 最小值
select min(usenum) from t_account where year = '2012';

--5, 统计个数
select count(*) from t_account where year = '2012';

-- 聚合函数2 分组聚合

-- group by 
select areaid, sum(money) from t_account group by areaid;

select areaid, t.* from t_account t;

-- 分组后条件查询
select areaid, sum(money) from t_account group by areaid having sum(money) > 169000;


---------------连接查询----

-- 多表内连接查询

select o.id as "业主编号"，
o.name as "业主名称",
ot.name as "业务类型"
from t_owners o, t_ownertype ot
where o.ownertypeid = ot.id;

select o.id as "业主编号"，
o.name as "业主名称",
ad.name as "地址",
ot.name as "业务类型"
from t_owners o, t_ownertype ot, t_address ad
where o.ownertypeid = ot.id
and o.addressid = ad.id;

select o.id as "业主编号"，
o.name as "业主名称",
ar.name as "区域",
ad.name as "地址",
ot.name as "业务类型"
from t_owners o, t_ownertype ot, t_address ad, t_area ar
where o.ownertypeid = ot.id
and o.addressid = ad.id
and ad.areaid = ar.id;

select o.id as "业主编号"，
o.name as "业主名称",
ar.name as "区域",
ad.name as "地址",
op.name as "收费员",
ot.name as "业务类型"
from t_owners o, t_ownertype ot, t_address ad, t_area ar, t_operator op
where o.ownertypeid = ot.id
and o.addressid = ad.id
and ad.areaid = ar.id
and ad.operatorid = op.id;

-- 左外连接

--1, 
select ow.id, ow.name,ac.year,ac.month,ac.money from t_owners ow left join t_account ac
on ow.id = ac.owneruuid;

--2,

select ow.id, ow.name,ac.year,ac.month,ac.money from t_owners ow, t_account ac
where ow.id = ac.owneruuid(+);

-- 右连接

select ow.id, ow.name,ac.year,ac.month,ac.money from t_owners ow right join t_account ac
on ow.id = ac.owneruuid;

-- 

select ow.id, ow.name,ac.year,ac.month,ac.money from t_owners ow, t_account ac
where ow.id(+)= ac.owneruuid;

-- 子查询
-- where 中的子查询

-- 单行子查询（=, >, >=, <, <=, <>）
select * from t_account t where year = '2012' and month = '01' and usenum >
(
select avg(usenum) from t_account where year = '2012' and month = '01'
) ;

-- 多行子查询 （in, any, all）
select * from t_owners t where t.addressid in (1,3,4);

select * from t_owners t where t.addressid in (
select id from t_address ad where ad.name like '%花园%'
);


select * from t_owners t where t.addressid  not in (
select id from t_address ad where ad.name like '%花园%'
);

-- from 字句中的子查询

select * from (
select o.id as "业主编号", o.name as "业主名称", ot.name as "业主类型"
from t_owners o, t_ownertype ot
where o.ownertypeid = ot.id
) t where t."业主类型" = '居民';

-- select 字句中的子查询

select id, name,(select name from t_address where id = addressid) as addressname from 
t_owners;

-- 这种select 中的子查询需要注意一点，查出来的结果只能有一条
select id, name, (
select name from t_address where id = addressid
) as addressname, (
select (select name from t_area where id = areaid) from t_address where id = addressid
) as areaname from t_owners;

-- 分页查询 简单分页
select rownum, t.* from t_account t where rownum <= 10;

-- 千万注意， rownum是查询语句在扫描记录时产生的，所以不能使用 > 符号，只能使用小于，或者小于等于。只只用等于号也是不行的
select rownum, t.* from t_account t where rownum > 10 and rownum <= 20;

select * from (select rownum r, t.* from t_account t where rownum <= 20) where r > 10;

-- 基于排序的分页  看下r列的数据

-- 错误写法rownum 是在表扫描时产生的，而排序是在表扫描之后进行的，所以这个时候的排序是不准确的
select * from (select rownum r, t.* from t_account t where rownum <= 20 order by usenum desc) where r > 10;

-- 正确写法
select * from (
select rownum r, j.* from (select t.* from t_account t order by t.usenum desc) j where rownum <=20
) where r > 10;












