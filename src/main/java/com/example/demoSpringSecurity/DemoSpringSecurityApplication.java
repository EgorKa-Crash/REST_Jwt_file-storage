package com.example.demoSpringSecurity;

import com.example.demoSpringSecurity.dao.FileDAO;
import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.entities.File;
import com.example.demoSpringSecurity.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringSecurityApplication {

	public static void main(String[] args) {

 //	User user = UserDAO.getUser(1L);
//		File file = new File( "name",   1,   "pach",  "Public", user);
//		File file2 = new File( "name2",   2,   "pach2",  "Public", user);
//		file2.setParantFile(file);
//
//		FileDAO.create(file);
//		FileDAO.create(file2);
//		System.out.println(file2.getName());

//		File file3 = new File( "name3",   3,   "pach3",  "Public", user);
//		file3.setParantFile(FileDAO.getFileById(11));
//		FileDAO.create(file3);
		SpringApplication.run(DemoSpringSecurityApplication.class, args);
	}
}
