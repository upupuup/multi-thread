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
     * 一次性申请所有的资源
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
     * 归还资源
     * @param from
     * @param to
     */
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }
}
