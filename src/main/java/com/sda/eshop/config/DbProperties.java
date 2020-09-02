package com.sda.eshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DbProperties {

    public final static ClassToMapDbProperties getSpecificProperty() {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("properties.yml")).getFile());

        ObjectMapper om = new ObjectMapper(new YAMLFactory());

        ClassToMapDbProperties classToMapDbProperties = null;

        {
            try {
                classToMapDbProperties = om.readValue(file, ClassToMapDbProperties.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return classToMapDbProperties;
    }


}
