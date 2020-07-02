package org.yzl.asyn.chp01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：让线程睡眠的sleep方法
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code06_SleepTest02 {

    // 创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        // 创建线程A
        Thread threadA = new Thread(() -> {

            // 获取独占锁
            lock.lock();
            try {
                System.out.println("child threadA is in sleep");
                Thread.sleep(10000);
                System.out.println("child threadA is in awaked");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        // 创建线程B
        Thread threadB = new Thread(() -> {
            // 获取独占锁
            lock.lock();
            try {
                System.out.println("child threadB is in sleep");
                Thread.sleep(10000);
                System.out.println("child threadB is in awaked");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();
    }
}
