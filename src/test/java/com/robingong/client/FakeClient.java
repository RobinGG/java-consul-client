package com.robingong.client;

import org.junit.Test;

public class FakeClient {

    @Test
    public void testKvClient() throws Throwable {
        KvClient kvClient = new KvClient();
        kvClient.initClient();
        System.out.println(kvClient.getKv("test"));
    }

}
