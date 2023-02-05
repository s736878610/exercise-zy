package com.zy.juc.pool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WorkStealingPoolTest
 * <p>
 * 每个线程有单独的队列，自己队列里的任务完成后，会去偷其他线程队列的任务
 *
 * @author zhongyuan
 * @since 2023/2/4
 */
public class WorkStealingPoolTest {

    public static void main(String[] args) throws IOException {
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        workStealingPool.execute(new Run(1000));
        workStealingPool.execute(new Run(2000));
        workStealingPool.execute(new Run(2000));
        workStealingPool.execute(new Run(2000));
        workStealingPool.execute(new Run(2000));

        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }

    static class Run implements Runnable {

        int time;

        Run(int time) {
            this.time = time;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time + " " + Thread.currentThread().getName());

        }
    }

}
