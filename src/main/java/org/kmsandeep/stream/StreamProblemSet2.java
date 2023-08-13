package org.kmsandeep.stream;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamProblemSet2 {
    // Transform a list of strings into a concatenated string using streams.

    public static void concatenatedString() {
        List<String> strings = List.of("sam", "bi", "small", "picked", "volley-ball", "eye", "bob");
        System.out.println(strings.stream().collect(Collectors.joining(", ", "{ ", " }")));
    }

    // Find the frequency of each element in a list using streams.
    public static void frequency() {
        List<Integer> integers = List.of(2, 7, 3, 15, 1, 15, 7, 15);
        Map<Integer, Long> frequency = integers.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        System.out.println("frequency: " + frequency);
    }

    // Find the nth Fibonacci number using streams.
    public static void nFibonacci() {
        int n = 12;
        System.out.println("nFibonacci: " +
                Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                        .map(fib -> fib[0])
                        .limit(n).collect(Collectors.toList()));

    }

    // Filter out prime numbers from a list of integers using streams.
    public static void filterPrime() {
        Predicate<Integer> notPrime = n -> n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).anyMatch(div -> n % div == 0);
        List<Integer> integers = List.of(2, 5, 3, 9, 10, 16, 7, 15);
        List<Integer> nonPrimes = integers.stream().filter(notPrime).collect(Collectors.toList());
        System.out.println("nonPrimes: " + nonPrimes);
    }

    // Merge two lists and remove duplicates using streams.
    public static void mergeLists() {
        List<Integer> integers1 = List.of(2, 5, 3, 9, 16);
        List<Integer> integers2 = List.of(10, 16, 7, 15);
        List<Integer> merge = Stream.concat(integers1.stream(), integers2.stream()).distinct().collect(Collectors.toList());
        System.out.println("merge: " + merge);
    }

    // finding the first non-repeated character
    public static void firstNonRepeatedCharacter() {
        String word = "aabbbcddeff";
        Character res = word.chars().mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1L)
                .map(e -> e.getKey())
                .findFirst()
                .get();
        System.out.println("firstNonRepeatedCharacter: " + res);
    }

    // cumulativeSum of list of numbers
    public static void cumulativeSum() {
        List<Integer> numbers = IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

        int[] sum = {0};

        List<Integer> cumulativeSum = numbers.stream()
                .map(num -> sum[0] += num)
                .collect(Collectors.toList());

        System.out.println("Original List: " + numbers);
        System.out.println("Cumulative Sum: " + cumulativeSum);
    }

    public static void longestSubsequence(){
        List<Integer> numbers = Arrays.asList(10, 22, 9, 33, 21, 50, 41, 60, 80);
        List<Integer> longestIncreasingSubsequence = numbers.stream()
                .collect(Collectors.toMap(num -> num, num -> new ArrayList<>(Collections.singletonList(num)),
                        (list1, list2) -> list2, TreeMap::new))
                .values()
                .stream()
                .reduce((list1, list2) -> list2.size() > list1.size() ? list2 : list1)
                .orElse(new ArrayList<>());
        System.out.println("Longest increasing subsequence: " + longestIncreasingSubsequence);
    }

    public static void main(String[] args) {
        concatenatedString();
        frequency();
        nFibonacci();
        filterPrime();
        mergeLists();
        firstNonRepeatedCharacter();
        longestSubsequence();
    }
}
