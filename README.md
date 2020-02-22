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
create  table  question(
    id int primary key auto_increment,
    title varchar(24),
    description TEXT,
    gmt_create bigint,
    gmt_modified bigint,
    creator int,
    comment_count int default  0,
    view_count int default  0,
    like_count int default  0,
    tag varchar(256)
)
```
