package org.zloong.asyn.chp01;

/**
 * Description：interrupted() 与 isInterrupted() 方法的不同
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code11_Interrupted_isInterrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(() -> {
            for (; ; ) {

            }
        });

        // 启动线程
        threadOne.start();

        // 设置中断标志
        threadOne.interrupt();

        // 获取中断标志
        System.out.println("isInterrupted：" + threadOne.isInterrupted());

        // 获取 (当前线程的) 中断标志并重置
        System.out.println("isInterrupted: " + threadOne.interrupted());

        // 获取 (当前线程的) 中断标志并重置
        System.out.println("isInterrupted: " + Thread.interrupted());

        // 获取中断标志
        System.out.println("isInterrupted: " + threadOne.isInterrupted());

        threadOne.join();

        System.out.println("main thread is over");
    }
}
