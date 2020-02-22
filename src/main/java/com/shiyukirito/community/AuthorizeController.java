package com.shiyukirito.community;

import com.shiyukirito.dto.AccessTokenDTO;
import com.shiyukirito.dto.GithubUserDTO;
import com.shiyukirito.mapper.UserMapper;
import com.shiyukirito.model.User;
import com.shiyukirito.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.Redirect_uri}")
    private String redirrectUrl;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response,
                           HttpServletRequest request)   {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO() ;
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirrectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUser =githubProvider.getUser(accessToken);
        if(githubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(String.valueOf(githubUser.getLogin()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(String.valueOf(githubUser.getAvatar_url()));
            userMapper.insert(user);
            //写入cookie
            response.addCookie(new Cookie("token",token));
            //登录成功 写入cookie和session
            request.getSession().setAttribute("user", githubUser);

            return "redirect:/";
            //重定向到index页面中

        } else{
            //登录失败 重新登录
            return "redirect:/";
        }
    }
}
