package org.kmsandeep.stream.collector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringJoiner implements Collector<String, StringBuilder, String> {

  private String prefix;
  private String delimiter;
  private String postfix;

  public StringJoiner(String prefix, String delimiter, String postfix ) {
    this.prefix = prefix;
    this.delimiter = delimiter;
    this.postfix = postfix;
  }

  @Override
  public Supplier<StringBuilder> supplier() {
    return StringBuilder::new;
  }

  @Override
  public BiConsumer<StringBuilder, String> accumulator() {
    return (sb, s) -> sb.append(s).append(delimiter);
  }

  @Override
  public BinaryOperator<StringBuilder> combiner() {
    return (sb1, sb2) -> {
      sb1.append(sb2);
      return sb1;
    };
  }

  @Override
  public Function<StringBuilder, String> finisher() {
    return sb -> prefix + sb.substring(0,sb.length()-delimiter.length()) + postfix;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }

  public static void main(String[] args) {
    StringJoiner stringJoiner = new StringJoiner("{ ",", "," }");

    List<String> list = Arrays.asList("san", "pok", "war", "raj");
    String collect = list.stream().collect(stringJoiner);
    System.out.println(collect);


  }
}
