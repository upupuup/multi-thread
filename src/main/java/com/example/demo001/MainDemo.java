package com.example.demo001;

/**
 * @Description: [java类作用描述]
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 15:13
 */
public class MainDemo {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        System.out.println(test.calc());

        int i = 17;
        i |= i >>> 1;
        i |= i >>> 2;
        i |= i >>> 4;
        i |= i >>> 8;
        i |= i >>> 16;
        i |= i >>> 32;
        System.out.println(i);
    }
}
