package com.example.myedu.repository;

import com.example.myedu.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findEnrollmentByStudentUserId(int id);

    @Query("select (count(e) > 0) from Enrollment e where e.studentUserId = ?1 and e.classId = ?2")
    boolean existsByStudentUserIdAndClassId(int studentUserId, int classId);

    boolean existsEnrollmentByClassId(int classId);
}
