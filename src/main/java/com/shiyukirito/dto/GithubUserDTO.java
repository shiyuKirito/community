package com.shiyukirito.dto;

import java.security.PrivateKey;

public class GithubUserDTO {
        private  String name;
        private  long id;
        private  String dio;
        private  String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatart_url) {
        this.avatar_url = avatart_url;
    }


    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public void setLogin(String name){this.name = name;} //github 返回的JSON中Login对应name;
    public String getLogin(){return  name;}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDio() {
        return dio;
    }

    public void setDio(String dio) {
        this.dio = dio;
    }
}
