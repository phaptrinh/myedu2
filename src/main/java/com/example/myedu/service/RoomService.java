package com.example.myedu.service;

import com.example.myedu.entity.Room;
import com.example.myedu.model.request.RoomRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    ResponseEntity<?> createNewRoom(RoomRequest roomRequest);

    ResponseEntity<?> updateById(Integer id, RoomRequest roomRequest);

    ResponseEntity<?> deleteById(Integer id);
}
