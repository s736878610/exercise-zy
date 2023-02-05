package com.zy.juc.print;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * BlockingQueueTest
 * <p>
 * 要求用线程顺序打印 A1B2C3...Z26
 *
 * @author zhongyuan
 * @since 2023/1/26
 */
public class BlockingQueueTest {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) throws Exception {

        char[] number = "1234567".toCharArray();
        char[] word = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for (char c : word) {
                try {
                    System.out.print(c);
                    q1.put("ok");
                    // take()到值线程才会继续执行 否则阻塞
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {

            for (char n : number) {
                try {
                    // take()到值线程才会继续执行 否则阻塞
                    q1.take();
                    System.out.print(n);
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t2").start();
    }
}
