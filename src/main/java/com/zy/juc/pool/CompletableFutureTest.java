package com.zy.juc.pool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureTest
 *
 * @author zhongyuan
 * @since 2023/1/30
 */
public class CompletableFutureTest {

    // 	1.与第一条线程相关
    // 		Run  没有返回值，参数是rannable
    // 		Supply  有返回值，参数是supplier
    //
    // 	2.依赖其他线程的线程
    // 		Then
    // 			Accept  没有返回值
    // 			Apply   有返回值
    //
    // 	3.同步和异步
    // 		Async 异步
    // 		Sync 同步
    // 			有包含Async的是异步，没有包含Async的是同步
    //
    // 	4.完成或者异常处理
    // 		whenComplete 线程执行后后置处理
    // 		completeExceptionally 线程的出异常处理
    //
    // 	5.线程执行组合方式
    // 		AnyOf 任意线程执行完成后，主线程即可跳出
    // 		AllOf 所有线程执行完成后，主线程可以跳出

    public static void main(String[] args) throws Exception {
        long start, end;

        start = System.currentTimeMillis();
        priceOfTm();
        priceOfTb();
        priceOfJd();
        end = System.currentTimeMillis();
        System.out.println("同步调用耗时：" + (end - start));

        start = System.currentTimeMillis();
        CompletableFuture<Double> futureTm = CompletableFuture.supplyAsync(CompletableFutureTest::priceOfTm);
        CompletableFuture<Double> futureTb = CompletableFuture.supplyAsync(CompletableFutureTest::priceOfTb);
        CompletableFuture<Double> futureJd = CompletableFuture.supplyAsync(CompletableFutureTest::priceOfJd);
        CompletableFuture.allOf(futureTm, futureTb, futureJd).join();
        end = System.currentTimeMillis();
        System.out.println("异步编排调用耗时：" + (end - start));

        CompletableFuture.supplyAsync(CompletableFutureTest::priceOfTm)
                .thenApply(String::valueOf)
                .thenApply(priceOfStr -> "price " + priceOfStr)
                .thenAccept(System.out::println);

        System.in.read();
    }

    /**
     * 模拟查询天猫的价格
     */
    private static double priceOfTm() {
        delay();
        return 1.00;
    }

    /**
     * 模拟查询淘宝的价格
     */
    private static double priceOfTb() {
        delay();
        return 2.00;
    }

    /**
     * 模拟查询京东的价格
     */
    private static double priceOfJd() {
        delay();
        return 5.00;
    }

    /**
     * 延时 模拟联网查询耗时
     */
    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.printf("查询耗时：%s%n", time);
    }

}
