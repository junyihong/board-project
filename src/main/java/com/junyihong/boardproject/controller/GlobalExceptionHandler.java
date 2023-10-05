package com.junyihong.boardproject.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // 커스텀 에러 페이지의 Thymeleaf 템플릿 경로
        modelAndView.addObject("errorMessage", ex.getMessage()); // 예외 메시지를 전달
        return modelAndView;
    }
}