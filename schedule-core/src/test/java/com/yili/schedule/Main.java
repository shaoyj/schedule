package com.yili.schedule;

import com.yili.schedule.core.ScheduleSpringFactroy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lancey on 16/3/5.
 */
public class Main {

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(1);
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringBeans.class);

        final ScheduleSpringFactroy factroy = context.getBean(ScheduleSpringFactroy.class);

        Runtime.getRuntime().addShutdownHook(new Thread(){

            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p/>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             * @see #Thread(ThreadGroup, Runnable, String)
             */
            @Override
            public void run() {
                factroy.stop();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
