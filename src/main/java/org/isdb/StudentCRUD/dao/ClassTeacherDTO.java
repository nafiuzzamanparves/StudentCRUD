package org.isdb.StudentCRUD.dao;

import lombok.Getter;

@Getter
public class ClassTeacherDTO {

    private String className;
    private String teacherName;

    public ClassTeacherDTO(String className, String teacherName) {
        this.className = className;
        this.teacherName = teacherName;
    }

}

