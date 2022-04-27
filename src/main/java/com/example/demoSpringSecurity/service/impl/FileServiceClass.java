package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.dao.FileDAO;
import com.example.demoSpringSecurity.entities.File;
import com.example.demoSpringSecurity.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceClass implements com.example.demoSpringSecurity.service.impl.FileService {

    @Override
    public void create(File file) {
        FileDAO.create(file);
    }

    @Override
    public void update(File file) {
        FileDAO.update(file);
    }

    @Override
    public void delete(long id) {
        FileDAO.delete(id);
    }

    @Override
    public List<File> getPublicFiles(long id) {
        return FileDAO.getPublicFiles(id);
    }
}
