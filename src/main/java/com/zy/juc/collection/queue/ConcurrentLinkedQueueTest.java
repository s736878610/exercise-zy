package com.zy.juc.collection.queue;

import com.zy.constants.Constants;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueueTest
 *
 * @author zhongyuan
 * @since 2023/1/22
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < Constants.TEN; i++) {
            // 添加元素 返回是否成功布尔值
            queue.offer("a" + i);
        }

        System.out.println(queue);
        System.out.println("Queue size：" + queue.size());

        // queue.poll() 取出一个元素，并从队列中拿走
        System.out.println(queue.poll());
        System.out.println("Queue size：" + queue.size());

        // queue.peek() 取出一个元素，不会队列中拿走
        System.out.println(queue.peek());
        System.out.println("Queue size：" + queue.size());
    }
}
