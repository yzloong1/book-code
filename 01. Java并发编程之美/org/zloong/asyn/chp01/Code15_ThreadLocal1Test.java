package org.zloong.asyn.chp01;

/**
 * Description：ThreadLocal 不支持继承性
 *
 * @Auther:zloong @Date:2020/7/3
 */
public class Code15_ThreadLocal1Test {

    // 1. 创建线程变量
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {

        // 2. 设置线程变量
        threadLocal.set("hello, threadLocal");
        // 3. 启动子线程
        Thread thread = new Thread(() -> {
            // 4. 子线程输出线程变量的值
            System.out.println("thread: " + threadLocal.get());
        });
        thread.start();
        // 5. 主线程输出线程变量的值
        System.out.println("main: " + threadLocal.get());
    }
}
