package com.wjw.cloudnote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wenjianwu
 * @date 2019/1/9 0009 上午 11:40
 */
@Controller
@RequestMapping(value="/portal")
public class ProtalController {
    @RequestMapping("/home")
    public String home() {
        return "index";
    }
   /* @RequestMapping("/drag")
    public String drag(){
        return "drag";
    }
    @RequestMapping("/change")
    public  String change(){
        return  "changediv";
    }
    @RequestMapping("/account")
    public  String account(){
        return  "account";
    }
    @RequestMapping("/simple")
    public  String simple(){
        return  "simple";
    }*/
}
