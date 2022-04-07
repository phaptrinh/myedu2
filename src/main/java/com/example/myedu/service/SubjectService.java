package com.example.myedu.service;

import com.example.myedu.entity.Subject;
import com.example.myedu.model.request.SubjectRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();

    List<Subject> getAllBySubjectId(List<Integer> integers);

    ResponseEntity<?> createNewSubject(SubjectRequest subjectRequest);

    ResponseEntity<?> updateById(Integer id, SubjectRequest subjectRequest);

    ResponseEntity<?> deleteById(Integer id);
}
