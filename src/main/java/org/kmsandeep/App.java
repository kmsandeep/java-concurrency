package org.kmsandeep;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Running");
        });

        thread.start();

        Runnable runnable = () -> {
            System.out.println("Running");
        };

        Object obj = new Object();
        synchronized (obj) {

        }

        Thread thread1 = new Thread(runnable);
        thread1.start();

        int[] arr = {1, 2, 3, 4, 5};
//      IntStream.generate()

        List<Integer> integers = List.of(1, 2, 3, 4);
        integers.stream().filter(n -> n > 3).forEach(System.out::println);


    }

    private class Node<E> {
        E data;
        Node next;


    }

}
