package com.zy.juc.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockConditionTest
 * <p>
 * 要求用线程顺序打印 A1B2C3...Z26
 *
 * @author zhongyuan
 * @since 2023/1/26
 */
public class LockConditionTest {

    public static void main(String[] args) {

        char[] number = "1234567".toCharArray();
        char[] word = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {

            try {
                lock.lock();

                for (char c : word) {
                    System.out.print(c);
                    // 叫醒t2
                    condition.signal();
                    // 自己阻塞
                    condition.await();
                }

                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {

            try {
                lock.lock();

                for (char n : number) {
                    System.out.print(n);
                    // 叫醒t1
                    condition.signal();
                    // 自己阻塞
                    condition.await();
                }

                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();
    }
}
