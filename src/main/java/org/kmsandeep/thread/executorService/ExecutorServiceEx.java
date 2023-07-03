package org.kmsandeep.thread.executorService;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecutorServiceEx {
    public static void main(String[] args) {
        List<String> tasks = List
                .of("Read", "Write", "Play", "Dance", "Cook", "Walk", "Fuck", "Cry", "Run");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List<Future<String>> futures = executorService.invokeAll(
                    tasks.stream()
                            .map(str -> callable(str))
                            .collect(Collectors.toList())
            );
            Observable.fromIterable(futures)
                    .subscribe(m -> System.out.println(m.get()),
                            error -> System.out.println(error),
                            () -> System.out.println("completed"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        tasks.stream()
//                .map(str -> executorService.submit(callable(str)))
//                .forEach(ExecutorServiceEx::printFuture);


        executorService.shutdown();
    }

    private static void printFuture(Future<String> future) {
        System.out.println(future.isDone());
        try {
            System.out.println(" get --> " + future.get());
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
        }
        System.out.println(future.isDone());
        System.out.println();
    }

    private static Runnable runnable(String msg) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " Runnable Task: " + msg);
        };
    }

    private static Callable<String> callable(String msg) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " Callable Task: " + msg);
            return msg;
        };
    }
}
