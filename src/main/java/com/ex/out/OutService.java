package com.ex.out;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

public class OutService {
    @PostConstruct
    public void setUp(){
        System.out.println("Out Service regist bean");
    }
}
