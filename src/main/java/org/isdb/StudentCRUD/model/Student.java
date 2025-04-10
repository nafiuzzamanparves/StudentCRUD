package org.isdb.StudentCRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.isdb.StudentCRUD.dto.CreateStudentDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "STUDENT_SB")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String clazz;
    private int age;
    private String address;
    private LocalDate dob;

    @SuppressWarnings("all")
    public Student(CreateStudentDto dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.age = dto.getAge();
        this.clazz = dto.getClazz();
        this.dob = dto.getDob();
    }

}
