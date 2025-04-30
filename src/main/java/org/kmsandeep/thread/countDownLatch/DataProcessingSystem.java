package org.kmsandeep.thread.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataProcessingSystem {

  public static void main(String[] args) {
    CountDownLatch startSignal = new CountDownLatch(1);
    int numWorkers = 10;
    initializeSystem(numWorkers, startSignal);
    System.out.println("System initialized. Starting Workers");
    startSignal.countDown();
    System.out.println("All processing finished");

  }

  private static void initializeSystem(int numWorkers, CountDownLatch startSignal) {
    loadConfig();
    validateDataFiles();
    startingWorker(numWorkers, startSignal);
  }

  private static void loadConfig() {
    System.out.println("Loading configurations");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static void validateDataFiles() {
    System.out.println("Validating DataFiles");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static void startingWorker(int numWorkers, CountDownLatch startSignal) {
    ExecutorService executorService = Executors.newFixedThreadPool(numWorkers);
    List<Worker> workers = new ArrayList<>();
    for (int i = 0; i < numWorkers; i++) {
      workers.add(new Worker("Worker-" + i, startSignal));
    }
    workers.forEach(executorService::submit);
    executorService.shutdown();
  }

  public static class Worker implements Runnable {

    String name;
    CountDownLatch startSignal;

    public Worker(String name, CountDownLatch startSignal) {
      this.name = name;
      this.startSignal = startSignal;
    }

    @Override
    public void run() {
      try {
        startSignal.await();
        System.out.println(name + " process started");
        Thread.sleep(1000);
        System.out.println(name + " process ended");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

    }
  }

}
