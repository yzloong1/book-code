package org.zloong.asyn.chp01;

/**
 * Description：TheadLocal 是 JDK 包提供的，它提供了线程本地变量。
 * 如果你创建了一个 ThreadLocal 变量，那么访问这个变量的每个线程都会有这个变量的一个本地副本。
 * 当多个线程操作这个变量时，实际操作的是自己本地内存里面的变量，从而避免了线程安全问题。
 *
 * @Auther:zloong @Date:2020/7/3
 */
public class Code14_ThreadLocalTest {

    // 1. print 函数
    static void print(String str) {
        // 1.1 打印当前线程本地内存中的 localVariable 变量的值
        System.out.println(str + " : " + localVariable.get());
        // 1.2 清除当前线程本地内存中的 localVariable 变量
        localVariable.remove();
    }

    // 2. 创建 ThreadLocal 变量
    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {

        // 3. 创建线程one
        Thread threadOne = new Thread(() -> {
            // 3.1 设置线程One中本地变量 localVariable 的值
            localVariable.set("threadOne local variable");
            // 3.2 调用打印函数
            print("threadOne");
            // 3.3 打印本地变量值
            System.out.println("threadOne remove after" + ":" + localVariable.get());
        });

        // 4. 创建线程two
        Thread threadTwo = new Thread(() -> {
            // 4.1 设置线程 two 中本地变量 localVariable 的值
            localVariable.set("threadTwo local variable");
            // 4.2 调用打印函数
            print("threadTwo");
            // 4.3 打印本地变量值
            System.out.println("threadTwo remove after" + ":" + localVariable.get());
        });

        // 5. 启动线程
        threadOne.start();
        threadTwo.start();
    }
}
