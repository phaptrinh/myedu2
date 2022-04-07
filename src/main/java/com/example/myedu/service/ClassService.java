package com.example.myedu.service;

import com.example.myedu.entity.Class;
import com.example.myedu.model.request.ClassRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<Class> getAllClasses();
    List<Class> getTeacherClassesById(int id);

    Class getClassById(int id);

    List<Class> getStudentClassesById(int id);

    ResponseEntity<?> createNewClass(ClassRequest classRequest);

    List<Integer> getSubjectIdsByTeacherId(Integer teacherId);

    ResponseEntity<?> updateById(Integer id, ClassRequest classRequest);

    ResponseEntity<?> deleteById(Integer id);
}
