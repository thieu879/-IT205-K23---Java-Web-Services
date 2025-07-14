package com.data.btngay14thang7.service;
import com.data.btngay14thang7.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    Student getStudentById(Long studentId);
    Student insertStudent(Student student);
    Student updateStudent(Student student, Long studentId);
    String deleteStudent(Long studentId);
    List<Student> getStudentsByFullName(String fullName);
    List<Student> getStudentsByAddress(String address);
    List<Student> getStudentsByClassName(String className);
    List<Student> getStudentsByGender(String gender);
}
