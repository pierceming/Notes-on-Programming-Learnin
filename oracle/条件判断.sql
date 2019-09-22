-- 条件判断
-- 异常
declare 
   v_price1 number(10,2); --不足5吨的单价
   v_price2 number(10,2); --超过5吨，不足10吨的单价
   v_price3 number(10,2); --超过10吨的单价
   v_account t_account%rowtype; -- 记录型
   v_usenum2 number(10,2); --使用吨数
   v_money number(10,2); -- 水费金额
   
begin
  -- 对单价进行赋值
  v_price1 := 2.45;
  v_price2 := 3.45;
  v_price3 := 4.45;
  -- 赋值
  select * into v_account from t_account   --注意下这种select ... into ... from ...的赋值方式
  where year = '2012' and month = '01' and owneruuid = 1;
  
  -- 使用吨数
  v_usenum2 := round(v_account.usenum/1000,2);
  -- 计算金额  阶梯水费
  if v_usenum2 <= 5 then
    v_money := v_price1*v_usenum2;
  elsif v_usenum2 > 5 and v_usenum2 <= 10 then
    v_money := v_price1*5 + v_price2 *(v_usenum2 - 5);
  else 
    v_money := v_price1*5 + v_price2 *5 +v_price3 *(v_usenum2 - 10);
  end if;
  DBMS_OUTPUT.put_line('单价:'||v_price1||'吨数:'||v_usenum2||'金额:'||v_money); 
exception
  when NO_DATA_FOUND then
    DBMS_OUTPUT.put_line('未找到数据，请核实');
  when TOO_MANY_ROWS then
    DBMS_OUTPUT.put_line('查询条件有误，返回多个值，请核实'); 
end;

-- 循环语句
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


-- 条件循环
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

-- for循环
declare 
v_num number :=0;
begin 
  for i in 1..100
    loop
    v_num := v_num + i;
    end loop;
  DBMS_OUTPUT.put_line(v_num); 
end;

-- 游标 cursor  可以理解为pl.sql中的结果集
declare 
   v_pricetable t_pricetable%rowtype; --价格行对象
   cursor cur_pricetable is select * from t_pricetable where ownertypeid = 1; --定义游标
   begin
     open cur_pricetable;
     loop
       fetch cur_pricetable into v_pricetable; --提取游标值到变量
       exit when cur_pricetable%notfound; -- 当游标到最后一行下面退出循环
     DBMS_OUTPUT.put_line('价格:'||v_pricetable.price||'吨位:'||v_pricetable.minnum||'-'||v_pricetable.maxnum); 
     end loop;
   close cur_pricetable; -- 关闭游标
end;


-- 带参数的游标

declare
   v_pricetable t_pricetable%rowtype;
   cursor cur_pricetable(v_ownertypeid number) is
   select * from t_pricetable where ownertypeid = v_ownertypeid; -- 定义游标
begin
  open cur_pricetable(2); -- 打开游标
  loop
    fetch cur_pricetable into v_pricetable;  --提取游标到变量
    exit when cur_pricetable % notfound;
    DBMS_OUTPUT.put_line('价格:'||v_pricetable.price||'吨位:'||v_pricetable.minnum||'-'||v_pricetable.maxnum); 
    end loop;
    close cur_pricetable;
end;

-- for 循环提取游标，解决游标的提取，关闭操作
declare 
   cursor cur_pricetable (v_ownertypeid number) is select * from t_pricetable where ownertypeid = v_ownertypeid; -- 定义游标
begin
  for v_pricetable in cur_pricetable(3)
    loop
      DBMS_OUTPUT.put_line('价格:'||v_pricetable.price||'吨位:'||v_pricetable.minnum||'-'||v_pricetable.maxnum); 
  end loop;
end;
   
     




