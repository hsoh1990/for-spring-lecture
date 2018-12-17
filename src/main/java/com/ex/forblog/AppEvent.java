package com.ex.forblog;

public class AppEvent {

    private int data;

    private Object source;

    public AppEvent(Object source, int data) {
        this.data = data;
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public int getData() {
        return data;
    }
}
