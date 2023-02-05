package com.zy.juc.print;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * TransferQueueTest
 * <p>
 * 要求用线程顺序打印 A1B2C3...Z26
 *
 * @author zhongyuan
 * @since 2023/1/28
 */
public class TransferQueueTest {

    private static final TransferQueue<Character> QUEUE = new LinkedTransferQueue<>();

    public static void main(String[] args) throws Exception {

        char[] number = "1234567".toCharArray();
        char[] word = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for (char c : word) {
                try {
                    // 调用transfer() 其他线程take()就能拿到值
                    QUEUE.transfer(c);
                    // take() T2线程的值 如果没有值 则阻塞
                    System.out.print(QUEUE.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {

            for (char n : number) {
                try {
                    // take() T1线程的值 如果没有值 则阻塞
                    System.out.print(QUEUE.take());
                    // 调用transfer() 其他线程take()就能拿到值
                    QUEUE.transfer(n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t2").start();
    }
}
