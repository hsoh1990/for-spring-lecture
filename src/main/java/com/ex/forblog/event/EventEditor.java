package com.ex.forblog.event;

import java.beans.PropertyEditorSupport;

public class EventEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Event event = (Event)getValue();
        return event.id.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Integer.parseInt(text)));
    }
}
