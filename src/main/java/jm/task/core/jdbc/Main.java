package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Sofi", "Zoreeva", (byte) 25);
        userDaoJDBC.saveUser("Alex", "Zoreev", (byte) 27);
        userDaoJDBC.saveUser("Siel", "Fantomhaiv", (byte) 13);
        userDaoJDBC.saveUser("Belfegor", "Kraud", (byte) 26);
//        System.out.println("User c имненем " + userDaoJDBC.getAllUsers().toString() + " добавлен в базу данных");

        System.out.println(userDaoJDBC.getAllUsers());


//      userDaoJDBC.cleanUsersTable();
      userDaoJDBC.dropUsersTable();






//        Session session = Util.getSessionFactory().openSession();
//        session.beginTransaction();
//        User user = new User("Sofi", "Xox", (byte)25);
//        session.save(user);
//        session.getTransaction().commit();


//
//        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.removeUserById(3l);

    }
}
