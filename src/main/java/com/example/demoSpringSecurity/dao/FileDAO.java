package com.example.demoSpringSecurity.dao;

import com.example.demoSpringSecurity.entities.File;
import com.example.demoSpringSecurity.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FileDAO {

    public static void create(File file) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.save(file);
            session.getTransaction().commit();
        }
    }

    public static void update(File file) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.update(file);
            session.getTransaction().commit();
        }
    }

    public static void delete(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.remove(session.get(File.class, id));
            session.getTransaction().commit();
        }
    }


    public static List<File> getPublicFiles(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("FROM File WHERE user.userId = :id ORDER BY name");
            query.setParameter("id", id);
            return query.list();
        }
    }
}
