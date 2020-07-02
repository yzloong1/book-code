package org.yzl.asyn.chp01;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Description：FutureTask： 有返回值的线程创建方式
 *
 * @Auther:zloong @Date:2020/7/1
 */
public class Code01_FutureTask {

    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) {
        // 1. 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        // 2. 启动线程
        new Thread(futureTask).start();
        try {
            // 3. 等待任务执行完毕，并返回结果
            String result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
