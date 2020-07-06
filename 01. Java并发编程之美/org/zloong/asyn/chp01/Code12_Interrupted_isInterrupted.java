package org.zloong.asyn.chp01;

/**
 * Description：interrupted() 与 isInterrupted() 方法的不同
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code12_Interrupted_isInterrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(() -> {
            // 中断标志为true时会退出循环，并且清除中断标志
            while (!Thread.currentThread().interrupted()) {

            }
            System.out.println("threadOne isInterrupted: " + Thread.currentThread().isInterrupted());
        });

        // 启动线程
        threadOne.start();

        // 设置中断标志
        threadOne.interrupt();

        threadOne.join();

        System.out.println("main thread is over");

    }
}
