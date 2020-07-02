package org.yzl.asyn.chp02;

import java.util.concurrent.*;

/**
 * Description：FutureTask 提交线程池执行
 *
 * @Auther:zloong @Date:2020/6/30
 */
public class Code07_AsyncFutureExample {

    public static String doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");
        return "TaskAResult";
    }

    public static String doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB---");
        return "TaskBResult";
    }

    // 0自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        // 1. 创建future任务
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            String result = null;
            try {
                result = doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 2. 开启异步单元执行任务A
        POOL_EXECUTOR.execute(futureTask);

        // 3. 执行任务B
        String taskBResult = doSomethingB();

        // 4. 同步等待线程A运行结束
        String taskAResult = futureTask.get();

        // 5. 打印两个任务执行结果
        System.out.println(taskAResult + "" + taskBResult);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
