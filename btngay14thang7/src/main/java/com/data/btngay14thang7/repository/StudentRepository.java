package com.data.btngay14thang7.repository;
import com.data.btngay14thang7.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.fullName LIKE %:fullName%")
    List<Student> findByFullNameContaining(@Param("fullName") String fullName);

    @Query("SELECT s FROM Student s WHERE s.address LIKE %:address%")
    List<Student> findByAddressContaining(@Param("address") String address);

    @Query("SELECT s FROM Student s WHERE s.className LIKE %:className%")
    List<Student> findByClassNameContaining(@Param("className") String className);

    List<Student> findByGender(String gender);

    List<Student> findByClassName(String className);

    boolean existsByClassName(String className);
}

