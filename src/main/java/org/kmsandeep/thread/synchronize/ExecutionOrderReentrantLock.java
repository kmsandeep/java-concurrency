package org.kmsandeep.thread.synchronize;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionOrderReentrantLock {

  Lock lock = new ReentrantLock();
  Condition firstDone = lock.newCondition();
  Condition secondDone = lock.newCondition();

  boolean firstComplete = false;
  boolean secondComplete = false;


  public static void main(String[] args) {
    ExecutionOrderReentrantLock main = new ExecutionOrderReentrantLock();
    main.executionOrder();
  }

  void executionOrder() {
    Thread thread1 = new Thread(() -> {
      try {
        Thread.sleep(100);

        lock.tryLock(1, TimeUnit.SECONDS);
        System.out.println("FIRST");
        firstComplete = true;
        firstDone.signal();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        lock.unlock();
      }

    });
    Thread thread2 = new Thread(() -> {

      try {
        lock.tryLock(1, TimeUnit.SECONDS);
        firstDone.await();
        System.out.println("SECOND");
        secondComplete = true;
        secondDone.signal();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        lock.unlock();
      }

    });
    Thread thread3 = new Thread(() -> {
      try {

        lock.tryLock(1, TimeUnit.SECONDS);
        secondDone.await();
        System.out.println("THIRD");

      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        lock.unlock();
      }
    });

    thread3.start();
    thread2.start();
    thread1.start();
  }

}
