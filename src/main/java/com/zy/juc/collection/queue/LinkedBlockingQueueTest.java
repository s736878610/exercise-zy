package com.zy.juc.collection.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueueTest
 *
 * @author zhongyuan
 * @since 2023/1/22
 */
public class LinkedBlockingQueueTest {

    /**
     * LinkedBlockingQueue 使用链表实现的阻塞队列
     */
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    static Random random = new Random();

    public static void main(String[] args) {

        // 阻塞的原理 Condition.await() 可能是底层实现了LockSupport的park()

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    // queue.put() 装满了会等待 阻塞住
                    queue.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        // queue.take() 取空了会等待 阻塞住
                        System.out.println(Thread.currentThread().getName() + " take-" + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "consumer-" + i).start();
        }
    }
}
