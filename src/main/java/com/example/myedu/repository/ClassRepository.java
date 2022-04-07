package com.example.myedu.repository;

import com.example.myedu.entity.Class;
import com.example.myedu.entity.Subject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Integer> {
    List<Class> findClassByTeacherUserId(int id);

    Optional<Class> findByClassId(Integer id);

    Class getClassByClassId(Integer id);

    Optional<Class> findByTeacherUserIdAndSubjectIdAndTimeId(Integer teacherUserId, Integer subjectIdm, Integer timeId);

    @Query( "select c.subjectId from Class c where c.teacherUserId = :id" )
    List<Integer> findSubjectIdByTeacherUserId(int id);
}
