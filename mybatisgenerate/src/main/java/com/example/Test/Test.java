package com.example.Test;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String args[]){
        String str1 = new String();
        String str2 = new String();
        str1.hashCode();
        str2.hashCode();
        boolean equals = str1.equals(str2);
        Map<String,String> map=new HashMap<>();
        map.hashCode();
        String put = map.put("1", "01");
        map.put("1","02");

    }
}
