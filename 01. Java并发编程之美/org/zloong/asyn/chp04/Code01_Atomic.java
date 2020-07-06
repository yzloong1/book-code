package org.zloong.asyn.chp04;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Description：多线程使用 AtomicLong 统计 0 的个数
 *
 * @Auther:zloong @Date:2020/7/4
 */
public class Code01_Atomic {

    // 创建 Long 型原子计数器
    private static AtomicLong atomicLong = new AtomicLong();

    // 创建数据源tong'ji
    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {

        // 线程 one 统计数组 arrayOne 中0的个数
        Thread threadOne = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; i++) {
                if (arrayOne[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        // 线程 two 统计数组 arrayTwo 中0的个数
        Thread threadTwo = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; i++) {

                if (arrayTwo[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        // 启动子线程
        threadOne.start();
        threadTwo.start();

        // 等待线程执行完毕
        threadOne.join();
        threadTwo.join();

        System.out.println("count 0: " + atomicLong.get());
    }

}
