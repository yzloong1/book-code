package org.zloong.asyn.chp01;

/**
 * Description：根据中断标志判断线程是否终止
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code09_InterruptTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + "hello");
            }
        });

        // 1. 启动子线程
        thread.start();

        // 主线程休眠1s，以便中断前让子线程输出
        Thread.sleep(1000);

        // 中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();

        // 等待子线程执行完毕
        thread.join();
        System.out.println("main is over");
    }
}

