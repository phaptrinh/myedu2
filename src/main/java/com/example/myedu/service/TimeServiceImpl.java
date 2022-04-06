package com.example.myedu.service;

import com.example.myedu.entity.Time;
import com.example.myedu.model.request.TimeRequest;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<?> createNewTime(TimeRequest timeRequest) {
        if (timeRepository.existsByWeekDay(timeRequest.getWeekDay())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Time da ton tai"));
        }
        timeRepository.save(Time.builder().weekDay(timeRequest.getWeekDay()).build());
        return  ResponseEntity.ok().body(new MessageResponse("Them thoi gian thanh cong"));
    }

    @Override
    public ResponseEntity<?> updateById(Integer id, TimeRequest timeRequest) {
        if (timeRepository.existsById(id)) {
            if (timeRepository.existsByWeekDay(timeRequest.getWeekDay())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Time da ton tai"));
            }
            Time time = timeRepository.getById(id);
            time.setWeekDay(timeRequest.getWeekDay());
            timeRepository.save(time);
            return  ResponseEntity.ok().body(new MessageResponse("Cap nhat thoi gian thanh cong"));
        }
        return new ResponseEntity<>(new MessageResponse("Khong tim thay time thoa man"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        if (timeRepository.existsById(id)) {
            timeRepository.deleteById(id);
            return  ResponseEntity.ok().body(new MessageResponse("Xoa thoi gian thanh cong"));
        }
        return new ResponseEntity<>(new MessageResponse("Khong tim thay time thoa man"), HttpStatus.NOT_FOUND);
    }
}
