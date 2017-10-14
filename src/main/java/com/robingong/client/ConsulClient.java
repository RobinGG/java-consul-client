package com.robingong.client;

public class ConsulClient {

    private String host;
    private int port;
    private String url;

    public final ConsulKvClient kv;
    public HttpClient session;

    public ConsulClient(String host, int port) {
        this.host = host;
        this.port = port;

        url = "http://" + host + ":" + port;
        kv = new ConsulKvClient(url);
    }

}
