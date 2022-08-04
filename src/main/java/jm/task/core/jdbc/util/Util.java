package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    // реализуйте настройку соеденения с БД
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "Qazwsxdfvgbh2622431!@";

    private Util() {
    }


    public static Connection start() {
        try {
            return DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(User.class);

            return configuration.buildSessionFactory();

        } catch (HibernateException ex) {

            throw new RuntimeException(ex);
        }
    }

}
