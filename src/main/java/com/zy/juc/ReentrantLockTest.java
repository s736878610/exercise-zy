package com.zy.juc;


import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLockTest
 *
 * @author zhongyuan
 * @since 2023/1/19
 */
public class ReentrantLockTest {

    private static volatile int i;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();
    }

}
