package org.kmsandeep.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerformanceComparison {
  public static void main(String[] args) {
    // Create a large dataset of integers
    int dataSize = 1_00_000_00;
    List<Integer> data = new ArrayList<>(dataSize);
    Random random = new Random();
    for (int i = 0; i < dataSize; i++) {
      data.add(random.nextInt(1000));
    }

    // Measure performance using traditional loops
    long startTime = System.currentTimeMillis();
    long sum = 0;
    for (int num : data) {
      sum += num;
    }
    long endTime = System.currentTimeMillis();
    System.out.println("Traditional loop sum: " + sum);
    System.out.println("Traditional loop time: " + (endTime - startTime) + " ms");

    // Measure performance using streams
    startTime = System.currentTimeMillis();
    sum = data.stream().mapToLong(Integer::longValue).sum();
    endTime = System.currentTimeMillis();
    System.out.println("Stream sum: " + sum);
    System.out.println("Stream time: " + (endTime - startTime) + " ms");

    // Measure performance using parallel streams
    startTime = System.currentTimeMillis();
    sum = data.parallelStream().mapToLong(Integer::longValue).sum();
    endTime = System.currentTimeMillis();
    System.out.println("Parallel stream sum: " + sum);
    System.out.println("Parallel stream time: " + (endTime - startTime) + " ms");
  }
}

