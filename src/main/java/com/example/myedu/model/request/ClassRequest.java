package com.example.myedu.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {
    private int classId;
    private String name;
    private int teacherUserId;
    private int subjectId;
    private int roomId;
    private int timeId;
}
