package com.data.btthemss6.service;

import com.data.btthemss6.entity.Classes;
import com.data.btthemss6.repository.ClassesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassesService {
    private ClassesRepository classesRepository;
    public ClassesService(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }
    public List<Classes> findAll() {
        return classesRepository.findAll();
    }
    public Classes findById(Long id) {
        return classesRepository.findById(id).orElse(null);
    }
    public Classes save(Classes classes) {
        return classesRepository.save(classes);
    }
    public boolean deleteById(Long id) {
        try {
            classesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting class with id: " + id, e);
        }
    }
    public Classes update(Classes classes) {
        try {
            return classesRepository.save(classes);
        } catch (Exception e) {
            throw new RuntimeException("Error updating class with id: " + classes.getId(), e);
        }
    }
}
