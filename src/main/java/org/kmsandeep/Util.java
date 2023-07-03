package org.kmsandeep;

public class Util {
    public static void println(Object object){
        System.out.println(Thread.currentThread().getName()+": "+object);
    }
}
