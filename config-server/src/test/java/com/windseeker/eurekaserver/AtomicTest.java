package com.windseeker.eurekaserver;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static AtomicInteger i=new AtomicInteger(0);
    public static void main(String[] args) {
        System.out.println(i.incrementAndGet());
        System.out.println(i.addAndGet(2));
    }
}
