package com.cl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/mvc")
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @RequestMapping("/show")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("show");
        modelAndView.addObject("userName", "cl");
        return modelAndView;
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/devicelist")
    public String devicelist(){
        return "devicelist";
    }

    @RequestMapping("/userlist")
    public String userlist(){
        return "userlist";
    }

    @RequestMapping("/loginIndex") //前端访问mvc/loginIndex
    public String loginIndex(){
        return "login"; // 映射到login.jsp
    }
}
