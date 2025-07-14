package com.data.btngay14thang7.service;
import com.data.btngay14thang7.entity.Student;
import com.data.btngay14thang7.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElse(null);
    }

    @Override
    public Student insertStudent(Student student) {
        if (student.getFullName() == null || student.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
        if (student.getClassName() == null || student.getClassName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên lớp không được để trống");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long studentId) {
        Optional<Student> existingStudentOpt = studentRepository.findById(studentId);
        if (existingStudentOpt.isPresent()) {
            Student existingStudent = existingStudentOpt.get();

            if (student.getFullName() != null && !student.getFullName().trim().isEmpty()) {
                existingStudent.setFullName(student.getFullName());
            }
            if (student.getGender() != null) {
                existingStudent.setGender(student.getGender());
            }
            if (student.getBirthday() != null) {
                existingStudent.setBirthday(student.getBirthday());
            }
            if (student.getAddress() != null) {
                existingStudent.setAddress(student.getAddress());
            }
            if (student.getClassName() != null && !student.getClassName().trim().isEmpty()) {
                existingStudent.setClassName(student.getClassName());
            }

            return studentRepository.save(existingStudent);
        }
        throw new RuntimeException("Không tìm thấy sinh viên với ID: " + studentId);
    }

    @Override
    public String deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return "Xóa sinh viên thành công";
        } else {
            throw new RuntimeException("Không tìm thấy sinh viên với ID: " + studentId);
        }
    }

    @Override
    public List<Student> getStudentsByFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tìm kiếm không được để trống");
        }
        return studentRepository.findByFullNameContaining(fullName.trim());
    }

    @Override
    public List<Student> getStudentsByAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ tìm kiếm không được để trống");
        }
        return studentRepository.findByAddressContaining(address.trim());
    }

    @Override
    public List<Student> getStudentsByClassName(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên lớp tìm kiếm không được để trống");
        }
        return studentRepository.findByClassNameContaining(className.trim());
    }

    @Override
    public List<Student> getStudentsByGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Giới tính tìm kiếm không được để trống");
        }
        return studentRepository.findByGender(gender.trim());
    }
}
