-- 单行函数

-- 字符串长度
select length('abcd') from dual;

-- 字符串裁剪
select substr('abcd',2,2) from dual;

-- 字符串拼接
select concat('abc','d') from dual;

-- 字符串拼接2
select 'abc' || 'd' from dual;

-- 四舍五入
select round(100.567) from dual;

select round(100.567,2) from dual;

--截取函数
select trunc(100.567) from dual;

select trunc(100.567,2) from dual;

-- 日期函数
select sysdate from dual;
-- 加月函数
select add_months(sysdate,2) from dual;

-- 所在月最后一天
select last_day(sysdate) from dual;
-- 日期截取函数
select trunc(sysdate,'yyyy') from dual;

select trunc(sysdate,'mm') from dual;

-- 数字转换成字符串
select to_char(1024) from dual;

select to_char(sysdate,'yyyy-mm-dd hh:mi:ss') from dual;

select to_date('2017-01-01', 'yyyy-mm-hh') from dual;

-- 字符串转是数字
select to_number('100.45') from dual;

-- 空值处理函数nvl
select nvl(null,0) from dual;

select price, minnum,nvl(maxnum,999999) from t_pricetable where ownertypeid =1;

-- 空值处理函数nvl2

select price, minnum,nvl2(maxnum,to_char(maxnum),'不限') from t_pricetable where ownertypeid =1;

-- 3种条件取值方式 decode, case when then else end
-- decode有局限性
select name, decode(ownertypeid,1,'居民', 2, '行政事业单位', 3, '商业') as "类型" from t_owners;
-- 可使用case when then
select name,(
case ownertypeid
  when 1 then '居民'
  when 2 then '行政事业单位'
  when 3 then '商业'
  else '其他'
    end  -- 注意这个end是结束语句，必须要有
) as "类型" from t_owners;

-- 行列转换
select * from t_area;
select * from t_account;

select (select name from t_area where id = areaid) 区域,
sum(case when month = '01' then money else 0 end) 一月,
sum(case when month = '02' then money else 0 end) 二月,
sum(case when month = '03' then money else 0 end) 三月,
sum(case when month = '04' then money else 0 end) 四月,
sum(case when month = '05' then money else 0 end) 五月,
sum(case when month = '06' then money else 0 end) 六月,
sum(case when month = '07' then money else 0 end) 七月,
sum(case when month = '08' then money else 0 end) 八月,
sum(case when month = '09' then money else 0 end) 九月,
sum(case when month = '010' then money else 0 end) 十月,
sum(case when month = '011' then money else 0 end) 十一月,
sum(case when month = '012' then money else 0 end) 十二月
from t_account where year = '2012' group by areaid;

-- 分析函数
-- rank函数 相同的值排名相同，排名跳跃
select rank() over(order by usenum desc), usenum from t_account;

-- dense_rank 相同的值，排名相同，排名连续

select dense_rank() over(order by usenum desc), usenum from t_account;

-- row_number 返回连续的值， 无论值是否相同
select row_number() over(order by usenum desc),usenum from t_account;

-- 使用row_number函数分页
select * from
(
select row_number() over(order by usenum desc) rownumber, usenum from t_account
) where rownumber > 10 and rownumber <= 20;

-- union函数

select * from t_owners where id <= 7
union
select * from t_owners where id >= 5;

--交集 intersect
select * from t_owners where id <= 7
intersect
select * from t_owners where id >= 5;

-- minus 差集 返回属于第一个集合的记录而不属于第二个集合的记录

select * from t_owners where id <= 7
minus
select * from t_owners where id >= 5;


-- 
select * from t_account;
select * from t_area;

select ar.name as "区域",() as "用水量", () as "金额";

select (select ar.name from t_area ar where ar.id = areaid) as "区域", round(sum(usenum)/1000,3) as "用水量(吨)", sum(money) as "金额" from t_account t group by areaid;









