package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.entities.File;

import java.util.List;

public interface IFileService {

    /**
     * Создает нового клиента
     *
     * @param file - клиент для создания
     */
    void create(File file);

    /**
     * Возвращает список всех имеющихся клиентов
     *
     * @return список клиентов
     */
    //List<File> readAll();

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    //File read(int id);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     *
     * @param file   - клиент в соответсвии с которым нужно обновить данные
     */
    void update(File file);

    /**
     * Удаляет клиента с заданным ID
     *
     * @param fileId - id клиента, которого нужно удалить
     */
    void delete(long fileId);

    //List<File> getPublicFiles();

    //List<File> getGroupFiles();

    List<File> getPublicFiles(long id);

    List<File> getFiles(long id);
}