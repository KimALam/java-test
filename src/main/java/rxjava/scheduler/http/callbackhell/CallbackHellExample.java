package rxjava.scheduler.http.callbackhell;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

import static rxjava.scheduler.http.CommonUtils.FIRST_URL;
import static rxjava.scheduler.http.CommonUtils.SECOND_URL;

@Slf4j
public class CallbackHellExample {
    private static final OkHttpClient client = new OkHttpClient();

    private Callback onSuccess = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            log.info("onResponse : {}", response.body().string());
        }
    };

    public void run() {
        Request request = new Request.Builder().url(FIRST_URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("run.onResponse : {}", response.body().string());

                // nested call
                Request request1 = new Request.Builder().url(SECOND_URL).build();
                client.newCall(request1).enqueue(onSuccess);
            }
        });
    }

    public static void main(String[] args) {
        CallbackHellExample demo = new CallbackHellExample();
        demo.run();
    }
}
