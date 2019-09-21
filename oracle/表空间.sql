create tablespace softwater   -- softwater 是表空间名字，
datafile '/data/tablespace/softwater.dbf'  -- softwater下的数据文件，都存在这里
size 100m   -- 默认表空间大小
autoextend on -- 开启自动增长模式
next 50m   -- 如果满100M,则自动增长
