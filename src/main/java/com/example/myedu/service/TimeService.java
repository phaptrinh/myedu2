package com.example.myedu.service;

import com.example.myedu.entity.Time;
import com.example.myedu.model.request.TimeRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TimeService {
    List<Time> getAllTime();

    ResponseEntity<?> createNewTime(TimeRequest timeRequest);

    ResponseEntity<?> updateById(Integer id, TimeRequest timeRequest);

    ResponseEntity<?> deleteById(Integer id);
}
