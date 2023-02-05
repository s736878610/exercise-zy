package com.zy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test
 *
 * @author zhongyuan
 * @since 2023/1/22
 */
public class Test {

    public static void main(String[] args) {
        // 拷贝数组 测试代码
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        int size = list.size();

        Object[] objects = Arrays.copyOf(list.toArray(), size + 1);

        System.out.println("end");


        // retry 测试代码
        retry:
        for (int i = 0; i < 10; i++) {

            System.out.println("i=" + i);

            for (int j = 0; j < 10; j++) {

                System.out.println("j=" + j);

                if (j == 1) {
                    // 结束当前循环，同时结束retry处的循环
                    break retry;
                }

                if (j == 2) {
                    // 结束当前循环，从retry处继续循环
                    continue retry;
                }
            }
        }
        System.out.println("end");
    }
}
