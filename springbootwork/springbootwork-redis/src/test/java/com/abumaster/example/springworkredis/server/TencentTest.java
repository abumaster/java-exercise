package com.abumaster.example.springworkredis.server;

/**
 * 功能详细描述
 * 有一个N阶的楼梯，每一步只能走一阶或者两阶；
 * 求从1阶到走到N阶一共可能的走法有多少种？
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/9
 */
public class TencentTest {

    public static int solve(int n) {
        if (n<=1) return 1;
        if (n==2) return 2;
        return solve(n-1)+solve(n-2);
    }
    public static void main(String[] args) {
        System.out.println(solve(3));
        System.out.println(solve(10));
    }
}
