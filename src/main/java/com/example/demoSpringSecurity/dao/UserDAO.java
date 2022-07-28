package com.example.demoSpringSecurity.dao;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.exception.ErrorObj;
import com.example.demoSpringSecurity.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDAO {

    public static void insertUser(User user) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            ErrorObj.setIsAble(true);
            ErrorObj.setMessange("Ошибка добавления пользователя");
        }
    }

    public static void updateUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            ErrorObj error = new ErrorObj(true, "Ошибка изменения пользователя");
        }
    }

    public static void deleteUser(Long Id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.remove(session.get(User.class, Id));
            session.getTransaction().commit();
        }
    }

    public static User getUser(Long Id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, Id);
        }
    }

    public static User findByLogin(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login = :login");
            query.setParameter("login", login);
            return (User) query.list().get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<User> getAllOfUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return loadAllData(User.class, session);
        }
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public static void update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.update(user);
        }
    }


    public static List<User> getSubscribers(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(" select subscriber FROM Subscriptions s WHERE s.user.userId = :id ORDER BY  s.subscriber.nickName"); // WHERE user.userId =" + id + "
            query.setParameter("id", id);
            return query.list();
        }
    }

    public static List<User> getSubscriptions(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(" select user FROM Subscriptions s WHERE s.subscriber.userId = :id ORDER BY  s.user.nickName"); // WHERE user.userId =" + id + "
            query.setParameter("id", id);
            return query.list();
        }
    }

    public static List<User> getAllSearchResults(String substring) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE nickName like '%" + substring + "%'ORDER BY nickName");//"+substring+"
            return query.list();
        }
    }

    public static List<User> readAll() {
        List<User> userrs;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(User.class);
        userrs = criteria.list();
        session.close();
        return userrs;
    }
}
