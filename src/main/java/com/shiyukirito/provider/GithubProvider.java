package com.shiyukirito.provider;

import com.alibaba.fastjson.JSON;
import com.shiyukirito.dto.AccessTokenDTO;
import com.shiyukirito.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Component
public class GithubProvider {
    public  String getAccessToken(AccessTokenDTO accessTokenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");
         OkHttpClient client = new OkHttpClient.Builder()
                 .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080)))
                 .build();

//        System.out.println(JSON.toJSONString(accessTokenDTO));
         Request request = new Request.Builder().url("https://github.com/login/oauth/access_token?client_id="+accessTokenDTO.getClient_id()+"&client_secret="+accessTokenDTO.getClient_secret()
                 +"&code="+accessTokenDTO.getCode()+"&redirect_uri=http://localhost:8887/callback&state=1")
                .build();
//         System.out.println("https://github.com/login/oauth/access_token?client_id="+accessTokenDTO.getClient_id()+"&client_secret="+accessTokenDTO.getClient_secret()
//                +"&code="+accessTokenDTO.getCode()+"&redirect_uri=http://localhost:8887/callback&state=1");
         try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            System.out.println(str);
            str=str.split("&")[0];
            return str;
         } catch (Exception e){
            System.out.println(e);
            return null;
         }

    }

    public GithubUserDTO getUser(String accesstoken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?"+accesstoken)
                .build();
        System.out.println("https://api.github.com/user?"+accesstoken);
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            System.out.println(str);
            GithubUserDTO user = JSON.parseObject(str,GithubUserDTO.class);
            return user;
        }
        catch (Exception e){
            return  null;
        }
    }

}
