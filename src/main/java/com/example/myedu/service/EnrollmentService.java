package com.example.myedu.service;

public interface EnrollmentService {
    void enrollClassById(int StudentUserId, int ClassId);

    boolean existsByStudentUserIdAndClassId(int studentUserId, int classId);

    boolean existsEnrollmentByClassId(int classId);
}
