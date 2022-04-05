package com.example.myedu.repository;

import com.example.myedu.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Integer> {
    List<Class> findClassByTeacherUserId(int id);

    Optional<Class> findByClassId(Integer id);

    Class getClassByClassId(Integer id);
}
