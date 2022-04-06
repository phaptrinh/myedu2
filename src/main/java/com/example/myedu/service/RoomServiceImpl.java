package com.example.myedu.service;

import com.example.myedu.entity.Room;
import com.example.myedu.model.request.RoomRequest;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public ResponseEntity<?> createNewRoom(RoomRequest roomRequest) {
        if (roomRepository.existsByName(roomRequest.getName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Phong da ton tai"));

        }
        Room room = Room.builder()
                .name(roomRequest.getName())
                .capacity(roomRequest.getCapacity())
                .build();
        roomRepository.save(room);
        return ResponseEntity.ok(new MessageResponse("Tao phong thanh cong"));
    }

    @Override
    public ResponseEntity<?> updateById(Integer id, RoomRequest roomRequest) {
        if (roomRepository.existsById(id)) {
            Room room = roomRepository.getById(id);
            room.setName(roomRequest.getName());
            room.setCapacity(roomRequest.getCapacity());
            roomRepository.save(room);
            return ResponseEntity.ok().body(new MessageResponse("Cap nhat thanh cong"));
        }
        return new ResponseEntity<>(new MessageResponse("Khong tim thay room id"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("Xoa thanh cong"));
        }
        return new ResponseEntity<>(new MessageResponse("Khong tim thay room id"), HttpStatus.NOT_FOUND);
    }
}
