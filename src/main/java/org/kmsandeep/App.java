package org.kmsandeep;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Runnable runnable = () -> {
            System.out.println("Thread running: " + Thread.currentThread());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread finished: " + Thread.currentThread());

        };
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Thread main: " + Thread.currentThread());


    }
}
