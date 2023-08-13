package org.kmsandeep.thread.executorService;

import io.reactivex.rxjava3.core.Observable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecutorServiceEx {
    public static void main(String[] args) throws IOException {


        InputStream inputStream = Files.newInputStream(Path.of("src/resources/countries-of-the-world.csv"));
        try (CSVParser csvRecords = CSVParser.parse(inputStream, StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
            Map<String, List<CSVRecord>> listMap = csvRecords.stream()
                    .collect(Collectors.groupingBy(
                            record -> record.get(1)
                    ));
        }


        List<String> tasks = List
                .of("Read", "Write", "Play", "Dance",
                        "Cook", "Walk", "Fuck", "Cry", "Run", "Fly", "Spy", "Read", "Write", "Play", "Dance",
                        "Cook", "Walk", "Fuck", "Cry", "Run", "Fly", "Spy");
        System.out.println(tasks.size());
        int n = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            List<Future<String>> futures = executorService.invokeAll(
                    tasks.stream()
                            .map(entry -> callable(String.valueOf(entry)))
                            .collect(Collectors.toList())
            );
            Observable.fromIterable(futures)
                    .subscribe(m -> {},
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
