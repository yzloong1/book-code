package org.zloong.asyn.chp05;

import java.util.concurrent.locks.LockSupport;

/**
 * Description：
 *
 * @Auther:zloong @Date:2020/7/6
 */
public class Code03_unpark {

    public static void main(String[] args) {

        System.out.println("begin park!");
        // 使用当前线程获取到许可证
        LockSupport.unpark(Thread.currentThread());

        // 再次调用 park 方法
        LockSupport.park();

        System.out.println("end park!");
    }
}
