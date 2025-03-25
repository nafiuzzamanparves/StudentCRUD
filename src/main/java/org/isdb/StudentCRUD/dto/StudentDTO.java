package org.isdb.StudentCRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String name;
    private String email;
    private Integer classId;
    private Integer roll;
    private List<Integer> bookIds;
    private String phone;
    private String address;
    private String gender;
    private Instant dob;
}
