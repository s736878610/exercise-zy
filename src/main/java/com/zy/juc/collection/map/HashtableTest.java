package com.zy.juc.collection.map;

import com.zy.constants.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Hashtable;
import java.util.UUID;

/**
 * HashtableTest
 *
 * @author zhongyuan
 * @since 2023/1/21
 */
public class HashtableTest {

    static Hashtable<UUID, UUID> hashtable = new Hashtable<>();

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
                hashtable.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        // --------------------写入--------------------

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

        System.out.println("写入耗时：" + (end - start));
        System.out.println("Hashtable插入数据量：" + hashtable.size());

        // --------------------读取--------------------

        start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < Constants.TEN_MILLION; j++) {
                    hashtable.get(keys[10]);
                }
            });
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

        end = System.currentTimeMillis();

        System.out.println("读取耗时：" + (end - start));
    }
}
