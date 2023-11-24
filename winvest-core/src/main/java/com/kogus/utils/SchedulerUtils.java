package com.kogus.utils;

public class SchedulerUtils {
    public static void runTaskAsync(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void runTask(Runnable runnable) {
        try {
            Thread t = new Thread(runnable);
            t.start();
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
