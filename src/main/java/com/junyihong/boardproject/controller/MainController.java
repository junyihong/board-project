package com.junyihong.boardproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * 메인페이지
     * @return 메인 페이지
     */
    @GetMapping("/")
    public String root() {
        return "forward:/articles";
    }

    /**
     * 에러페이지
     * @return 에러 페이지
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
