package org.kmsandeep.thread.sharedObject;

import java.util.ArrayList;
import java.util.List;

public class MyRunnable implements Runnable {

    private volatile int count = 0;
    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            count++;
//            synchronized (this){count++;}
        }
        System.out.println(Thread.currentThread().getName()+" count: " + count);
    }
    public static void main(String[] args) {
        MyRunnable myRunnable1 = new MyRunnable();
        Thread thread1 = new Thread(myRunnable1,"T1");
//        Thread thread2 = new Thread(myRunnable1,"T2");
//        thread1.start();
//        thread2.start();
//        Class<?>[] classes = Object.class.getClasses();
//        printClasses(classes);
        Class<Thread> threadClass = Thread.class;
        Class<Runnable> runnableClass = Runnable.class;
//        System.out.println(threadClass.getName());
//        printClasses(Thread.class.isInstance());
        printClasses(ArrayList.class.getClasses());

        if(thread1 instanceof Thread){

        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(myRunnable1.count);
    }

    private static void printClasses(Class<?>[] classes) {
        List.of(classes).stream().forEach(c -> System.out.print( c.getName()+", "));
    }
}
