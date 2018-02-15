package com.robingong.client;

import com.robingong.configuration.ConfigContext;
import com.robingong.util.GsonHolder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KvClient {

    private static final String BASE_PATH = "/v1/kv/";
    private OkHttpClient httpClient;

    @PostConstruct
    public void initClient() throws MalformedURLException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        httpClient = builder.build();

    }

    public String getKv(String key) throws IOException {
        URL url = buildUrl(key);
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        List<Map<String, String>> responseList = GsonHolder.instance.getGson().fromJson(response.body().string(), List.class);
        return responseList.get(0).get("Value");
    }

    private URL buildUrl(String key) throws MalformedURLException {
        URL url = new URL(ConfigContext.getProtocol(), ConfigContext.getAddress(), ConfigContext.getPort(), BASE_PATH + key);
        return url;
    }

}
