package com.nik.hpm.error.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiErrorController implements ErrorController{

    @Override
    public String getErrorPath() {
        return "/error";
    }
    
    @RequestMapping("/error")
    public String notFound() {
        return "forward:/";
    }

}
