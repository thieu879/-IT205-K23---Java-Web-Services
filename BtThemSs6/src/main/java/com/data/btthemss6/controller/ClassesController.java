package com.data.btthemss6.controller;

import com.data.btthemss6.entity.Classes;
import com.data.btthemss6.model.DataResponse;
import com.data.btthemss6.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    @GetMapping
    public ResponseEntity<DataResponse<List<Classes>>> getAll() {
        List<Classes> classesList = classesService.findAll();
        DataResponse<List<Classes>> response = new DataResponse<>(classesList, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<DataResponse<Classes>> createClass(Classes classes) {
        Classes savedClass = classesService.save(classes);
        DataResponse<Classes> response = new DataResponse<>(savedClass, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Classes>> updateClasses(@PathVariable Long id, @RequestBody Classes classes) {
        classes.setId(id);
        Classes updatedClass = classesService.update(classes);
        DataResponse<Classes> response = new DataResponse<>(updatedClass, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteClasses(@PathVariable Long id) {
        boolean isDeleted = classesService.deleteById(id);
        if (isDeleted) {
            DataResponse<String> response = new DataResponse<>("Class deleted successfully", HttpStatus.OK);
            return ResponseEntity.ok(response);
        } else {
            DataResponse<String> response = new DataResponse<>("Class not found", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
