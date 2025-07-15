package com.data.btthemss6.service;

import com.data.btthemss6.entity.Students;
import com.data.btthemss6.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Students> findAll() {
        return studentRepository.findAll();
    }
    public Students findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    public Students save(Students student) {
        return studentRepository.save(student);
    }
    public boolean deleteById(Long id) {
        try {
            studentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting student with id: " + id, e);
        }
    }
    public Students update(Students student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Error updating student with id: " + student.getId(), e);
        }
    }
}
