package com.zy.juc.print;

import java.util.concurrent.Exchanger;

/**
 * ExchangerTest
 * <p>
 * 要求用线程顺序打印 A1B2C3...Z26
 * <p>
 * Exchanger无法实现该需求
 *
 * @author zhongyuan
 * @since 2023/1/28
 */
public class ExchangerTest {

    /**
     * Exchanger 线程之间互相交换数据
     */
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws Exception {

        char[] number = "1234567".toCharArray();
        char[] word = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for (char c : word) {
                try {
                    System.out.print(c);
                    // 有另一个线程调用 exchange() 才会继续执行 否则阻塞
                    EXCHANGER.exchange("T1 OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {

            for (char n : number) {
                try {
                    // 有另一个线程调用 exchange() 才会继续执行 否则阻塞
                    EXCHANGER.exchange("T2 OK");
                    System.out.print(n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t2").start();
    }
}
