package org.kmsandeep.thread.blockingQueue;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Stream;

public class BlockingQueueEx {

  public static void main(String[] args) throws InterruptedException {
    BlockingQueueEx main = new BlockingQueueEx();
    MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(10);

//    Thread producer = new Thread(main.addItem(myBlockingQueue), "Producer-1");
//    Thread consumer = new Thread(main.removeItem(myBlockingQueue), "Consumer-1");

    ExecutorService producer = Executors.newFixedThreadPool(3);
    ExecutorService consumer = Executors.newFixedThreadPool(3);

    while (!producer.isTerminated() && !consumer.isTerminated()) {
      producer.submit(main.addItem(myBlockingQueue));
      Thread.sleep(100);
      consumer.submit(main.removeItem(myBlockingQueue));
    }


  }

  private Runnable addItem(MyBlockingQueue<Integer> myBlockingQueue) {
    return () -> {
      for (int i = 0; i < 10; i++) {
        System.out.printf("Producer %s is adding item: %d\n", Thread.currentThread().getName(), i);
        myBlockingQueue.add(i);
        try {
          Thread.sleep(500 + (int)Math.random()*100);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

  private Runnable removeItem(MyBlockingQueue<Integer> myBlockingQueue) {
    return () -> {
      for (int i = 0; i < 10; i++) {
        Integer item = myBlockingQueue.remove();
        System.out.printf("Consumer %s is removing item: %d\n", Thread.currentThread().getName(), item);
        try {
          Thread.sleep(500 + (int)Math.random()*100);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

}


