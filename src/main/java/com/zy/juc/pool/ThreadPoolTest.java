package com.zy.juc.pool;

import java.util.concurrent.*;

/**
 * ThreadPoolTest
 * <p>
 * ThreadPoolExecutor源码解析 详见有道云笔记
 *
 * @author zhongyuan
 * @since 2023/1/30
 */
public class ThreadPoolTest {

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor
            (
                    // 核心线程数
                    6,
                    // 最大线程数
                    12,
                    // 空闲线程存活时间 (核心线程以外的线程)
                    60,
                    // 空闲线程存活时间参数的时间单位
                    TimeUnit.MINUTES,
                    // 阻塞队列
                    new SynchronousQueue<>(),
                    // 线程工厂
                    Executors.defaultThreadFactory(),
                    // 拒绝策略
                    (r, executor) -> {
                        // 不用抛出异常 线程池满载情况下直接由调用线程执行
                        r.run();
                    }
            );

    public static void main(String[] args) {
        THREAD_POOL.submit(() -> System.out.println("提交任务1"));
        THREAD_POOL.submit(() -> System.out.println("提交任务2"));
        THREAD_POOL.submit(() -> System.out.println("提交任务3"));
        THREAD_POOL.execute(() -> System.out.println("任务执行"));
        THREAD_POOL.shutdown();
    }

    public static void shutdown() {
        THREAD_POOL.shutdown();
    }

    // public static ThreadPoolExecutor getThreadPool() {
    //     return new ThreadPoolExecutor(
    //             10,// 核心线程数
    //             1000,// 最大线程数
    //             3,// 空闲线程存活时间
    //             TimeUnit.SECONDS,// 空闲线程存活时间参数的时间单位
    //             new ArrayBlockingQueue<>(1024),// 阻塞队列
    //             Executors.defaultThreadFactory(),// 线程工厂
    //             new ThreadPoolExecutor.CallerRunsPolicy());// 拒绝策略
    // }

}
