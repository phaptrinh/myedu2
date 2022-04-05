package com.example.myedu.service;

import com.example.myedu.entity.Enrollment;
import com.example.myedu.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Override
    public void enrollClassById(int studentUserId, int classId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentUserId(studentUserId);
        enrollment.setClassId(classId);
        enrollmentRepository.save(enrollment);
    }

    @Override
    public boolean existsByStudentUserIdAndClassId(int studentUserId, int classId) {
        return enrollmentRepository.existsByStudentUserIdAndClassId(studentUserId, classId);
    }

    @Override
    public boolean existsEnrollmentByClassId(int classId) {
        return enrollmentRepository.existsEnrollmentByClassId(classId);
    }
}
