package com.zy.algorithm;

/**
 * SumOfFactorial
 * <p>
 * 将1~N的阶乘相加
 * 1!+2!+3!+4!...+N!
 *
 * @author zhongyuan
 * @since 2023/3/1
 */
public class SumOfFactorial {

    /**
     * 方法1 一个for循环加阶乘的结果 另一个for循环求阶乘
     */
    public static long f1(int n) {
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += factorial(i);
        }
        return ans;
    }

    /**
     * 求n的阶乘
     */
    public static long factorial(int n) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

    /**
     * 方法2 用一个临时变量存阶乘的结果 然后在一个for循环完成求阶乘以及结果相加
     */
    public static long f2(int n) {
        long temp = 1;
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            temp *= i;
            ans += temp;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(f1(n));
        System.out.println(f2(n));
    }

}
