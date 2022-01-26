package com.example.myedu.repository;

import com.example.myedu.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findEnrollmentByStudentUserId(int id);
}
