package com.robingong.configuration;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class ConfigurationLoader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    private static final Configurations configurations = new Configurations();
    private static Configuration config;
    private static String confPath;

    static {
        initLoader();
    }

    @PostConstruct
    public static void initLoader() {
        confPath = System.getProperty("consul.config.path");
        if (StringUtils.isNotEmpty(confPath)) {
            try {
                config = configurations.properties(confPath);
            } catch (ConfigurationException nonProperties) {
                try {
                    config = configurations.xml(confPath);
                } catch (ConfigurationException nonXml) {
                    logger.error("Failed to load configuration file: {}", confPath);
                }
            }
        }
    }

    public static String getAddress() {
        return config.getString("consul.address", "localhost");
    }

    public static int getPort() {
        return config.getInt("consul.port", 8500);
    }
}
