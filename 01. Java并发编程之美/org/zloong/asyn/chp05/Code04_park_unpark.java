package org.zloong.asyn.chp05;

import java.util.concurrent.locks.LockSupport;

/**
 * Description：
 *
 * @Auther:zloong @Date:2020/7/6
 */
public class Code04_park_unpark {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park!");

            // 调用 park 方法，挂起自己
            LockSupport.park();

            System.out.println("child thread unpark");
        });

        // 启动子线程
        thread.start();

        // 主线程休眠 1s
        Thread.sleep(1000);

        System.out.println("main thread begin unpark");

        // 调用 unpark 方法让 thread 线程持有许可证，然后 park 方法返回
        LockSupport.unpark(thread);
    }
}
