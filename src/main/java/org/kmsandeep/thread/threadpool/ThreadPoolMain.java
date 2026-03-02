package org.kmsandeep.thread.threadpool;

public class ThreadPoolMain {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10, 100);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(createTask(i));
        }
        threadPool.waitUntilAllTaskCompleted();
        threadPool.stop();
    }
    private static Runnable createTask(int i){
        return () -> System.out.println(Thread.currentThread().getName() + " Task: " + i);
    }
}
