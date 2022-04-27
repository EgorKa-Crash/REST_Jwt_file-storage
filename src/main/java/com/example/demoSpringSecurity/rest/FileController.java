package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.entities.File;
import com.example.demoSpringSecurity.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final int TEST_USER = 1;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody File file) {
        fileService.create(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/public/{id}")
    public ResponseEntity<List<File>> read(@PathVariable int id) {
        final List<File> files = fileService.getPublicFiles(id);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<?> update( @RequestBody File file) {
        fileService.update(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}