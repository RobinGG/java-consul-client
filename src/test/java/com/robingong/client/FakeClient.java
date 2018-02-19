package com.robingong.client;

import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FakeClient {

    private static ConsulProcess consul;
    private static final String TEST_KEY = "testKey";
    private static final String TEST_VALUE = "testValue";

    @BeforeClass
    public static void init() throws InterruptedException {
        String customConfig = "{\n" +
                "        \"ports\": {\n" +
                "            \"dns\": 8600,\n" +
                "            \"http\": 8500,\n" +
                "            \"serf_lan\": 8301,\n" +
                "            \"serf_wan\": 8302,\n" +
                "            \"server\": 8300\n" +
                "        }\n" +
                "    }";
        consul = ConsulStarterBuilder.consulStarter().withCustomConfig(customConfig).build().start();
    }

    @Test
    public void testKvClient() throws Throwable {
        KvClient kvClient = new KvClient();
        kvClient.initClient();
        boolean result = kvClient.setKv(TEST_KEY, TEST_VALUE);
        Assert.assertEquals(true, result);
        String value = kvClient.getKv(TEST_KEY);
        Assert.assertEquals(TEST_VALUE, value);
        result = kvClient.deleteKv(TEST_KEY);
        Assert.assertEquals(true, result);
    }

    @AfterClass
    public static void destroy() {
        if (consul != null)
            consul.close();
    }
}
