package com.example.demoSpringSecurity.fileSystem;

import com.example.demoSpringSecurity.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileManager {


    static String dir = "src/main/resources/userFiles/";

    public static String addNewFile(User user, MultipartFile file) {
        String finalDir = dir + user.getLogin() + "/";
        new File(finalDir).mkdirs();  //создание директории

        Date date = new Date();
        String fileName = (int)date.getTime() + file.getOriginalFilename();
        Path filepath = Paths.get(finalDir,  fileName);

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalDir + fileName;
    }

    public static void deleteFile(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("файл уже удален");
        }
    }
}
