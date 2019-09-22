-- 存储函数-存储过程

-- plsql 是oracle对sql语句的过程化扩展

-- 基本语法结构

-- [declare
         -- 申明变量
--]
-- begin
   -- 逻辑代码
   -- [exception
      -- 异常代码处理
   --]
--end;

-- 二变量

-- 申明变量 变量名 类型（长度）;
-- 变量赋值 变量名 := 变量值

-- 实例1

declare 
   v_price number(10,2); -- 水费单价
   v_usenum number; -- 水费字数
   v_usenum2 number(10,2); -- 吨数
   v_money number(10,2); -- 金额
   
begin
  v_price := 2.45; 
  v_usenum := 8012;
  v_usenum2 := round(v_usenum/1000,2);
  v_money := round(v_price*v_usenum2,2);
  dbms_output.put_line('单价:' || v_price ||'吨数:'||v_usenum2||'金额:'||v_money);
end;



-- select into 方式赋值
declare 
   v_price number(10,2); -- 水费单价
   v_usenum number; -- 水费字数
   v_num0 number;
   v_num1 number;
   v_usenum2 number(10,2); -- 吨数
   v_money number(10,2); -- 金额
   
begin
  v_price := 2.45; 
  -- 变量赋值
  select usenum, num0,num1 into v_usenum,v_num0,v_num1 from 
  t_account where year = '2012' and month = '01' and owneruuid = 1;
  
  v_usenum2 := round(v_usenum/1000,2);
  v_money := round(v_price*v_usenum2,2);
  dbms_output.put_line('单价:' || v_price ||'吨数:'||v_usenum2||'金额:'||v_money);
end;

-- 属性类型

-- %TYPE引用   引用某表某列的字段类型
declare 
   v_price number(10,2); -- 单价
   v_usenum t_account.usenum %TYPE; --水费字数
   v_num0 t_account.num0 %TYPE; -- 上月字数
   v_num1 t_account.num1 %TYPE; -- 本月字数
   v_usenum2 number(10,2); --使用吨数
   v_money number(10,2); -- 水费金额
begin
  -- 对单价进行赋值
  v_price := 3.45;
  select usenum,num0,num1 into v_usenum,v_num0,v_num1 from t_account 
  where year ='2012' and month ='01' and owneruuid =1;
  
  -- 使用吨数
  v_usenum2 := round(v_usenum/1000,2);
  -- 计算金额
  v_money := v_price*v_usenum2;
  DBMS_OUTPUT.put_line('单价:'||v_price||'吨数:'||v_usenum2||'金额:'||v_money||'上月字数:'||v_num0||'本月字数:'||v_num1);
end; 



-- 记录型 %ROWTYPE
declare 
   v_price number(10,2); --单价
   v_account t_account%rowtype; -- 记录型
   v_usenum2 number(10,2); --使用吨数
   v_money number(10,2); -- 水费金额
   
begin
  -- 对单价进行赋值
  v_price := 3.45;
  -- 赋值
  select * into v_account from t_account   --注意下这种select ... into ... from ...的赋值方式
  where year = '2012' and month = '01' and owneruuid = 1;
  
  -- 使用吨数
  v_usenum2 := round(v_account.usenum/1000,2);
  -- 计算金额
  v_money := v_price*v_usenum2;
  DBMS_OUTPUT.put_line('单价:'||v_price||'吨数:'||v_usenum2||'金额:'||v_money); 
end;

-- 异常
declare 
   v_price number(10,2); --单价
   v_account t_account%rowtype; -- 记录型
   v_usenum2 number(10,2); --使用吨数
   v_money number(10,2); -- 水费金额
   
begin
  -- 对单价进行赋值
  v_price := 3.45;
  -- 赋值
  select * into v_account from t_account   --注意下这种select ... into ... from ...的赋值方式
  where year = '2012' and month = '01' and owneruuid = 1;
  
  -- 使用吨数
  v_usenum2 := round(v_account.usenum/1000,2);
  -- 计算金额
  v_money := v_price*v_usenum2;
  DBMS_OUTPUT.put_line('单价:'||v_price||'吨数:'||v_usenum2||'金额:'||v_money); 
exception
  when NO_DATA_FOUND then
    DBMS_OUTPUT.put_line('未找到数据，请核实');
  when TOO_MANY_ROWS then
    DBMS_OUTPUT.put_line('查询条件有误，返回多个值，请核实'); 
end;





