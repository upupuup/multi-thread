package com.example.demo001;

/**
 * @Description: [java类作用描述]
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 15:08
 */
public class Test {
    private Long count = new Long(0);
    private String str = "0";

    private void add10k(Long count) {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
            str = "5";
        }
        System.out.println("----" + count);
        System.out.println(str + "====");
    }

    private void setUser(User user) {
        user.setAge(11);
        user.setName("xiaoming");
    }

    public long calc() throws Exception {
        final Test test = new Test();
        Thread thread1 = new Thread(() -> test.add10k(count));
        Thread thread2 = new Thread(() -> test.add10k(count));

        // 启动线程
        thread1.start();
        thread2.start();
        // 等待两个线程执行结束
        thread1.join();
        thread2.join();
        test.add10k(count);
        User user = new User();
        user.setName("iii");
        user.setAge(1);
        System.out.println(user.toString());
        test.setUser(user);
        System.out.println(count);
        System.out.println("[[" + str);
        System.out.println(user.toString());
        return count;
    }
}
