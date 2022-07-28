package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.dao.FileDAO;
import com.example.demoSpringSecurity.entities.File;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService implements IFileService {

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
        File deletedFile = FileDAO.getFileById(id);
        File perentFile = FileDAO.getFilePerent(deletedFile.getName());
        perentFile.setParantFile(deletedFile.getParantFile());
        FileDAO.update(perentFile);
        FileDAO.delete(id);
    }

    @Override
    public List<File> getPublicFiles(long id) {
        List<File> files = FileDAO.getPublicFiles(id);
        List<Long> dellList = new ArrayList<>();
        for(File file: files){
            if(file.getParantFile() != null){
                dellList.add(file.getParantFile().getFileId());
            }
        }
        files.removeIf(value -> dellList.contains(value.getFileId()));
        return files;
    }

    @Override
    public List<File> getFiles(long id) {
        List<File> files = FileDAO.getFiles(id);
        List<Long> dellList = new ArrayList<>();
        for(File file: files){
            if(file.getParantFile() != null){
                dellList.add(file.getParantFile().getFileId());
            }
        }
        files.removeIf(value -> dellList.contains(value.getFileId()));
        return  files;
    }
}
