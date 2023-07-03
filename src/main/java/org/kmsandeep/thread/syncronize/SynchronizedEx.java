package org.kmsandeep.thread.syncronize;

import org.kmsandeep.thread.dto.MyObject;

public class SynchronizedEx {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                myObject.setObject(Thread.currentThread().getName() + " " + i);
            }
        },"T1");
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(myObject.getObject());
            }
        },"T2");
        thread1.start();
        thread2.start();
    }
}
