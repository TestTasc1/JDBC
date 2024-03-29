package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    public static final String userName = "postgres";
    public static final String password = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String DRIVER = "org.postgresql.Driver";

    private static SessionFactory sessionFactory;


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, userName, password);
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.username", userName);
        properties.setProperty("hibernate.connection.password", password);
        properties.setProperty("hibernate.connection.driver_class", DRIVER);
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.current_session_context_class", "thread");

        return properties;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.setProperties(getProperties());
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}


