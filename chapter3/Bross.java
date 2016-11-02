package chapter3;

import com.sun.java.browser.plugin2.DOM;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by bross on 2016. 11. 1..
 */
public class Problem {
    static Map<String, Integer> domains = new HashMap<>();

    public static void main(String[] args) {
        try {
            Files.list(Paths.get("/Users/bross/Documents/Coding/Java/Java8/Practice/src/chapter3/files"))
                    .forEach(file -> {
                        try (Stream<String> stream = Files.lines(Paths.get(file.toString()))) {
                            stream.forEach(line -> {
                                String key = line.split("@")[1];
                                if (domains.containsKey(key)) {
                                    int value = domains.get(key);
                                    domains.remove(key);
                                    domains.put(key, value + 1);
                                } else {
                                    domains.put(key, 1);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
        }
        domains.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEachOrdered(stringIntegerEntry -> System.out.println(stringIntegerEntry.getKey() + " : " + stringIntegerEntry.getValue()));
    }
}