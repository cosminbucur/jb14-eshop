package com.sda.eshop.config;

import com.sda.eshop.model.Order;
import com.sda.eshop.model.Product;
import com.sda.eshop.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration config = createConfig();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        return config.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static Configuration createConfig() {
        // TODO: read properties from a yml file - DONE
        Configuration configuration = new Configuration();

        //added properties from yml file
        DbConfig dbConfig = PropertiesLoader.loadProperties();

        Properties props = new Properties();
        props.put(Environment.DRIVER, dbConfig.getDriver());
        props.put(Environment.URL, dbConfig.getUrl());
        props.put(Environment.DIALECT, dbConfig.getDialect());
        props.put(Environment.HBM2DDL_AUTO, dbConfig.getHbm2DdlAuto());
        props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        props.put(Environment.USER, dbConfig.getUser());
        props.put(Environment.PASS, dbConfig.getPass());
        props.put(Environment.SHOW_SQL, dbConfig.getShowSql());

        /*
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC");
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.put(Environment.HBM2DDL_AUTO, "create-drop");
        props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "root");
        props.put(Environment.SHOW_SQL, "false");*/

        configuration.setProperties(props);

        // annotated classes
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Product.class);

        return configuration;
    }

}
