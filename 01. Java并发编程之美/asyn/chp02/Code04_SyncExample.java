package org.yzl.asyn.chp02;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description：同步编程模型下，一个线程要做两件事情
 * 线程池的方式
 *
 * @Auther:zloong @Date:2020/6/28
 */
public class Code04_SyncExample {

    public static void doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA ---");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- soSomethingB ---");
    }

    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    // 设置线程池核心线程个数为当前物理机的CPU核数，最大线程个数为当前物理机CPU核数的2倍
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5));

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 1. 开启异步单元执行任务A
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 3. 同步等待线程A运行结束
        System.out.println(System.currentTimeMillis() - start + "ms");

        // 4. 挂起当前线程
        Thread.currentThread().join();
    }
}
