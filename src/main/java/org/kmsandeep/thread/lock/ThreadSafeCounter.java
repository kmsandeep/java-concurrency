package org.kmsandeep.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCounter {

  private ReentrantLock lock = new ReentrantLock();
  private int counter = 0;

  private void increment() {
    lock.lock();
    try {
      counter++;
    } finally {
      lock.unlock();
    }
  }

  private int getCount() {
    lock.lock();
    try {
      return counter;
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {

    int numThread = 100;
    Thread[] threads = new Thread[numThread];

    ThreadSafeCounter threadSafeCounter = new ThreadSafeCounter();

    for (int i = 0; i < numThread; i++) {
      threads[i] = new Thread(() -> {
        for (int j = 0; j < 5; j++) {
          threadSafeCounter.increment();
        }
        System.out.println(
            Thread.currentThread().getName() + " counter: " + threadSafeCounter.getCount());
      });
      threads[i].start();
      threads[i].join();
    }

    System.out.println(
        Thread.currentThread().getName() + " counter: " + threadSafeCounter.getCount());


  }

}
