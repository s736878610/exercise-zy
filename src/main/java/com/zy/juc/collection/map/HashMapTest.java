package com.zy.juc.collection.map;

import com.zy.constants.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.UUID;

/**
 * HashMapTest
 *
 * @author zhongyuan
 * @since 2023/1/21
 */
public class HashMapTest {

    static HashMap<UUID, UUID> hashMap = new HashMap<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    static class MyThread extends Thread {
        int start;
        // 每个线程往里面装多少个数
        int gap = count / THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                hashMap.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            // i * (count / THREAD_COUNT) 可以知道每个线程从哪个数开始
            threads[i] = new MyThread(i * (count / THREAD_COUNT));
        }

        // 启动线程
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有线程结束
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));
        System.out.println("HashMap插入数据量：" + hashMap.size());
    }
}
