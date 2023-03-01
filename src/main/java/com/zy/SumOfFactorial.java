package com.zy;

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
