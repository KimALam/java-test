package test.syncq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SyncQSample {
    private static final Logger log = LoggerFactory.getLogger(SyncQSample.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Runnable producer = () -> {
            Integer producedElement = ThreadLocalRandom.current().nextInt();
            try {
                log.debug("producer| putting value: {}", producedElement);
                queue.put(producedElement);
                log.debug("producer| put value: {}", producedElement);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                log.debug("consumer| sleep 3 seconds...");
                Thread.sleep(10000);
                log.debug("consumer| wake up...");
                Integer consumedElement = queue.take();
                log.debug("consumer| got it: {}", consumedElement);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executor.execute(producer);
        executor.execute(consumer);

        log.debug("main| waiting workers...");

        executor.awaitTermination(20, TimeUnit.SECONDS);
        executor.shutdown();

        log.debug("main| queue size: {}", queue.size());
    }
}
