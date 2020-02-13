package com.shiyukirito.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helloController {
//    @GetMapping("/")
//    public String hello(@RequestParam(name="name") String name,Model model){
//        model.addAttribute("name",name);
//        return "index";
//    }
    @GetMapping("/")
    public  String index(){
        return "index";
    }
}
