package com.example.demoSpringSecurity.dao;

import com.example.demoSpringSecurity.dto.SubscriptionsIds;
import com.example.demoSpringSecurity.entities.Subscriptions;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.exception.ErrorObj;
import com.example.demoSpringSecurity.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SubscriptionsDAO {
    public static boolean add(SubscriptionsIds subscriptionsIds) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            Subscriptions subscriptions = new Subscriptions(session.load(User.class, subscriptionsIds.getSubId()), session.load(User.class, subscriptionsIds.getUserId()));
            session.save(subscriptions);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            ErrorObj error = new ErrorObj(true, "Ошибка, данная подписка уже существует");
        }
        return false;
    }

    public static void updateSubscriptions(Subscriptions subscriptions) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.update(subscriptions);
            session.getTransaction().commit();
        }
    }

    public static boolean remove(SubscriptionsIds subscriptionsIds) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE FROM Subscriptions WHERE (subscriber.userId = :subscriber AND user.userId = :user)");
            query.setParameter("subscriber", subscriptionsIds.getUserId());
            query.setParameter("user", subscriptionsIds.getSubId());
            query.executeUpdate();
            session.getTransaction().commit();
            return false;
        }
    }

    public static List<Subscriptions> getAllOfSubscriptions() {
        List<Subscriptions> subscriptions;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return loadAllData(Subscriptions.class, session);
        }
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public static boolean isContainSubscription(SubscriptionsIds subscriptionsIds) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Subscriptions WHERE (subscriber.userId = :userId AND user.userId = :subscriberId)");
            query.setParameter("subscriberId", subscriptionsIds.getSubId());
            query.setParameter("userId", subscriptionsIds.getUserId());
            boolean b = (query.list().size() > 0);
            return b;
        }
    }
}
