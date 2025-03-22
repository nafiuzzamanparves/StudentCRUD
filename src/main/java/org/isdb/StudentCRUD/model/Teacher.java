package org.isdb.StudentCRUD.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "T_TEACHER")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(name = "joining_date", nullable = false, updatable = false)
    private Instant joiningDate;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(name = "marital_status")
    private Boolean maritalStatus;
}
