package org.zloong.asyn.chp01;

/**
 * Description：
 * 当前线程调用共享变量的wait()方法后只会释放当前共享变量上的锁，如果当前线程还持有其他共享变量的锁，则这些锁是不会释放的。
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code02_WaitNotifyTest {

    // 创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取 resourceA 共享资源的监视器锁
                    synchronized (resourceA) {

                        System.out.println("threadA get resourceA lock");
                        // 获取 resourceB 共享资源的监视器锁
                        synchronized (resourceB) {

                            System.out.println("threadA get resourceB lock");
                            // 线程A阻塞，并释放获取到的 resouceA 的锁
                            resourceA.wait();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 休眠1s
                    Thread.sleep(1000);
                    // 获取 resourceA 共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB try get resourceB lock...");

                        // 获取 resourceB 共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.println("threadB get resourceB lock");

                            // 线程B阻塞，并释放获得到的 resourceA 的锁
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待两个线程执行结束
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }
}
