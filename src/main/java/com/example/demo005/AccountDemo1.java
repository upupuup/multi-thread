package com.example.demo005;

import com.example.demo004.Account;
import com.example.demo004.AccountDemo2;

/**
 * @Description: [java类作用描述]
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 16:02
 */
public class AccountDemo1 {
    private int id;
    private int balance;

    /**
     * 解决demo004的问题（锁的是Account.class），但是有一个问题是这个方法会有死锁的可能。
     * 这就是使用细粒度锁的缺点
     * @param target
     * @param amt
     */
    void transfer(AccountDemo1 target, int amt) {
        synchronized (this) {
            synchronized (target) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    /**
     * 改进方法就是一个线程一次性申请到所有的锁。也就是破坏占用且等待条件。
     * 缺点是一直循环去获取锁，如果并发量很大的话，那么就会消耗CPU。
     * 最好的方式是，如果线程的要求不满足，那么就阻塞自己，进入等待状态。
     * 当线程要求的条件满足之后，通知等待的线程重新执行。
     * @param target
     * @param amt
     */
    private Allocator actr;
    void transferDev(AccountDemo1 target, int amt) {
        // 一次性获取所有的锁
        while (!actr.apply(this, target)) {

        }

        try {
            synchronized (this) {
                synchronized (target) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }

    /**
     * 改进方法就是对资源排序，比如根据id排序，申请的时候按照从小到大来申请。也就是破坏循环等待条件。
     * @param target
     * @param amt
     */
    void transferDev2(AccountDemo1 target, int amt) {
        AccountDemo1 left = this;
        AccountDemo1 right = target;

        if (this.id > target.id) {
            left = target;
            right = this;
        }

        // 锁定序号小的账户
        synchronized (left) {
            // 锁定序号大的账户
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
