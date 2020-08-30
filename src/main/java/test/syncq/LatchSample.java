package test.syncq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LatchSample {
    private static final Logger log = LoggerFactory.getLogger(LatchSample.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
//        Sum sum = new Sum();
        RefObj ref = new RefObj();
//        CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                log.debug("Producer| start...");
                ref.ref = new Sum(10, 20);
                log.debug("Producer| before countdown. value = {}", ref.ref);
//                countDownLatch.countDown();
                log.debug("Producer| after countdown...");
            }
        };

        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                log.debug("Consumer| start...");
                log.debug("Consumer| notified...");
                log.debug("Consumer| value : {}", ref.ref);
//                try {
//                    Thread.sleep(1);
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };

        executor.execute(producer);
        executor.execute(consumer);
        executor.execute(consumer);
        executor.execute(consumer);
        executor.execute(consumer);
        executor.execute(consumer);
        executor.execute(consumer);
        executor.execute(consumer);
        executor.execute(consumer);
        executor.execute(consumer);

        executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
        executor.shutdown();

//        log.debug("latch value = {}", countDownLatch.getCount());
    }

    private static class RefObj {
        Object ref;
    }

    private static class Sum {
        int total;
        int lhs;
        int rhs;

        public Sum() {}

        public Sum(int lhs, int rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
            this.total = lhs + rhs;
        }

        @Override
        public String toString() {
            return "Sum{" +
                    "total=" + total +
                    ", lhs=" + lhs +
                    ", rhs=" + rhs +
                    '}';
        }

        public void sum() {
            total = lhs + rhs;
        }
    }
}
