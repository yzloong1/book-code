package org.zloong.asyn.chp05;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description：弱一致性
 *
 * @Auther:zloong @Date:2020/7/6
 */
public class Code02_Copylist {

    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        arrayList.add("hello");
        arrayList.add("alibaba");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("shandong");

        Thread threadOne = new Thread(() -> {
            arrayList.set(1, "alan");
            arrayList.remove(2);
            arrayList.remove(3);
        });

        // 保证在修改线程启动前获取迭代器
        Iterator<String> itr = arrayList.iterator();

        // 启动线程
        threadOne.start();

        // 等待线程执行完毕
        threadOne.join();

        // 迭代元素
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
