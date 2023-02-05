package com.zy.juc.print;

import java.util.concurrent.locks.LockSupport;

/**
 * LockTest
 * <p>
 * 要求用线程顺序打印 A1B2C3...Z26
 *
 * @author zhongyuan
 * @since 2023/1/25
 */
public class LockSupportTest {

    static Thread t1, t2;

    public static void main(String[] args) {

        char[] number = "1234567".toCharArray();
        char[] word = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char c : word) {
                System.out.print(c);
                // 叫醒t2
                LockSupport.unpark(t2);
                // 自己阻塞
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char n : number) {
                // 自己阻塞
                LockSupport.park();
                System.out.print(n);
                // 叫醒t1
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}
