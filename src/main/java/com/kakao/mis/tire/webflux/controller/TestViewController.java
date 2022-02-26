package com.kakao.mis.tire.webflux.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class TestViewController {

    @GetMapping("/test/view")
    public String getView() {
        return "hello";
    }

    @GetMapping("/test/view2")
    public ModelAndViewContainer getView2() {
        final ModelAndViewContainer modelAndViewContainer = new ModelAndViewContainer();
        modelAndViewContainer.setView("hello");
        return modelAndViewContainer;
    }

    @GetMapping("/test/entity")
    public ResponseEntity get() {
        return ResponseEntity.ok().body("hello");
    }

    @GetMapping("/test/mono")
    public ResponseEntity getMono() {
        return ResponseEntity.ok().body(Mono.just("hello"));
    }

}
