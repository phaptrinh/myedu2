package com.example.myedu.service;

import com.example.myedu.entity.Class;
import com.example.myedu.entity.Enrollment;
import com.example.myedu.repository.ClassRepository;
import com.example.myedu.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return classRepository.findClassByClassId(id);
    }

    @Override
    public List<Class> getStudentClassesById(int id) {
        List<Class> classes = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentByStudentUserId(id);
        for (Enrollment enrollment : enrollments) {
            classes.add(classRepository.findClassByClassId(enrollment.getClassId()));
        }
        return classes;
    }

}
