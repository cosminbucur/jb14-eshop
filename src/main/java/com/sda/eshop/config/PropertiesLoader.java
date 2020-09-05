package com.sda.eshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PropertiesLoader {

    public static DbConfig loadProperties() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("properties.yml")).getFile());
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        DbConfig dbConfig = null;
        try {
            dbConfig = mapper.readValue(file, DbConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbConfig;
    }
}
