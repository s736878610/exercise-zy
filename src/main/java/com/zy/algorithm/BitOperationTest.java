package com.zy.algorithm;

/**
 * BitOperationTest
 * <p>
 * 位运算测试
 *
 * @author zhongyuan
 * @since 2023/2/18
 */
public class BitOperationTest {

    public static void main(String[] args) {

        // // int整形 32位存储
        // int num = 2;
        // // 打印二进制码
        // print(num);

        // // 左移
        // int i = 1;
        // print(i);
        // print(i << 1);

        // // 右移
        // int i = Integer.MIN_VALUE;
        // print(i);
        // // 带符号右移
        // print(i >> 1);
        // // 不带符号右移
        // print(i >>> 1);

        // System.out.print("Integer最大值：");
        // print(Integer.MAX_VALUE);
        // System.out.print("Integer最小值：");
        // print(Integer.MIN_VALUE);

        // 一个数的正负值怎么写
        int a = 5;
        int b = -a;
        // 取反+1 也可以表达负数
        int c = (~a + 1);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        // // 正数 第32位是0 后面31位用来表示数值
        // int c = 5;
        // print(c);
        // // 负数 第32位是1 后面31位取反+1来表示数值
        // int d = -5;
        // print(d);
        // // 为什么负数要这么设计? 因为代码中的 + - * / 是用 << & | ^ ~ 实现的 这么设计在做数字运算时只用写一套逻辑

        // ~ 取反
        // int a = 4789327;
        // int b = ~a;
        // print(a);
        // print(b);

        // & 与 (都为1取1)
        // int a = 50;
        // int b = 60;
        // print(a);
        // print(b);
        // System.out.println(Constants.SEPARATOR);
        // print(a & b);

        // | 或 (有1取1)
        // int a = 50;
        // int b = 60;
        // print(a);
        // print(b);
        // System.out.println(Constants.SEPARATOR);
        // print(a | b);

        // ^ 异或 (相同取0 不同取1)
        // int a = 50;
        // int b = 60;
        // print(a);
        // print(b);
        // System.out.println(Constants.SEPARATOR);
        // print(a ^ b);
    }

    /**
     * 打印int数二进制码
     */
    private static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

}
