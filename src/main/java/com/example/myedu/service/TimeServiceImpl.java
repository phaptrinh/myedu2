package com.example.myedu.service;

import com.example.myedu.entity.Time;
import com.example.myedu.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {
    @Autowired
    TimeRepository timeRepository;

    @Override
    public List<Time> getAllTime() {
        return timeRepository.findAll();
    }
}
