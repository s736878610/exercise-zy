package com.zy.juc;

import java.util.concurrent.*;

/**
 * CreateThread
 *
 * @author zhongyuan
 * @since 2023/1/8
 */
public class CreateThread {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread!!!");
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnable!!!");
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            System.out.println("MyCallable!!!");
            return "success";
        }
    }

    public static void main(String[] args) throws Exception {
        // 继承 Thread 方式
        new MyThread().start();
        // 实现 Runnable接口 方式 无返回值
        new Thread(new MyRunnable()).start();
        // 实现 Callable接口 方式 有返回值
        String call = new MyCallable().call();
        // FutureTask 方式
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        // Lambda表达式方式
        new Thread(() -> System.out.println("Lambda!!!")).start();
        // 线程池方式
        ExecutorService threadPoolService = Executors.newCachedThreadPool();
        threadPoolService.execute(() -> System.out.println("ThreadPool!!!"));
        Future<String> future = threadPoolService.submit(new MyCallable());
        System.out.println(future.get());
        threadPoolService.shutdown();
    }

}
