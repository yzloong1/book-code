package org.yzl.asyn.chp02;

import java.util.concurrent.*;

/**
 * Description：
 *
 * @Auther:zloong @Date:2020/6/29
 */
public class Code05_AsyncThreadPoolExample {

    public static String doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA ---");
        return "A Task Done";
    }

    // 0自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
            new NamedThreadFactory("ASYNC-POOL"), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1.开启异步单元执行任务A
        Future<?> resultA = POOL_EXECUTOR.submit(() -> doSomethingA());

        // 2.同步等待执行结果
        System.out.println(resultA.get());
    }
}
