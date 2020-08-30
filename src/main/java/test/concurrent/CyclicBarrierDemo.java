package test.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private CyclicBarrier cyclicBarrier;
    private List<Integer> partialResults = Collections.synchronizedList(new ArrayList<>());
    private Random random = new Random();
    private int NUM_PARTIAL_RESULTS;
    private int NUM_WORKERS;

    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        demo.runSimulation(5, 3);
        System.out.println("main end-line");
    }

    public void runSimulation(int numWorkers, int numberOfPartialResults) {
        NUM_PARTIAL_RESULTS = numberOfPartialResults;
        NUM_WORKERS = numWorkers;

        cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread());

        System.out.println("Spawning " + NUM_WORKERS + " worker threads to compute " + NUM_PARTIAL_RESULTS + " partial results each");

        List<Thread> workerList = new ArrayList<>();
        for (int i = 0; i < NUM_WORKERS; i++) {
            Thread worker = new Thread(new NumberCruncherThread());
            workerList.add(worker);

            worker.setName("Thread " + i);
            worker.start();
        }

        for (Thread worker : workerList) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class NumberCruncherThread implements Runnable {
        private int count = 0;

        private boolean done() {
            return count == NUM_PARTIAL_RESULTS ? true : false;
        }

        @Override
        public void run() {
            String thisThreadName = Thread.currentThread().getName();

            while (!done()) {
                ++count;
                Integer num = random.nextInt(10);
                System.out.println(thisThreadName + ": Add num - " + num);

                partialResults.add(num);
                System.out.println(thisThreadName + " waiting for others to reach barrier.");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class AggregatorThread implements Runnable {
        @Override
        public void run() {
            String thisThreadName = Thread.currentThread().getName();
            System.out.println(thisThreadName + ": Computing sum of " + NUM_WORKERS + " workers, having " + NUM_PARTIAL_RESULTS + " results each.");
            int sum = 0;

            for (Integer result : partialResults) {
                System.out.print("Adding " + result + " ");
                sum += result;
                System.out.println();
            }
            System.out.println(thisThreadName + ": Final result = " + sum);
        }
    }
}
