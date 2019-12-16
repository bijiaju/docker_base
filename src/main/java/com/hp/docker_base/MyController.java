package com.hp.docker_base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kemp on 2018/8/15.
 */
@Controller
public class MyController {

    @RequestMapping("/test")
    public String test(){
        return "index";
    }
}