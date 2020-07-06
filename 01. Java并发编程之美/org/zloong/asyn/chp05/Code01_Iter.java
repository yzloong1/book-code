package org.zloong.asyn.chp05;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description：使用迭代器
 *
 * @Auther:zloong @Date:2020/7/6
 */
public class Code01_Iter {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("hello");
        arrayList.add("ali");

        Iterator<String> itr = arrayList.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
