package org.yzl.asyn.chp02;

/**
 * Description：同步编程模型下，一个线程要做两件事情
 *
 * @Auther:zloong @Date:2020/6/28
 */
public class Code02_SyncExample {

    public static void doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA ---");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- soSomethingB ---");
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 1. 开启异步单元执行任务A
        Thread thread = new Thread(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "threadA");
        thread.start();

        // 2. 执行任务B
        doSomethingB();

        // 3. 同步等待线程A运行结束
        thread.join();
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
