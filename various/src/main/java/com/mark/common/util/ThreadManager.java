package com.mark.common.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Marks
 * @date 2018/7/20 14:18
 * email: mottoboy@126.com
 * describe:
 * <li>线程池管理</li>
 */
public class ThreadManager {

    public static ThreadManager manager = new ThreadManager();
    private ThreadPool threadpool;

    public static ThreadManager getInstance() {
        return manager;
    }

    public synchronized ThreadPool getThreadPool() {
        
        if (threadpool == null) {
            threadpool = new ThreadPool(10, 10, 100L);
        }
        return threadpool;
    }

    public class ThreadPool {
        private ThreadPoolExecutor pool;

        ThreadPool(int corePoolSize, int maximumPoolSize, long time) {
            if (pool == null) {
                // 创建线程池
                /*
                 * 1. 线程池里面管理多少个线程2. 如果排队满了, 额外的开的线程数3. 如果线程池没有要执行的任务 存活多久4.
                 * 时间的单位 5 如果 线程池里管理的线程都已经用了,剩下的任务 临时存到LinkedBlockingQueue对象中 排队
                 */

                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                        time, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(10));
            }
        }

        /**
         * 执行任务
         *
         * @param
         */
        public void execute(Runnable runnable) {
            // 调用线程池 执行异步任务
            pool.execute(runnable);
        }

        /**
         * 取消任务
         *
         * @param runnable
         */
        public void cancel(Runnable runnable) {
            if (pool != null && !pool.isShutdown() && !pool.isTerminated()) {
                LogUtil.e("AA","取消了线程");
                // 取消异步任务
                pool.remove(runnable);
            }
        }
    }
}
