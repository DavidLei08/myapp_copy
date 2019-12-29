package jp.co.nttdata.myapp.utils.thread;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ThreadPoolUtilmpl  implements  ThreadPoolUtilInterface{
    /**
     * 将独立的线程放入线程池.
     * @param task 线程任务.
     */
    public  void pushTaskToThreadPool(Runnable task){
        //线程放入线程池
        threadPoolExecutor.submit(task);

    }
    /**
     * 将独立的方法放入线程线程池.
     * @param method 方法.
     * @param methodArgs 方法入参对象数组.
     */
    public void pushMethodToThreadPool(Method method, Object methodArgs[]){
        //将方法放入线程，在把线程放入线程池
        threadPoolExecutor.submit(getMethodRunnable(method,methodArgs));
    }
    /**
     * 将独立的方法装入线程.
     * @param method 方法.
     * @param methodArgs 方法入参对象数组.
     * @return 线程对象.
     */
    public Runnable getMethodRunnable(Method method, Object methodArgs[]){
        //定义线程.
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    //执行方法.
                    method.invoke(null, methodArgs);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        return  runnable;
    }
}
