package test.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class IOMultiplexingTest {
    private static final String LOCAL_HOST = "http://localhost:8888";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        long time = System.currentTimeMillis();
        final IOMultiplexingTest test = new IOMultiplexingTest();

        /* one thread call
        test.sendGet();
        test.sendGet();
         */

        // multi thread call
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<String> res1 = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return test.sendGet();
            }
        });

        Future<String> res2 = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return test.sendGet();
            }
        });

        System.out.println("block1...");
        System.out.println("response1: " + res1.get());
        System.out.println("bloc2...");
        System.out.println("response2: " + res2.get());
        System.out.println("all done...");
        System.out.println("total: " + (System.currentTimeMillis() - time));

        service.shutdown();
    }

    private String sendGet() throws IOException {
        // prepare connection
        URL url = new URL(LOCAL_HOST);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // get response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String responseString;
        String res = null;
        while ((responseString = in.readLine()) != null) {
            res = responseString;
            System.out.println("response: " + responseString);
        }

        in.close();
        return res;
    }
}
