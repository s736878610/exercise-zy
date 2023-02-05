package com.zy.juc;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalTest
 *
 * @author zhongyuan
 * @since 2023/1/20
 */
public class ThreadLocalTest {

    /**
     * ThreadLocal泛型内的对象是线程独有的
     */
    static ThreadLocal<Person> t = new ThreadLocal();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println(t.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            t.set(new Person());
        }).start();

        System.out.println(t);

        t.remove();

    }

    static class Person {
    }

}
