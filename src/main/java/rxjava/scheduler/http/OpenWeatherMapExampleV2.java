package rxjava.scheduler.http;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class OpenWeatherMapExampleV2 {
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=";
    private static final OkHttpClient client = new OkHttpClient();

    public void run() {
        log.info("Go on...");

        Observable<String> source = Observable.just(URL + "86047ce156349a73b0d82b228e13d5ae")
                .map(this::get)
                .subscribeOn(Schedulers.io())
                .share()
                .observeOn(Schedulers.newThread());

        source.map(this::parseTemperature).subscribe(val -> log.info("Temperature : {}", val));
        source.map(this::parseCityName).subscribe(val -> log.info("City: {}", val));
        source.map(this::parseCountry).subscribe(val -> log.info("Country: {}", val));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String get(String url) throws IOException {
        log.info("Requesting get : {}", url);

        Request request = new Request.Builder().url(url).build();
        return client.newCall(request)
                .execute()
                .body()
                .string();
    }

    private String parseTemperature(String json) {
        return parse(json, "\"temp\":[0-9]*.[0-9]*");
    }

    private String parseCityName(String json) {
        return parse(json, "\"name\":\"[a-zA-Z]*\"");
    }

    private String parseCountry(String json) {
        return parse(json, "\"country\":\"[a-zA-Z]*\"");
    }

    private String parse(String json, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(json);
        if (match.find()) {
            return match.group();
        }
        return "N/A";
    }

    public static void main(String[] args) {
        OpenWeatherMapExampleV2 demo = new OpenWeatherMapExampleV2();
        demo.run();
    }
}
