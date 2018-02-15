package com.robingong.configuration;

public class ConfigContext {

    private static final String DEFAULT_SERVER_ADDRESS = "localhost";
    private static final int DEFAULT_SERVER_PORT = 8500;

    private static final String PROTOCOL = "http";
    private static String serverAddress = DEFAULT_SERVER_ADDRESS;
    private static int serverPort = DEFAULT_SERVER_PORT;


    public static String getAddress() {
        try {
            serverAddress = ConfigurationLoader.getAddress();
        } catch (Exception ignore) {
        }
        return serverAddress;
    }

    public static int getPort() {
        try {
            serverPort = ConfigurationLoader.getPort();
        } catch (Exception ignore) {
        }
        return serverPort;
    }

    public static String getProtocol() {
        return PROTOCOL;
    }
}
