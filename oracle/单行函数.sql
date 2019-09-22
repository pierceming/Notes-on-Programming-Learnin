-- ���к���

-- �ַ�������
select length('abcd') from dual;

-- �ַ����ü�
select substr('abcd',2,2) from dual;

-- �ַ���ƴ��
select concat('abc','d') from dual;

-- �ַ���ƴ��2
select 'abc' || 'd' from dual;

-- ��������
select round(100.567) from dual;

select round(100.567,2) from dual;

--��ȡ����
select trunc(100.567) from dual;

select trunc(100.567,2) from dual;

-- ���ں���
select sysdate from dual;
-- ���º���
select add_months(sysdate,2) from dual;

-- ���������һ��
select last_day(sysdate) from dual;
-- ���ڽ�ȡ����
select trunc(sysdate,'yyyy') from dual;

select trunc(sysdate,'mm') from dual;

-- ����ת�����ַ���
select to_char(1024) from dual;

select to_char(sysdate,'yyyy-mm-dd hh:mi:ss') from dual;

select to_date('2017-01-01', 'yyyy-mm-hh') from dual;

-- �ַ���ת������
select to_number('100.45') from dual;

-- ��ֵ������nvl
select nvl(null,0) from dual;

select price, minnum,nvl(maxnum,999999) from t_pricetable where ownertypeid =1;

-- ��ֵ������nvl2

select price, minnum,nvl2(maxnum,to_char(maxnum),'����') from t_pricetable where ownertypeid =1;

-- 3������ȡֵ��ʽ decode, case when then else end
-- decode�о�����
select name, decode(ownertypeid,1,'����', 2, '������ҵ��λ', 3, '��ҵ') as "����" from t_owners;
-- ��ʹ��case when then
select name,(
case ownertypeid
  when 1 then '����'
  when 2 then '������ҵ��λ'
  when 3 then '��ҵ'
  else '����'
    end  -- ע�����end�ǽ�����䣬����Ҫ��
) as "����" from t_owners;

-- ����ת��
select * from t_area;
select * from t_account;

select (select name from t_area where id = areaid) ����,
sum(case when month = '01' then money else 0 end) һ��,
sum(case when month = '02' then money else 0 end) ����,
sum(case when month = '03' then money else 0 end) ����,
sum(case when month = '04' then money else 0 end) ����,
sum(case when month = '05' then money else 0 end) ����,
sum(case when month = '06' then money else 0 end) ����,
sum(case when month = '07' then money else 0 end) ����,
sum(case when month = '08' then money else 0 end) ����,
sum(case when month = '09' then money else 0 end) ����,
sum(case when month = '010' then money else 0 end) ʮ��,
sum(case when month = '011' then money else 0 end) ʮһ��,
sum(case when month = '012' then money else 0 end) ʮ����
from t_account where year = '2012' group by areaid;

-- ��������
-- rank���� ��ͬ��ֵ������ͬ��������Ծ
select rank() over(order by usenum desc), usenum from t_account;

-- dense_rank ��ͬ��ֵ��������ͬ����������

select dense_rank() over(order by usenum desc), usenum from t_account;

-- row_number ����������ֵ�� ����ֵ�Ƿ���ͬ
select row_number() over(order by usenum desc),usenum from t_account;

-- ʹ��row_number������ҳ
select * from
(
select row_number() over(order by usenum desc) rownumber, usenum from t_account
) where rownumber > 10 and rownumber <= 20;

-- union����

select * from t_owners where id <= 7
union
select * from t_owners where id >= 5;

--���� intersect
select * from t_owners where id <= 7
intersect
select * from t_owners where id >= 5;

-- minus � �������ڵ�һ�����ϵļ�¼�������ڵڶ������ϵļ�¼

select * from t_owners where id <= 7
minus
select * from t_owners where id >= 5;


-- 
select * from t_account;
select * from t_area;

select ar.name as "����",() as "��ˮ��", () as "���";

select (select ar.name from t_area ar where ar.id = areaid) as "����", round(sum(usenum)/1000,3) as "��ˮ��(��)", sum(money) as "���" from t_account t group by areaid;









