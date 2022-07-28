package com.example.demoSpringSecurity.dao;

import com.example.demoSpringSecurity.entities.File;
import com.example.demoSpringSecurity.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Comparator;
import java.util.List;

public class FileDAO {

    public static void create(File file) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.save(file);
            session.getTransaction().commit();
        }
    }

    public static void update(File file) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.update(file);
            session.getTransaction().commit();
        }
    }

    public static void delete(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE FROM File WHERE fileId = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static File getFileById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM File WHERE fileId = :id");
            query.setParameter("id", id);
            return (File) query.list().get(0);
        }
    }


    public static List<File> getPublicFiles(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM File WHERE user.userId = :id AND availability = 'Public' ORDER BY name");
            query.setParameter("id", id);
            return query.list();
        }
    }

    public static List<File> getFiles(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM File WHERE user.userId = :id ORDER BY name");
            query.setParameter("id", id);
            return query.list();
        }
    }


    public static File getFilePerent(String fileName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM File WHERE name = :fileName");
            //Query query = session.createQuery("Select id From File Where name = :fileName Order by version desc limit 1");
            query.setParameter("fileName", fileName);
            List<File> fileList = query.list();
            if (!fileList.isEmpty()) {
                File f = fileList.get(0);
                for (File file : fileList) {
                    if (file.getVersion()>f.getVersion())
                        f=file;
                }
                return f;
            }
            return null;
        }
    }
}
