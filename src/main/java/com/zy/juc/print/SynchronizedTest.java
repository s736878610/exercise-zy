package com.zy.juc.print;

import java.util.concurrent.CountDownLatch;

/**
 * SynchronizedTest
 * <p>
 * 要求用线程顺序打印 A1B2C3...Z26
 *
 * @author zhongyuan
 * @since 2023/1/26
 */
public class SynchronizedTest {

    private static volatile boolean t2State;

    private static final CountDownLatch LATCH = new CountDownLatch(1);

    public static void main(String[] args) {

        final Object o = new Object();

        char[] word = "ABCDEFG".toCharArray();
        char[] number = "1234567".toCharArray();

        new Thread(() -> {

            // t2State = true;

            LATCH.countDown();

            synchronized (o) {

                for (char c : word) {
                    try {
                        System.out.print(c);
                        // 叫醒t2
                        o.notify();
                        // 自己阻塞
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t1").start();

        new Thread(() -> {

            synchronized (o) {

                // 保证t1先运行 方法一
                // while (!t2State) {
                //     try {
                //         o.wait();
                //     } catch (InterruptedException e) {
                //         e.printStackTrace();
                //     }
                // }

                // 保证t1先运行 方法二
                try {
                    LATCH.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (char n : number) {
                    try {
                        System.out.print(n);
                        // 叫醒t1
                        o.notify();
                        // 自己阻塞
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t2").start();

    }
}
