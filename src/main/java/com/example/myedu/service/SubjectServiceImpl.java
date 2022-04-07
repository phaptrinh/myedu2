package com.example.myedu.service;

import com.example.myedu.entity.Subject;
import com.example.myedu.exception.CustomException;
import com.example.myedu.model.request.SubjectRequest;
import com.example.myedu.model.response.MessageResponse;
import com.example.myedu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> getAllBySubjectId(List<Integer> integers) {
        return subjectRepository.findAllBySubjectIdIn(integers);
    }

    @Override
    public ResponseEntity<?> createNewSubject(SubjectRequest subjectRequest) {
        if (subjectRepository.existsByName(subjectRequest.getName())) {
            throw new CustomException("Mon hoc da ton tai", HttpStatus.BAD_REQUEST);
        }
        subjectRepository.save(Subject.builder().name(subjectRequest.getName()).description(subjectRequest.getDescription()).build());
        return ResponseEntity.ok().body(new MessageResponse("Them mon hoc thanh cong"));
    }

    @Override
    public ResponseEntity<?> updateById(Integer id, SubjectRequest subjectRequest) {
        if (subjectRepository.existsByName(subjectRequest.getName())) {
            throw new CustomException("Mon hoc da ton tai", HttpStatus.BAD_REQUEST);
        }
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new CustomException("Khong tim thay mon hoc", HttpStatus.NOT_FOUND));
        subject.setName(subjectRequest.getName());
        subject.setDescription(subjectRequest.getDescription());
        subjectRepository.save(subject);
        return ResponseEntity.ok().body(new MessageResponse("Cap nhat mon hoc thanh cong"));
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("Xoa mon hoc thanh cong"));
        }
        else {
            throw new CustomException("Khong tim thay mon hoc", HttpStatus.NOT_FOUND);
        }
    }
}
