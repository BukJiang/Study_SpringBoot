package com.buk.utils.concurrent;

import java.util.concurrent.*;

/**
 * TODO: 异步执行服务
 *
 * @author jiangbk
 * @date 2020/11/30
 **/
public class MyExecutorService {

    public static void threadPoolExecutor() {
        // 核心线程池大小
        int corePoolSize = 1;
        // 最大线程池大小
        int maximumPoolSize = 1;
        // 线程最大空闲时间
        long keepAliveTime = 10L;
        // 时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 线程等待队列
//        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
//        BlockingQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();
        BlockingQueue<Runnable> workQueue = null;
        // 线程创建工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };
        // 拒绝策略
        RejectedExecutionHandler handler = null;

        // 线程池执行器
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );
    }

    /**
     * 1. corePoolSize与maximumPoolSize相等，即其线程全为核心线程，是一个固定大小的线程池
     * 2. keepAliveTime=0该参数默认对核心线程无效，而FixedThreadPool全部为核心线程
     * 3. workQueue为LinkedBlockingQueue(无界阻塞队列)，队列最大值为 Integer.MAX_VALUE，如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。因为队列很大，很有可能在拒绝策略前，内存溢出
     * 4. FixedThreadPool的任务执行是无序的
     */
    public static void newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    }

    /**
     * 1. corePoolSize=0，maximumPoolSize= Integer.MAX_VALUE，即线程数量几乎无限制
     * 2. keepAliveTime=60s，线程空闲60s后自动结束
     * 3. workQueue为SynchronousQueue同步队列，这个队列类似于一个接力棒，入队出队必须同时传递，因为CachedThreadPool线程创建无限制，不会有队列等待，所以使用SynchronousQueue
     */
    public static void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }

    /**
     * 1. FixedThreadPool可以向下转型为ThreadPoolExecutor，并对其线程池进行配置，而SingleThreadExecutor被包装后，无法成功向下转型。因此，SingleThreadExecutor被定以后，无法修改，做到了真正的Single
     *
     * @return
     */
    public static ExecutorService newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }


    /**
     * 1. 支持任务周期性调度的线程池
     * 2. 整个调度的核心在于内部类DelayedWorkQueue，一个有序的延时队列
     *
     * @param corePoolSize
     * @return
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }
}
