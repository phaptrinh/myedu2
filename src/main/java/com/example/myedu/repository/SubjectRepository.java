package com.example.myedu.repository;

import com.example.myedu.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
//     List<Optional<Subject>> findBySubjectIdIn(List<Integer> ids);

    List<Subject> findAllBySubjectIdIn(Iterable<Integer> integers);

    boolean existsByName(String name);
}
