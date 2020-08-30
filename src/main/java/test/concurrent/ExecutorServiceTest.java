package test.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            futureList.add(executor.submit(() -> {
                System.out.println("In thread aaa: " + index);
                Thread.sleep(10000);
                System.out.println("In thread bbb: " + index);
                return Thread.currentThread().getName() + ", index=" + index + ", ended at " + new Date();
            }));
        }

        for (Future<String> future : futureList) {
            System.out.println("before get()...");
            String result = future.get();
            System.out.println("Thread result: " + result);
        }

        executor.shutdown();
    }
}
