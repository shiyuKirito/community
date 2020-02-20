package com.shiyukirito.community;

import com.shiyukirito.mapper.UserMapper;
import com.shiyukirito.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.CookieStore;

@Controller
public class IndexColler {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public  String index(HttpServletRequest request){
        if(request.getCookies()== null){
            return "index";
        }
        Cookie[] cookies= request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.FindbyToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
