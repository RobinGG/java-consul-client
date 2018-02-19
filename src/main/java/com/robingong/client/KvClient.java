package com.robingong.client;

import com.robingong.configuration.ConfigContext;
import com.robingong.util.GsonHolder;
import okhttp3.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class KvClient {

    private static final String BASE_PATH = "/v1/kv/";
    private OkHttpClient httpClient;

    @PostConstruct
    public void initClient() throws MalformedURLException {
        if (httpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            httpClient = builder.build();
        }
    }

    public String getKv(String key) throws IOException {
        URL url = buildUrl(key);
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        List<Map<String, String>> responseList = GsonHolder.instance.getGson().fromJson(response.body().string(), List.class);
        return new String(Base64.getDecoder().decode(responseList.get(0).get("Value")), "utf-8");
    }

    public boolean setKv(String key, String value) throws IOException {
        URL url = buildUrl(key);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), value);
        Request request = new Request.Builder().url(url).put(body).build();
        Response response = httpClient.newCall(request).execute();
        return Boolean.parseBoolean(response.body().string().trim());
    }

    public boolean deleteKv(String key) throws IOException {
        URL url = buildUrl(key);
        Request request = new Request.Builder().url(url).delete().build();
        Response response = httpClient.newCall(request).execute();
        return Boolean.parseBoolean(response.body().string().trim());
    }

    private URL buildUrl(String key) throws MalformedURLException {
        return new URL(ConfigContext.getProtocol(), ConfigContext.getAddress(), ConfigContext.getPort(), BASE_PATH + key);
    }
}
