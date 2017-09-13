package com.syj.schedule;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
@SpringBootApplication
public class LocalSpringApplication {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch downLatch = new CountDownLatch(1);
        new SpringApplicationBuilder(LocalSpringApplication.class).web(true).registerShutdownHook(true).run(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                downLatch.countDown();
            }
        });
        downLatch.await();
    }
}
