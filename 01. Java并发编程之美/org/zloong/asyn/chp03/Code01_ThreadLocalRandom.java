package org.zloong.asyn.chp03;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description：
 *
 * @Auther:zloong @Date:2020/7/3
 */
public class Code01_ThreadLocalRandom {

    public static void main(String[] args) {

        // 1. 获取一个随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < 10; i++) {

            System.out.print(random.nextInt(5) + " ");
        }
    }
}
