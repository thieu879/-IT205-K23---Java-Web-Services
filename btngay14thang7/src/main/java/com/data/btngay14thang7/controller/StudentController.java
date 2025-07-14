package com.data.btngay14thang7.controller;
import com.data.btngay14thang7.entity.DataResponse;
import com.data.btngay14thang7.entity.Student;
import com.data.btngay14thang7.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Student>>> getAllStudents() {
        return new ResponseEntity<>(new DataResponse<>(studentService.getStudents(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<DataResponse<Student>> getStudentById(@PathVariable Long studentId) {
        return new ResponseEntity<>(new DataResponse<>(studentService.getStudentById(studentId), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Student>> createStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(new DataResponse<>(studentService.insertStudent(student), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DataResponse<Student>> updateStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(new DataResponse<>(studentService.updateStudent(student, student.getStudentId()), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<DataResponse<String>> deleteStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(new DataResponse<>(studentService.deleteStudent(studentId), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/students-by-name/{fullName}", produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<DataResponse<List<Student>>> getStudentsByName(@PathVariable String fullName) {
        return new ResponseEntity<>(new DataResponse<>(studentService.getStudentsByFullName(fullName), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/students-by-address/{address}", produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<DataResponse<List<Student>>> getStudentsByAddress(@PathVariable String address) {
        return new ResponseEntity<>(new DataResponse<>(studentService.getStudentsByAddress(address), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/students-by-class/{className}", produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<DataResponse<List<Student>>> getStudentsByClassName(@PathVariable String className) {
        return new ResponseEntity<>(new DataResponse<>(studentService.getStudentsByClassName(className), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/students-by-gender/{gender}", produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<DataResponse<List<Student>>> getStudentsByGender(@PathVariable String gender) {
        return new ResponseEntity<>(new DataResponse<>(studentService.getStudentsByGender(gender), HttpStatus.OK), HttpStatus.OK);
    }
}

