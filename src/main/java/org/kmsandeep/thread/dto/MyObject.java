package org.kmsandeep.thread.dto;

public class MyObject {
    private Object object = null;

    public synchronized Object getObject() {
        return object;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
    }
}
