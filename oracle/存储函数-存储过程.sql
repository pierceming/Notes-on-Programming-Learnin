-- �洢����-�洢����

-- plsql ��oracle��sql���Ĺ��̻���չ

-- �����﷨�ṹ

-- [declare
         -- ��������
--]
-- begin
   -- �߼�����
   -- [exception
      -- �쳣���봦��
   --]
--end;

-- ������

-- �������� ������ ���ͣ����ȣ�;
-- ������ֵ ������ := ����ֵ

-- ʵ��1

declare 
   v_price number(10,2); -- ˮ�ѵ���
   v_usenum number; -- ˮ������
   v_usenum2 number(10,2); -- ����
   v_money number(10,2); -- ���
   
begin
  v_price := 2.45; 
  v_usenum := 8012;
  v_usenum2 := round(v_usenum/1000,2);
  v_money := round(v_price*v_usenum2,2);
  dbms_output.put_line('����:' || v_price ||'����:'||v_usenum2||'���:'||v_money);
end;



-- select into ��ʽ��ֵ
declare 
   v_price number(10,2); -- ˮ�ѵ���
   v_usenum number; -- ˮ������
   v_num0 number;
   v_num1 number;
   v_usenum2 number(10,2); -- ����
   v_money number(10,2); -- ���
   
begin
  v_price := 2.45; 
  -- ������ֵ
  select usenum, num0,num1 into v_usenum,v_num0,v_num1 from 
  t_account where year = '2012' and month = '01' and owneruuid = 1;
  
  v_usenum2 := round(v_usenum/1000,2);
  v_money := round(v_price*v_usenum2,2);
  dbms_output.put_line('����:' || v_price ||'����:'||v_usenum2||'���:'||v_money);
end;

-- ��������

-- %TYPE����   ����ĳ��ĳ�е��ֶ�����
declare 
   v_price number(10,2); -- ����
   v_usenum t_account.usenum %TYPE; --ˮ������
   v_num0 t_account.num0 %TYPE; -- ��������
   v_num1 t_account.num1 %TYPE; -- ��������
   v_usenum2 number(10,2); --ʹ�ö���
   v_money number(10,2); -- ˮ�ѽ��
begin
  -- �Ե��۽��и�ֵ
  v_price := 3.45;
  select usenum,num0,num1 into v_usenum,v_num0,v_num1 from t_account 
  where year ='2012' and month ='01' and owneruuid =1;
  
  -- ʹ�ö���
  v_usenum2 := round(v_usenum/1000,2);
  -- ������
  v_money := v_price*v_usenum2;
  DBMS_OUTPUT.put_line('����:'||v_price||'����:'||v_usenum2||'���:'||v_money||'��������:'||v_num0||'��������:'||v_num1);
end; 



-- ��¼�� %ROWTYPE
declare 
   v_price number(10,2); --����
   v_account t_account%rowtype; -- ��¼��
   v_usenum2 number(10,2); --ʹ�ö���
   v_money number(10,2); -- ˮ�ѽ��
   
begin
  -- �Ե��۽��и�ֵ
  v_price := 3.45;
  -- ��ֵ
  select * into v_account from t_account   --ע��������select ... into ... from ...�ĸ�ֵ��ʽ
  where year = '2012' and month = '01' and owneruuid = 1;
  
  -- ʹ�ö���
  v_usenum2 := round(v_account.usenum/1000,2);
  -- ������
  v_money := v_price*v_usenum2;
  DBMS_OUTPUT.put_line('����:'||v_price||'����:'||v_usenum2||'���:'||v_money); 
end;

-- �쳣
declare 
   v_price number(10,2); --����
   v_account t_account%rowtype; -- ��¼��
   v_usenum2 number(10,2); --ʹ�ö���
   v_money number(10,2); -- ˮ�ѽ��
   
begin
  -- �Ե��۽��и�ֵ
  v_price := 3.45;
  -- ��ֵ
  select * into v_account from t_account   --ע��������select ... into ... from ...�ĸ�ֵ��ʽ
  where year = '2012' and month = '01' and owneruuid = 1;
  
  -- ʹ�ö���
  v_usenum2 := round(v_account.usenum/1000,2);
  -- ������
  v_money := v_price*v_usenum2;
  DBMS_OUTPUT.put_line('����:'||v_price||'����:'||v_usenum2||'���:'||v_money); 
exception
  when NO_DATA_FOUND then
    DBMS_OUTPUT.put_line('δ�ҵ����ݣ����ʵ');
  when TOO_MANY_ROWS then
    DBMS_OUTPUT.put_line('��ѯ�������󣬷��ض��ֵ�����ʵ'); 
end;





