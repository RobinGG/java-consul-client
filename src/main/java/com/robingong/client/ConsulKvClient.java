package com.robingong.client;

import com.google.gson.Gson;
import com.robingong.factory.GsonFactory;
import com.robingong.model.KvResponse;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Base64;

public class ConsulKvClient extends HttpClient {

    private Gson gson = GsonFactory.getInstance();

    public ConsulKvClient(String url) {
        super(url);
        this.url = url + "/v1/kv/";
    }

    public String getKeyValue(String key) throws IOException {
        key = url + key;
        Request request = new Request.Builder().url(key).get().build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            return null;
        }
        KvResponse[] kvList = gson.fromJson(response.body().string(), KvResponse[].class);
        return new String(Base64.getDecoder().decode(kvList[0].getValue()));
    }

    public String setKeyValue(String key, String value) throws IOException {
        key = url + key;
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/text"), value);
        Request request = new Request.Builder().url(key).put(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public String deleteKeyValue(String key) throws IOException {
        key = url + key;
        Request request = new Request.Builder().url(key).delete().build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
