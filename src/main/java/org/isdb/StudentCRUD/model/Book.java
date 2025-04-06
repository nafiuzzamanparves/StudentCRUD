package org.isdb.StudentCRUD.model;
/*
 * Book
   id - mandatory
   name - mandatory
   author - optional
   publisher - optional
   class - mandatory
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 100)
    private String publisher;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private StudentClass clazz;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<Student> students = new HashSet<>();
}
