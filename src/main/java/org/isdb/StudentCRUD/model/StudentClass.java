package org.isdb.StudentCRUD.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_CLASS")
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "class_teacher", referencedColumnName = "id", nullable = false)
    private Teacher classTeacher;

    @Column(name = "room_number", nullable = false, unique = true, length = 10)
    private Integer roomNumber;
}
