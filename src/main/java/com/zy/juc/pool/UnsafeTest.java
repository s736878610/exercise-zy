package com.zy.juc.pool;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * UnsafeTest
 *
 * @author zhongyuan
 * @since 2023/2/15
 */
public class UnsafeTest {

    static final Unsafe UNSAFE;

    static final long I_OFFSET;

    static volatile int i;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            Class<UnsafeTest> testClass = UnsafeTest.class;
            I_OFFSET = UNSAFE.staticFieldOffset(testClass.getDeclaredField("i"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void inc() {
        int t = i;
        // CAS成功则跳出循环
        while (!UNSAFE.compareAndSwapInt(UnsafeTest.class, I_OFFSET, t, ++t)) {
            // 防止cpu卡死
            Thread.yield();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 核心线程数为0 最大线程数为Integer的最大值 来一个任务启一个线程执行
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 开10000个线程
        for (int j = 0; j < 10000; j++) {
            threadPool.execute(UnsafeTest::inc);
        }

        // 关闭线程池 等待所有任务完成
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println(i);
    }
}
