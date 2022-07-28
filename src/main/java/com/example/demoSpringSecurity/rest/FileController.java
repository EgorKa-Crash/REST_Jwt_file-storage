package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.dao.FileDAO;
import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.entities.File;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.fileSystem.FileManager;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import com.example.demoSpringSecurity.service.impl.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/file")
public class FileController {

    private final IFileService fileService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public FileController(IFileService fileService, JwtTokenProvider jwtTokenProvider) {
        this.fileService = fileService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody File file) {
        fileService.create(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/public/{id}")
    public ResponseEntity<List<File>> read(@PathVariable int id) {
        final List<File> files = fileService.getPublicFiles(id);
        System.out.println("получение списока файлов");
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping(value = "/allFiles/")
    public ResponseEntity<List<File>> read1(@RequestHeader("Authorization") String token) {
        String stillToken = jwtTokenProvider.resolveToken(token);
        User user = UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken));
        final List<File> files = fileService.getFiles(user.getUserId());
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<?> update(@RequestBody File file) {
        String availability = file.getAvailability();
        do {
            fileService.update(file);
            file = file.getParantFile();
            if (file != null) {
                file.setAvailability(availability);
            }
        }
        while (file != null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        String path = FileDAO.getFileById(id).getPach();
        FileManager.deleteFile(path);

        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/upload/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile file, @RequestHeader("Authorization") String token) {
        //System.out.println(file.getName() + " " + file.getSize());
        String stillToken = jwtTokenProvider.resolveToken(token);
        User user = UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken));

        String filePath = FileManager.addNewFile(user, file);
        File dbFile = new File(
                file.getOriginalFilename(),
                (int) file.getSize(),
                0,
                filePath,
                "Private",
                user
        );
        File perentFile = FileDAO.getFilePerent(file.getOriginalFilename());
        if (perentFile != null) {
            dbFile.setParantFile(perentFile);
            dbFile.setVersion(perentFile.getVersion() + 1);
        }
        fileService.create(dbFile);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        File f = FileDAO.getFileById(id);
        String path = f.getPach();
        java.io.File file = new java.io.File(path);
        Path file1 = Paths.get(file.getAbsolutePath());
        Resource resource = new UrlResource(file1.toUri());
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}