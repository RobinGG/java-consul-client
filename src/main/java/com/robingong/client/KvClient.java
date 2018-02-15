package com.robingong.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class KvClient {

    private OkHttpClient httpClient;

    @PostConstruct
    public void initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        httpClient = builder.build();
    }

    public String getKv() throws IOException {
        Request request = new Request.Builder().url("http://127.0.0.1:8500/v1/kv/test").build();
        Response response = httpClient.newCall(request).execute();
        System.out.println(response.code());
        return response.body().string();
    }

}
