package rxjava.scheduler.http.callbackhell;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

import static rxjava.scheduler.http.CommonUtils.URL_README;

@Slf4j
public class HttpRestExample {
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        Request request = new Request.Builder()
                .url(URL_README)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("onResponse : {}", response.body().string());
            }
        });
    }
}
