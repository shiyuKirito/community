package com.shiyukirito.community;

import com.shiyukirito.dto.AccessTokenDTO;
import com.shiyukirito.dto.GithubUserDTO;
import com.shiyukirito.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


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


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                            HttpServletRequest request) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO() ;
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirrectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO user =githubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        if(user!=null){
            //登录成功 写入cookie和session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
            //重定向到index页面中

        } else{
            //登录失败 重新登录
            return "redirect:/";
        }
    }
}
