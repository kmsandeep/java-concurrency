package org.kmsandeep;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) throws InterruptedException {

    App app = new App();
    Counter counter = new Counter();

    Thread thread1 = new Thread(getRunnable(counter, "Thread 1: "));
    Thread thread2 = new Thread(getRunnable(counter, "Thread 2: "));

    thread1.start();
    thread2.start();

  }

  private static Runnable getRunnable(Counter counter, String message) {
    return () -> {
      for (int i = 0; i < 100_000; i++) {
        counter.incrementAndGet();
        counter.incrementAndGetAtomic();
      }
      System.out.println("Final - " + message + counter.getCount());
      System.out.println("Final Atomic - " + message + counter.getAtomicCount());
    };
  }

  private static class Counter {

    private  volatile int count = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void incrementAndGet() {
        count++;
    }
    public void incrementAndGetAtomic() {
      atomicInteger.incrementAndGet();
    }

    public int getCount() {
      return count;
    }

    public int getAtomicCount() {
      return atomicInteger.get();
    }


  }

}
