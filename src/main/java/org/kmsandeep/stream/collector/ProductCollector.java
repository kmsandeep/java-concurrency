package org.kmsandeep.stream.collector;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ProductCollector implements Collector<Integer, int[], Integer> {

  @Override
  public Supplier<int[]> supplier() {
    return () -> new int[]{1};
  }

  @Override
  public BiConsumer<int[], Integer> accumulator() {
    return (acc, n) -> acc[0] *= n;
  }

  @Override
  public BinaryOperator<int[]> combiner() {
    return (acc1, acc2) -> {
      acc1[0] *= acc2[0];
      return acc1;
    };
  }

  @Override
  public Function<int[], Integer> finisher() {
    return acc -> acc[0];
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }

  public static void main(String[] args) {
    Integer collect = List.of(2, 3, 4, 5).stream().collect(new ProductCollector());
    System.out.println(collect);

  }
}
