package org.yzl.asyn.chp01;

/**
 * Description：让出 CPU 执行权的 yield 方法
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code08_YieldTest implements Runnable {

    public Code08_YieldTest() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            // 当i=0时，让出CPU执行权，放弃时间片，进行下一轮调度
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread() + "yield cpu");
                //当前线程让出 CPU 执行权，放弃时间片，进行下一轮调度
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String[] args) {
        new Code08_YieldTest();
        new Code08_YieldTest();
        new Code08_YieldTest();
    }
}
