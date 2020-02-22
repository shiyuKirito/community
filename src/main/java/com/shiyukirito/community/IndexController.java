package com.shiyukirito.community;

import com.shiyukirito.Serivice.QuesstionService;
import com.shiyukirito.dto.QuestionDTO;
import com.shiyukirito.mapper.QuestionMapper;
import com.shiyukirito.mapper.UserMapper;
import com.shiyukirito.model.Question;
import com.shiyukirito.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.CookieStore;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuesstionService quesstionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        if (request.getCookies() == null) {
            return "index";
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.FindbyToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        List<QuestionDTO> questionList = quesstionService.list();
        model.addAttribute("questions",questionList);



        return "index";
    }
}
