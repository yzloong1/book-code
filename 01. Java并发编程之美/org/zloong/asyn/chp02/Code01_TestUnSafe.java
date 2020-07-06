package org.zloong.asyn.chp02;

import sun.misc.Unsafe;

/**
 * Description：使用UnSafe类
 *
 * @Auther:zloong @Date:2020/7/3
 */
public class Code01_TestUnSafe {

    // 获取UnSafe的实例
    static final Unsafe unsafe = Unsafe.getUnsafe();

    // 记录变量 state 在类 Code01_TestUnSafe 中的偏移值
    static final long stateOffset;

    // 变量
    private volatile long state = 0;

    static {

        try {
            // 获取 state 变量在 Code01_TestUnSafe 中的偏移值
            stateOffset = unsafe.objectFieldOffset(Code01_TestUnSafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {

        // 创建实例, 并且设置 state 值为1
        Code01_TestUnSafe test = new Code01_TestUnSafe();

        Boolean sucess = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(sucess);
    }
}
