package org.zloong.asyn.chp01;

/**
 * Description：一个线程调用了Thread.sleep(3000)，那么调用线程会被阻塞，直到3s后才会从阻塞状态变为激活状态。
 * 但是有可能在3s内已被满足，这个时候可以调用该线程的interrupt()，强制sleep方法InterruptedException异常而返回，线程恢复到激活状态
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code10_Interrupt01Test {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(() -> {
            try {
                System.out.println("threadOne begin sleep for 2000 seconds");
                Thread.sleep(20000000);
                System.out.println("threadOne awaking");
            } catch (Exception e) {
                System.out.println("threadOne is interrupted while sleeping");
                return;
            }
            System.out.println("threadOne-leaving normally");
        });

        // 启动线程
        threadOne.start();

        // 确保子线程进入休眠状态
        Thread.sleep(1000);

        // 打断子线程的休眠，让子线程从sleep函数返回
        threadOne.interrupt();

        // 等待子线程执行完毕
        threadOne.join();

        System.out.println("main thread is over");
    }
}
