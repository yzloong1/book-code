package org.zloong.asyn.chp01;

/**
 * Description：Thread 类的 join 方法。
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code05_JoinTest {

    public static void main(String[] args) {

        // 线程 one
        Thread threadOne = new Thread(() -> {

            System.out.println("threadOne begin run!");
            for (; ; ) {

            }
        });

        // 获取主线程
        final Thread mainThread = Thread.currentThread();

        // 线程 two
        Thread threadTwo = new Thread(() -> {
            // 休眠 1s
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 中断主线程
            mainThread.interrupt();
        });

        // 启动子线程
        threadOne.start();
        // 延迟1s启动线程
        threadTwo.start();
        try{
            // 等待线程 one 执行结束
            threadOne.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
