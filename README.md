# shiyukirito communtiy

#Mysql数据库脚本
```sql
create  table  user(
    id int primary key auto_increment,
    account_id varchar(100),
    name varchar(100),
    token char(36),
    gmt_create bigint,
    gmt_modified bigint
);
```