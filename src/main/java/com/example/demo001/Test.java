package com.example.demo001;

/**
 * @Description: [java类作用描述]
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 15:08
 */
public class Test {
    private long count = 0;

    private void add10k() {
        int index = 0;
        while(index++ < 10000) {
            count += 1;
        }
    }

    public long calc() throws Exception {
        final Test test = new Test();
        Thread thread1 = new Thread(() -> test.add10k());
        Thread thread2 = new Thread(() -> test.add10k());

        // 启动线程
        thread1.start();
        thread2.start();
        // 等待两个线程执行结束
        thread1.join();
        thread2.join();
        return count;
    }
}
