package jp.co.nttdata.myapp.utils.thread;


import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author  DavidLei08.
 * @version  1.0.
 * 线程池操作工具类.
 */
public interface ThreadPoolUtilInterface {
    /** 核心线程数*/
    int corePoolSize = 5;
    /** 最大线程总数*/
    int maximumPoolSize = 20;
    /** 非核心线程数闲置超时时长*/
     long keepAliveTime = 1000;
    /** keepAliveTime的单位*/
     TimeUnit unit = TimeUnit.MILLISECONDS;
    /** 线程池中的任务队列*/
      BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
      /** 定义全局的线程池*/
     ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
              maximumPoolSize,keepAliveTime,unit,workQueue);

    /**
     * 将独立的线程放入线程池.
     * @param task 线程任务.
     */
     void pushTaskToThreadPool(Runnable task);

    /**
     * 将独立的方法放入线程线程池.
     * @param method 方法.
     * @param methodArgs 方法入参对象数组.
     */
    void pushMethodToThreadPool(Method method, Object methodArgs[]);

    /**
     * 将独立的方法装入线程.
     * @param method 方法.
     * @param methodArgs 方法入参对象数组.
     * @return 线程对象.
     */
    Runnable getMethodRunnable(Method method, Object methodArgs[]);

}
