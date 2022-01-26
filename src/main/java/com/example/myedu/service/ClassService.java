package com.example.myedu.service;

import com.example.myedu.entity.Class;

import java.util.List;

public interface ClassService {
    List<Class> getAllClasses();
    List<Class> getTeacherClassesById(int id);

    Class getClassById(int id);

    List<Class> getStudentClassesById(int id);
}
