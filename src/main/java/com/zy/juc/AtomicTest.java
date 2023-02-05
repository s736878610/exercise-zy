package com.zy.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicTest
 *
 * @author zhongyuan
 * @since 2023/1/11
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
