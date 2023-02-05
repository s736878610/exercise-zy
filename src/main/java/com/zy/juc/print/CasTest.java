package com.zy.juc.print;

import static com.zy.juc.print.CasTest.ReadyToRun.T1;
import static com.zy.juc.print.CasTest.ReadyToRun.T2;

/**
 * CasTest
 * <p>
 * 要求用线程顺序打印 A1B2C3...Z26
 *
 * @author zhongyuan
 * @since 2023/1/26
 */
public class CasTest {

    enum ReadyToRun {
        /**
         * 能够执行的线程枚举
         */
        T1, T2
    }

    /**
     * 默认T1先执行
     */
    static volatile ReadyToRun readyToRun = T1;

    public static void main(String[] args) {

        char[] number = "1234567".toCharArray();
        char[] word = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for (char c : word) {
                // 自旋 直到readyToRun等于T1
                while (readyToRun != T1) {
                }
                System.out.print(c);
                readyToRun = T2;
            }

        }, "t1").start();

        new Thread(() -> {

            for (char n : number) {
                // 自旋 直到readyToRun等于T2
                while (readyToRun != T2) {
                }
                System.out.print(n);
                readyToRun = T1;
            }

        }, "t2").start();
    }

}
