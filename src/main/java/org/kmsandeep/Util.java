package org.kmsandeep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static void main(String[] args) {
        List<Character> list = List.of('a','a','b','b','b','c','d','e','e');
        HashMap<Character,Integer> map = new HashMap<>();
        Character res = null;
//        int count=0;
        for(Character c: list){
            if(!map.containsKey(c)){
                map.put(c,0);
            }
            else {
                Integer integer = map.get(c);
                map.put(c,integer+1);
            }
        }
        int small = Integer.MAX_VALUE;
        for(Map.Entry<Character,Integer> entry: map.entrySet()){
            if (entry.getValue() < small){
                small = entry.getValue();
                res = entry.getKey();
            }

        }
        System.out.println("res = "+ res);
    }
    public static void println(Object object){
//        System.out.println(Thread.currentThread().getName()+": "+object);



    }

    // a,a,b,b,b,c,d,e,e



}
