package org.yzl.asyn.chp01;

/**
 * Description：
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code07_SleepTest03 {

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread thread = new Thread(() -> {
            try {
                System.out.println("child thread is in sleep");
                Thread.sleep(10000);
                System.out.println("child thread is in awaked");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        thread.start();
        // 主线程休眠2s
        Thread.sleep(2000);
        // 主线程中断子线程
        thread.interrupt();
    }
}
