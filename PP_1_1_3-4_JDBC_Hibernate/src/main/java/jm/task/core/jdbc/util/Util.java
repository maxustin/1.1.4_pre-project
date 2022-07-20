package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.lang.module.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String HOST = "jdbc:mysql://localhost:3306/userbase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "u6wf4mkdjb";

    //Hibernate connection
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry standardServiceRegistry;

    static {
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        Map<String, String> dbSetting = new HashMap<>();
        dbSetting.put(Environment.URL, "jdbc:mysql://localhost:3306/userbase");
        dbSetting.put(Environment.USER, "root");
        dbSetting.put(Environment.PASS, "u6wf4mkdjb");
        dbSetting.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        dbSetting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        registryBuilder.applySettings(dbSetting);
        standardServiceRegistry = registryBuilder.build();
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        metadataSources.addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    //JDBC connection
    private static Connection connection;

    private Util() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
