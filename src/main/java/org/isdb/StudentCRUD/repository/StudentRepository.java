package org.isdb.StudentCRUD.repository;

import org.isdb.StudentCRUD.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByName(String name);

    List<Student> findAllByNameAndAge(String name, int age);

}
