package org.kmsandeep.stream;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamProblemSet1 {


    //   Given a list of integers, find the maximum and minimum values using streams.
    public static void minMax() {
        List<Integer> integers = List.of(2, 1, -3, 15, 11, 45, 7);
        Optional<Integer> max = integers.stream().max(Integer::compareTo);
        Optional<Integer> min = integers.stream().min(Integer::compareTo);
        max.ifPresent(n -> System.out.println("max: " + n));
        min.ifPresent(n -> System.out.println("min: " + n));
    }

    //  Remove duplicate elements from a list using streams.
    public static void removeDuplicate() {
        List<Integer> integers = List.of(2, 11, 23, 15, 11, 45, 23);
        List<Integer> distinct = integers.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct: " + distinct);
    }

    // Count the number of elements in a list using streams.
    public static void count() {
        List<Integer> integers = List.of(2, 1, -3, 15, 11, 45, 7);
        System.out.println("count: " + integers.stream().count());
    }
    //Sort a list of strings in alphabetical order using streams.

    public static void sortAlphabeticalOrder() {
        List<String> strings = List.of("sam", "big", "small", "pick", "arm", "ball", "eye", "bob");
        List<String> sorted = strings.stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println("sorted: " + sorted);
    }

    // Find the average of a list of integers using streams.
    public static void average() {
        List<Integer> integers = List.of(2, 1, -3, 15, 11, 45, 7);
        OptionalDouble average = integers.stream().mapToInt(Integer::valueOf).average();
        average.ifPresent(n -> System.out.printf("average: %.3f\n", n));
    }

    // Extract all names from a list of objects using streams.
    public static void extractNames() {
        List<Object> objects = List.of(5, "karan", 6.8, 5l, "amar", true);
        List<String> strings = objects.stream().filter(obj -> obj instanceof String)
                .map(String::valueOf).collect(Collectors.toList());
        System.out.println("strings: " + strings);
    }

    // Group a list of people by their name-length using streams.
    public static void groupByNameLength() {
        List<String> strings = List.of("ashish", "karan", "amar", "rahul", "rakesh", "rajshekhar");
        Map<Integer, List<String>> groups = strings.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("groups: " + groups);
    }

    // Find the second highest element in an array of integers using streams.
    public static void secondHighest() {
        List<Integer> integers = List.of(2, 1, -3, 15, 11, 45, 7);
        Optional<Integer> secondHighest = integers.stream()
                .sorted(Comparator.comparingInt(Integer::intValue).reversed())
                .skip(1).findFirst();
        secondHighest.ifPresent(n -> System.out.println("secondHighest: " + n));

    }

    // Partition a list of numbers into odd and even using streams.
    public static void partitionEvenOdd() {
        List<Integer> integers = List.of(2, 1, -3, 10, 15, 11, 6, 45, 8, 7);
        Map<Boolean, List<Integer>> partition = integers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("partition: " + partition);
    }

    // Find the longest string in a list of strings using streams.
    public static void longestString() {
        List<String> strings = List.of("sam", "bi", "small", "picked", "volley-ball", "eye", "bob");
        Optional<String> longest = strings.stream()
                .max(Comparator.comparingInt(String::length));
        longest.ifPresent(n -> System.out.println("longest: " + n));
    }

    // Given a list of words, sort them by length and then alphabetically using streams.
    public static void sortByLengthAndAlphabetical() {
        List<String> strings = List.of("sam", "bi", "small", "picked", "volley-ball", "eye", "bob");
        List<String> sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(String::compareTo))
                .collect(Collectors.toList());
        System.out.println("sortByLengthAndAlphabetical: " + sorted);
    }

    public static void main(String[] args) {
        minMax();
        removeDuplicate();
        count();
        sortAlphabeticalOrder();
        average();
        extractNames();
        groupByNameLength();
        secondHighest();
        partitionEvenOdd();
        longestString();
        sortByLengthAndAlphabetical();
    }
}
