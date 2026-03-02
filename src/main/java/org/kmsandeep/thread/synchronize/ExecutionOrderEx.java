package org.kmsandeep.thread.synchronize;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ExecutionOrderEx {


  public static void main(String[] args) {
    final Object lock1 = new Object(); // For signaling Thread 2
    final Object lock2 = new Object(); // For signaling Thread 3

    Map<String,String> concurrentMap = new ConcurrentHashMap<>();


    Thread t1 = new Thread(() -> {
      try {
        Thread.sleep(100); // wait for thread-2 to call lock.wait()
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      synchronized (lock1) {
        System.out.println("First");
        lock1.notify(); // Notify Thread 2
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (lock1) {
        try {
          lock1.wait(); // Wait for Thread 1
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }

      synchronized (lock2) {
        System.out.println("Second");
        lock2.notify(); // Notify Thread 3
      }
    });

    Thread t3 = new Thread(() -> {
      synchronized (lock2) {
        try {
          lock2.wait(); // Wait for Thread 2
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        System.out.println("Third");
      }
    });

    // Start threads in any order
    t3.start();
    t2.start();
    t1.start();
  }

}
