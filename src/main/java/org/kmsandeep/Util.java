package org.kmsandeep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {

    public static void main(String[] args) {
        Util util = new Util();
        Util util1 = util;
        Util util2 = new Util();
        System.out.println(util2.hashCode());
        System.out.println(util1.hashCode());
        System.out.println(util.hashCode());
        // ---> 10000
    }




}
