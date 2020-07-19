package com.example.demo004;

/**
 * @Description: 保护有关联的多个资源
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 15:31
 */
public class AccountDemo2 {
    private int balance;

    /**
     * 转账，这个转账有问题，没有加锁。
     *
     * @param target
     * @param amt
     */
    void transfer(AccountDemo2 target, int amt) {
        if (this.balance > amt) {
            this.balance -= amt;
            target.balance += amt;
        }
    }

    /**
     * 转账，这个转账有问题，问题在于就是锁的是this，但是他要保护的是当前人的账户和别人的账户。
     * 不能用自己家的锁保护别人家的资产。不能保证这个时候别人没有转账的操作。
     * @param target
     * @param amt
     */
    synchronized void transferSyn(AccountDemo2 target, int amt) {
        if (this.balance > amt) {
            this.balance -= amt;
            target.balance += amt;
        }
    }

    /**
     * 改进方法一，this是对象级别的锁，AccountDemo2.class是类级别的锁，
     * 在Java虚拟机在加载Account的时候创建的，所以不用担心唯一性。但是这样的话，
     * 所有的转账方法都变成了串行操作。demo005中优化
     * @param target
     * @param amt
     */
    void transferSynDev(AccountDemo2 target, int amt) {
        // 类级别的锁
        synchronized (AccountDemo2.class) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }

}
