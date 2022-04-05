package com.example.myedu.service;

import com.example.myedu.entity.Class;
import com.example.myedu.entity.Enrollment;
import com.example.myedu.exception.CustomException;
import com.example.myedu.repository.ClassRepository;
import com.example.myedu.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Override
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public List<Class> getTeacherClassesById(int id) {
        return classRepository.findClassByTeacherUserId(id);
    }

    @Override
    public Class getClassById(int id) {
        return classRepository.findByClassId(id).orElseThrow(() -> new CustomException("Khong tim thay lop nay", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Class> getStudentClassesById(int id) {
        List<Class> classes = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentByStudentUserId(id);
        for (Enrollment enrollment : enrollments) {
            classes.add(classRepository.getClassByClassId(enrollment.getClassId()));
        }
        return classes;
    }

}
