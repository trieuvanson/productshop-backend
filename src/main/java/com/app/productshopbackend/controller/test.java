package com.app.productshopbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) throws IOException {
        Map<String,String> map1 = new HashMap<>();
        map1.put("one", "Apple");
        map1.put("two", "Orange");
        Map<String,String> map2 = new HashMap<>();
        map2.put("three", "Cat");
        map2.put("four", "Dog");
        Map<String,String> map3 = new HashMap<>();
        map3.put("five", "Cricket");
        map3.put("six", "Football");
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        List result = list.stream().map(map->new ArrayList<String>(map.values())).
                flatMap(List::stream).collect(Collectors.toList());
        System.out.println(result);

    }
}
