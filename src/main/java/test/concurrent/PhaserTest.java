package test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

class PhaserTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Phaser ph = new Phaser(1);

        executorService.submit(new LongRunningAction("thread-1", ph));
        executorService.submit(new LongRunningAction("thread-2", ph));
        executorService.submit(new LongRunningAction("thread-3", ph));

        System.out.println("awaiting: main(" + ph.getPhase() + ")");
        ph.arriveAndAwaitAdvance();
        System.out.println("awaited: main(" + ph.getPhase() + ")");

        executorService.submit(new LongRunningAction("thread-4", ph));
        executorService.submit(new LongRunningAction("thread-5", ph));

        System.out.println("awaiting: main(" + ph.getPhase() + ")");
        ph.arriveAndAwaitAdvance();
        System.out.println("awaited: main(" + ph.getPhase() + ")");

        ph.arriveAndDeregister();
    }

    private static class LongRunningAction implements Runnable {
        private String threadName;
        private Phaser ph;

        public LongRunningAction(String threadName, Phaser ph) {
            this.threadName = threadName;
            this.ph = ph;
            ph.register();
        }

        @Override
        public void run() {
            System.out.println("awaiting: " + threadName);
            ph.arriveAndAwaitAdvance();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("deregistering: " + threadName);
            ph.arriveAndDeregister();
        }

        @Override
        public String toString() {
            return threadName;
        }
    }
}
