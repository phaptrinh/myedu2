package com.example.myedu.service;

import com.example.myedu.entity.Class;

import java.util.List;

public interface ClassService {
    List<Class> getAllClasses();
    Class getClassById(int id);

}
