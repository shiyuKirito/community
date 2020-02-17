package com.shiyukirito.dto;

import java.security.PrivateKey;

public class GithubUserDTO {
        private  String name;
        private  long id;
        private  String dio;

    public String getLogin() {
        return name;
    }

    public void setLogin(String name) {
        this.name = name;
    }

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
