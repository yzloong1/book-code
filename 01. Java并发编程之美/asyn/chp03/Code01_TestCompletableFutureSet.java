package org.yzl.asyn.chp03;

import java.util.concurrent.*;

/**
 * Description：CompletableFuture是一种可以通过编程显式设置结果的future
 *
 * @Auther:zloong @Date:2020/6/30
 */
public class Code01_TestCompletableFutureSet {

    // 0 自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 创建一个CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<String>();

        // 2. 开启线程计算任务结果，并设置
        POOL_EXECUTOR.execute(() -> {
            // 2.1 休眠3s，模拟任务计算
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 2.2 设置计算结果到future
            System.out.println("----" + Thread.currentThread().getName() + " set future result----");
            future.complete("hello, ji");
        });

        // 3. 等待计算结果
        System.out.println("---main thread wait future result---");
        System.out.println(future.get());
        System.out.println("---main thread got future result---");
    }
}
