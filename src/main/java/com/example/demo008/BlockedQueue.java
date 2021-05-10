package com.example.demo008;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: [java类作用描述]
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 17:33
 */
public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();

    /**
     * 条件变量：队列不满
     */
    final Condition notFull = lock.newCondition();

    /**
     * 条件变量：队列不空
     */
    final Condition notEmpty = lock.newCondition();

    /**
     * 入队
     * @param x
     */
//    void enq(T x) throws Exception {
//        lock.lock();
//        try {
//            // 队列已满
//            while (队列已满) {
//                // 等待队列不满
//                notFull.await();
//            }
//            // 省略入队操作，入队之后，通知可以出队
//            notEmpty.signal();
//        } finally {
//            lock.unlock();
//        }
//    }

    /**
     * 出队。
     * 在try代码块之前调用Lock实现类的lock()方法，避免由于加锁失败，导致finally调用unlock()抛出异常。
     */
//    void deq() throws Exception {
//        lock.lock();
//        try {
//            while (队列已空) {
//                notEmpty.await();
//            }
//            // 省略出队操作，出队后，通知可入队
//            notFull.signal();
//        } finally {
//            lock.unlock();
//        }
//    }

}
