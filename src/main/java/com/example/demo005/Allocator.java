package com.example.demo005;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: [java类作用描述]
 * @Author: jiangzhihong
 * @CreateDate: 2020/7/19 16:11
 */
public class Allocator {
    private List<Object> als = new ArrayList<>();

    /**
     * 一次性申请所有的资源，缺点是一直申请一直获取不到，那么就会非常耗cpu
     * @param from
     * @param to
     * @return
     */
    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    /**
     * 一次性申请所有的锁改进版
     * @param from
     * @param to
     */
    synchronized void applySync(Object from , Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                // 等待，这样就不会耗费cpu
                wait();
            } catch (Exception e) {

            }
        }
        als.add(from);
        als.add(to);
    }

    /**
     * 归还资源。缺点是一直申请一直获取不到，那么就会非常耗cpu
     * @param from
     * @param to
     */
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }

    /**
     * 归还资源改进版
     * @param from
     * @param to
     */
    synchronized void freeDev(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        // 唤醒
        notifyAll();
    }
}
