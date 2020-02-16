package com.shiyukirito.community;

import com.shiyukirito.dto.AccessTokenDTO;
import com.shiyukirito.dto.GithubUserDTO;
import com.shiyukirito.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;

    @Value("${github.clit.iden}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.Redirect_uri}")
    private String redirrectUrl;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO() ;
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirrectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO user =githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
