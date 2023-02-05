package com.zy.juc.pool;

import com.zy.constants.Constants;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPoolTest
 *
 * @author zhongyuan
 * @since 2023/2/3
 */
public class ForkJoinPoolTest {

    static int[] nums = new int[Constants.ONE_MILLION];
    static final int MAX_NUM = 50000;
    static Random random = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        System.out.println("初始化完成 nums总数=" + Arrays.stream(nums).sum());
    }

    /**
     * 无返回值类型 ForkJoin任务类
     */
    static class AddTask extends RecursiveAction {

        int start, end;

        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {

            // 分片小于最大值 开始计算
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("index from:" + start + " to:" + end + "=" + sum);
            }
            // 分片大于最大值 继续切割
            else {
                int middle = start + (end - start) / 2;
                AddTask task1 = new AddTask(start, middle);
                AddTask task2 = new AddTask(middle, end);
                task1.fork();
                task2.fork();
            }

        }
    }

    /**
     * 有返回值类型 ForkJoin任务类
     */
    static class AddTaskRet extends RecursiveTask<Long> {

        int start, end;

        AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {

            // 分片小于最大值 开始计算
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                // System.out.println("index from:" + start + " to:" + end + "=" + sum);
                return sum;
            }
            // 分片大于最大值 继续切割
            else {
                int middle = start + (end - start) / 2;
                AddTaskRet task1 = new AddTaskRet(start, middle);
                AddTaskRet task2 = new AddTaskRet(middle, end);
                task1.fork();
                task2.fork();
                // 计算后的值汇总
                return task1.join() + task2.join();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // AddTask addTask = new AddTask(0, nums.length);
        // forkJoinPool.execute(addTask);
        // System.in.read();

        AddTaskRet addTaskRet = new AddTaskRet(0, nums.length);
        forkJoinPool.execute(addTaskRet);
        System.out.println("ForkJoinTask 计算结果=" + addTaskRet.join());
    }

}
