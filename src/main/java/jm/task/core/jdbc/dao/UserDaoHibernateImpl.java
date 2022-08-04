package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.From;
import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("""
                            CREATE TABLE IF NOT EXISTS solur( 
                            id SERIAL PRIMARY KEY,
                            name VARCHAR NOT NULL, 
                            lastName VARCHAR NOT NULL,
                            age SMALLINT NOT NULL CHECK (age>0)
                            );
                            """).
                    executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {

            throw new RuntimeException(ex);

        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS solur").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {

            throw new RuntimeException(ex);
        }
    }

    @Override

    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (HibernateException ex) {

            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<User> list = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
            return list;
        } catch (HibernateException ex) {

            throw new RuntimeException(ex);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException ex) {

            throw new RuntimeException(ex);
        }
    }
}

