package com.ex.forblog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForBlogController {

    @GetMapping(value = "/")
    String hello(){
        return "Hello World!";
    }
}
