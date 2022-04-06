package com.example.myedu.repository;

import com.example.myedu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    User getUserByUsername(String username);

    boolean existsByUsername(String username);

//    @Override
//    Optional<User> findByRoleId(Integer roleId);

    List<User> findAllByRoleId(Integer roleId);
}
