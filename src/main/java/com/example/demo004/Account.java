package com.example.demo004;

/**
 * @Description: [java类作用描述]
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 15:21
 */
public class Account {
    /**
     * 锁，保护余额
     */
    private final Object balLock = new Object();
    /**
     * 余额
     */
    private Integer balance;
    /**
     * 锁，保护账户密码
     */
    private final Object pwdLock = new Object();
    /**
     * 密码
     */
    private String password;

    /**
     * 取款
     * @param amt
     */
    void withdraw(Integer amt) {
        synchronized (balLock) {
            if (this.balance > amt) {
                this.balance -= amt;
            }
        }
    }

    /**
     * 查看余额
     * @return
     */
    Integer getBalance() {
        synchronized (balLock) {
            return balance;
        }
    }

    /**
     * 更改密码
     * @param pw
     */
    void updatePassword(String pw) {
        synchronized (pwdLock) {
            this.password = pw;
        }
    }

    /**
     * 查看密码
     * @return
     */
    String getPassword() {
        synchronized (pwdLock) {
            return password;
        }
    }
}
