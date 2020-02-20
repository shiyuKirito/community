package com.shiyukirito.mapper;

import com.shiyukirito.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
                                                                                private  int id;
                                                                                private  String name;
                                                                                private  String accountId;
                                                                                private  String token;
                                                                                private  Long gmtCreate;
                                                                                private  Long gmtModified;
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User FindbyToken(@Param("token") String token);
}
