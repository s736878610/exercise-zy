package com.zy.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadPoolTest
 *
 * @author zhongyuan
 * @since 2023/2/1
 */
public class SingleThreadPoolTest {

    public static void main(String[] args) {
        // 单例线程池 里面只有一个线程
        ExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();

        for (int i = 0; i < 5; i++) {
            final int j = i;
            threadPool.execute(() -> System.out.println(j + ":" + Thread.currentThread().getName()));
        }

        threadPool.shutdown();
    }
}
