package org.zloong.asyn.chp02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Description：利用反射使用UnSafe类
 *
 * @Auther:zloong @Date:2020/7/3
 */
public class Code02_TestUnSafe_reflect {

    static final Unsafe unsafe;

    static final long stateOffset;

    private volatile long state = 0;

    static {

        try {

            // 使用反射获取 Unsafe 的成员变量 theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");

            // 设置为可存取
            field.setAccessible(true);

            // 获取该变量的值
            unsafe = (Unsafe) field.get(null);

            // 获取 state 在 Code02_TestUnSafe_reflect 中的偏移量
            stateOffset = unsafe.objectFieldOffset(Code02_TestUnSafe_reflect.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {

        Code02_TestUnSafe_reflect test = new Code02_TestUnSafe_reflect();
        Boolean sucess = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(sucess);
    }
}
