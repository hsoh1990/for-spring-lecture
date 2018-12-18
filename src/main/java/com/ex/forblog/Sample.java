package com.ex.forblog;

import org.springframework.stereotype.Component;

@Component
public class Sample {
    int data =200;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
