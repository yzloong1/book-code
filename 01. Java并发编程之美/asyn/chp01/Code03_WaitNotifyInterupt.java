package org.yzl.asyn.chp01;

/**
 * Description：当一个线程调用共享对象的 wait()方法被阻塞挂起后，如果其他线程中断了该线程，则该线程会抛出 InterruptedException 异常并返回。
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code03_WaitNotifyInterupt {

    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

        // 创建线程
        Thread threadA = new Thread(() -> {
            try {
                // 创建线程
                System.out.println("begin");
                // 阻塞当前线程
                synchronized (obj) {
                    obj.wait();
                }
                System.out.println("end");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        Thread.sleep(1000);

        System.out.println("---begin interrupt threadA---");
        threadA.interrupt();
        System.out.println("---end interrupt threadA---");
    }
}
