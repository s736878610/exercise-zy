package com.zy.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPoolTest
 *
 * @author zhongyuan
 * @since 2023/2/1
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) throws Exception {
        // 核心线程数为0 最大线程数为Integer的最大值 来一个任务启一个线程执行
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // [Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
        System.out.println(threadPool);

        for (int i = 0; i < 2; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        // [Running, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]
        System.out.println(threadPool);

        TimeUnit.SECONDS.sleep(5);

        // [Running, pool size = 2, active threads = 0, queued tasks = 0, completed tasks = 2]
        System.out.println(threadPool);

        threadPool.shutdown();
    }
}
