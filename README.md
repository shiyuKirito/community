# shiyukirito communtiy

##涉及技术及相关文档
[bootstrap](https://v3.bootcss.com/)  
[mybatis](http://mybatis.org/spring/zh/index.html)  
[thymeleaf](https://www.thymeleaf.org/documentation.html)  
[spring-boot](https://spring.io/projects/spring-boot/) 
## [shiyukirito博客](http://39.105.143.220/)
## [shiyukirito博客](https://blog.csdn.net/kirito9943)（CSDN）

###MySQL数据库脚本
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
###此论坛把重点放在后端实现上，比如：分页不使用插件，主要由后端逻辑实现分页。

###点击[commit](https://github.com/shiyuKirito/community/commits/master)可以查看我的commit记录查看进度

###论坛界面效果展示  （未完成状态）展示部分页面

主界面：![图片](/ReadMeImg/1.png)

发布问题页面：![图片](/ReadMeImg/2.png)




