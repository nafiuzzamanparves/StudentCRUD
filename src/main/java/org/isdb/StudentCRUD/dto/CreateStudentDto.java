package org.isdb.StudentCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {

    private String name;
    private String clazz;
    private int age;
    private String address;
    private LocalDate dob;

}
