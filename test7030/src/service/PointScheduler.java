package service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PointScheduler {
    private static ScheduledExecutorService scheduler;
    private static Runnable task = new Runnable() {
        @Override
        public void run() {
            // 포인트를 증가시키는 로직 구현
        }
    };

    public static void startScheduler() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(task, 0, 20, TimeUnit.SECONDS);
        }
    }

    public static void stopScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
}
