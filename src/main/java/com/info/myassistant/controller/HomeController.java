package com.info.myassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author rawalokes
 * Date:3/25/22
 * Time:12:18 PM
 */
@Controller
public class HomeController {
    @GetMapping("/home")
    public String homePage(){
        return "index";
    }
}
