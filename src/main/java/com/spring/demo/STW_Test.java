package com.spring.demo;

import java.util.ArrayList;
import java.util.Collection;

// -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCDetails
public class STW_Test {
    private static final Collection<Object> leak = new ArrayList<>();
    private static volatile Object sink;

    public static void main(String[] args) {
        while (true) {
            try {
                leak.add(new byte[1024 * 1024]);
                sink = new byte[1024 * 1024];
            } catch (OutOfMemoryError e) {
                leak.clear();
            }
        }
    }
}
