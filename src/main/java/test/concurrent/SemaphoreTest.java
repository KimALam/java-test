package test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        final SomeResource resource = new SomeResource(2);

        Thread temp = null;
        for (int i = 0; i < 10; i++) {
            temp = new Thread(() -> resource.use());
            threadList.add(temp);
            temp.start();
        }

        for (Thread thread : threadList) {
            System.out.println("[" + thread.getName() + "] joining...");
            thread.join();
        }
    }

    private static class SomeResource {
        private final Semaphore semaphore;
        private final int maxThread;

        public SomeResource(int maxThread) {
            this.maxThread = maxThread;
            this.semaphore = new Semaphore(maxThread);
        }

        public void use() {
            try {
                semaphore.acquire();

                System.out.println("[" + Thread.currentThread().getName() + "] spare: " + semaphore.availablePermits());
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
