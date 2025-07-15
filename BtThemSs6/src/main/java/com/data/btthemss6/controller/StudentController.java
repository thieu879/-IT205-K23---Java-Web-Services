package com.data.btthemss6.controller;

import com.data.btthemss6.entity.Students;
import com.data.btthemss6.model.DataResponse;
import com.data.btthemss6.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public ResponseEntity<DataResponse<List<Students>>> findAll() {
        List<Students> studentsList = studentService.findAll();
        DataResponse<List<Students>> response = new DataResponse<>(studentsList, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<DataResponse<Students>> createStudent(@RequestBody Students student) {
        Students savedStudent = studentService.save(student);
        DataResponse<Students> response = new DataResponse<>(savedStudent, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Students>> updateStudent(@PathVariable Long id, @RequestBody Students student) {
        student.setId(id);
        Students updatedStudent = studentService.save(student);
        DataResponse<Students> response = new DataResponse<>(updatedStudent, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteById(id);
            DataResponse<String> response = new DataResponse<>("Student deleted successfully", HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            DataResponse<String> response = new DataResponse<>("Student not found", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
