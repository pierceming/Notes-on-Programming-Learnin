-- �����ж�
-- �쳣
declare 
   v_price1 number(10,2); --����5�ֵĵ���
   v_price2 number(10,2); --����5�֣�����10�ֵĵ���
   v_price3 number(10,2); --����10�ֵĵ���
   v_account t_account%rowtype; -- ��¼��
   v_usenum2 number(10,2); --ʹ�ö���
   v_money number(10,2); -- ˮ�ѽ��
   
begin
  -- �Ե��۽��и�ֵ
  v_price1 := 2.45;
  v_price2 := 3.45;
  v_price3 := 4.45;
  -- ��ֵ
  select * into v_account from t_account   --ע��������select ... into ... from ...�ĸ�ֵ��ʽ
  where year = '2012' and month = '01' and owneruuid = 1;
  
  -- ʹ�ö���
  v_usenum2 := round(v_account.usenum/1000,2);
  -- ������  ����ˮ��
  if v_usenum2 <= 5 then
    v_money := v_price1*v_usenum2;
  elsif v_usenum2 > 5 and v_usenum2 <= 10 then
    v_money := v_price1*5 + v_price2 *(v_usenum2 - 5);
  else 
    v_money := v_price1*5 + v_price2 *5 +v_price3 *(v_usenum2 - 10);
  end if;
  DBMS_OUTPUT.put_line('����:'||v_price1||'����:'||v_usenum2||'���:'||v_money); 
exception
  when NO_DATA_FOUND then
    DBMS_OUTPUT.put_line('δ�ҵ����ݣ����ʵ');
  when TOO_MANY_ROWS then
    DBMS_OUTPUT.put_line('��ѯ�������󣬷��ض��ֵ�����ʵ'); 
end;

-- ѭ�����
declare 
v_num number :=1;
v_sum number :=0;
begin 
  loop
    --DBMS_OUTPUT.put_line(v_num);
    v_num := v_num + 1;
    v_sum := v_sum + v_num;
    exit when v_num > 100;
  end loop;
  DBMS_OUTPUT.put_line(v_sum); 
end; 


-- ����ѭ��
declare 
v_num number :=1;
v_sum number :=0;
begin 
  while v_num <= 100
    loop
    --DBMS_OUTPUT.put_line(v_num);
    v_num := v_num + 1;
    v_sum := v_sum + v_num;
    end loop;
  DBMS_OUTPUT.put_line(v_sum); 
end;

-- forѭ��
declare 
v_num number :=0;
begin 
  for i in 1..100
    loop
    v_num := v_num + i;
    end loop;
  DBMS_OUTPUT.put_line(v_num); 
end;

-- �α� cursor  �������Ϊpl.sql�еĽ����
declare 
   v_pricetable t_pricetable%rowtype; --�۸��ж���
   cursor cur_pricetable is select * from t_pricetable where ownertypeid = 1; --�����α�
   begin
     open cur_pricetable;
     loop
       fetch cur_pricetable into v_pricetable; --��ȡ�α�ֵ������
       exit when cur_pricetable%notfound; -- ���α굽���һ�������˳�ѭ��
     DBMS_OUTPUT.put_line('�۸�:'||v_pricetable.price||'��λ:'||v_pricetable.minnum||'-'||v_pricetable.maxnum); 
     end loop;
   close cur_pricetable; -- �ر��α�
end;


-- ���������α�

declare
   v_pricetable t_pricetable%rowtype;
   cursor cur_pricetable(v_ownertypeid number) is
   select * from t_pricetable where ownertypeid = v_ownertypeid; -- �����α�
begin
  open cur_pricetable(2); -- ���α�
  loop
    fetch cur_pricetable into v_pricetable;  --��ȡ�α굽����
    exit when cur_pricetable % notfound;
    DBMS_OUTPUT.put_line('�۸�:'||v_pricetable.price||'��λ:'||v_pricetable.minnum||'-'||v_pricetable.maxnum); 
    end loop;
    close cur_pricetable;
end;

-- for ѭ����ȡ�α꣬����α����ȡ���رղ���
declare 
   cursor cur_pricetable (v_ownertypeid number) is select * from t_pricetable where ownertypeid = v_ownertypeid; -- �����α�
begin
  for v_pricetable in cur_pricetable(3)
    loop
      DBMS_OUTPUT.put_line('�۸�:'||v_pricetable.price||'��λ:'||v_pricetable.minnum||'-'||v_pricetable.maxnum); 
  end loop;
end;
   
     




