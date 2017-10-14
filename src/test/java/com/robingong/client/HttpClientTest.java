package com.robingong.client;

import org.junit.Assert;
import org.junit.Test;

public class HttpClientTest {

    private String key = "test_key";
    private String value = "test_value";

    private ConsulClient client = new ConsulClient("localhost", 8500);

    @Test
    public void kvGetValue() throws Exception {
        String json = client.kv.getKeyValue(key);
        Assert.assertEquals(value, json);
    }

    @Test
    public void kvSetValue() throws Exception {
        String json = client.kv.setKeyValue(key, value);
        Assert.assertEquals("true", json);
    }

    @Test
    public void kvDeleteKey() throws Exception {
        String json = client.kv.deleteKeyValue(key);
        Assert.assertEquals("true", json);

        Assert.assertNull(client.kv.getKeyValue(key));
    }
}