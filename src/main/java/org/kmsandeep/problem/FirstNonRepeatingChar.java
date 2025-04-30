package org.kmsandeep.problem;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingChar {
    private static char firstNonRepeatingCharacter(String message) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : message.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : message.toCharArray()) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return '\0';
    }

    public static void main(String[] args) {
        System.out.println(firstNonRepeatingCharacter("aaauu33"));
    }
}
