package com.robingong.client;

import com.robingong.configuration.ConfigContext;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
        return response.body().string();
    }

    private URL buildUrl(String key) throws MalformedURLException {
        URL url = new URL(ConfigContext.getProtocol(), ConfigContext.getAddress(), ConfigContext.getPort(), BASE_PATH + key);
        return url;
    }

}
