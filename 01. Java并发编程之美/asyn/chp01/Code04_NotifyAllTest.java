package org.yzl.asyn.chp01;

/**
 * Description：
 *
 * @Auther:zloong @Date:2020/7/2
 */
public class Code04_NotifyAllTest {

    // 创建资源
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {

        // 创建线程 threadA
        Thread threadA = new Thread(() -> {
            // 获取 resourceA 的资源监视器
            synchronized (resourceA) {
                System.out.println("threadA get resourceA lock");
                try {
                    System.out.println("threadA begin await");
                    resourceA.wait();
                    System.out.println("threadA end wait");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建线程 threadA
        Thread threadB = new Thread(() -> {
            // 获取 resourceB 的资源监视器
            synchronized (resourceA) {
                System.out.println("threadB get resouceA lock");
                try {
                    System.out.println("threadB begin wait");
                    resourceA.wait();
                    System.out.println("threadB end wait");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建线程 threadC
        Thread threadC = new Thread(() -> {

            synchronized (resourceA) {
                System.out.println("threadC begin notify");
                resourceA.notify();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        Thread.sleep(1000);
        threadC.start();

        // 等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");
    }
}
