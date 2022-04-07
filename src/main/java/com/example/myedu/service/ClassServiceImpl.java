package com.example.myedu.service;

import com.example.myedu.entity.Class;
import com.example.myedu.entity.Enrollment;
import com.example.myedu.exception.CustomException;
import com.example.myedu.model.request.ClassRequest;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.repository.ClassRepository;
import com.example.myedu.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Override
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public List<Class> getTeacherClassesById(int id) {
        if (classRepository.findClassByTeacherUserId(id).isEmpty()) {
            throw new CustomException("Khong tim thay", HttpStatus.NOT_FOUND);
        }
        return classRepository.findClassByTeacherUserId(id);
    }

    @Override
    public Class getClassById(int id) {
        return classRepository.findByClassId(id).orElseThrow(() -> new CustomException("Khong tim thay lop nay", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Class> getStudentClassesById(int id) {
        List<Class> classes = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentByStudentUserId(id);
        for (Enrollment enrollment : enrollments) {
            classes.add(classRepository.getClassByClassId(enrollment.getClassId()));
        }
        return classes;
    }

    @Override
    public ResponseEntity<?> createNewClass(ClassRequest classRequest) {
        if (classRepository.findByTeacherUserIdAndSubjectIdAndTimeId(classRequest.getTeacherUserId(), classRequest.getSubjectId(), classRequest.getTimeId()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Lop hoc da ton tai"));
        }
        Class c = Class.builder()
                .name(classRequest.getName())
                .teacherUserId(classRequest.getTeacherUserId())
                .subjectId(classRequest.getSubjectId())
                .roomId(classRequest.getRoomId())
                .timeId(classRequest.getTimeId())
                .build();
        classRepository.save(c);

        return ResponseEntity.ok(new MessageResponse("Tao lop hoc thanh cong"));
    }

    @Override
    public List<Integer> getSubjectIdsByTeacherId(Integer teacherId) {
        if (classRepository.findSubjectIdByTeacherUserId(teacherId).isEmpty()) {
            throw new CustomException("Khong tim thay", HttpStatus.NOT_FOUND);

        }
        return classRepository.findSubjectIdByTeacherUserId(teacherId);
    }

    @Override
    public ResponseEntity<?> updateById(Integer id, ClassRequest classRequest) {
        if (classRepository.findByTeacherUserIdAndSubjectIdAndTimeId(classRequest.getTeacherUserId(), classRequest.getSubjectId(), classRequest.getTimeId()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Lop hoc da ton tai"));
        }
        Class c = classRepository.findByClassId(id).orElseThrow(()-> new CustomException("Khong tim thay lop hoc", HttpStatus.NOT_FOUND));
        c.setName(classRequest.getName());
        c.setTeacherUserId(classRequest.getTeacherUserId());
        c.setSubjectId(classRequest.getSubjectId());
        c.setRoomId(classRequest.getRoomId());
        c.setTimeId(classRequest.getTimeId());
        classRepository.save(c);
        return ResponseEntity.ok(new MessageResponse("Cap nhat lop hoc thanh cong"));
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
            return ResponseEntity.ok(new MessageResponse("Xoa lop hoc thanh cong"));

        }
        throw new CustomException("Khong tim thay lop hoc", HttpStatus.NOT_FOUND);
    }

}
