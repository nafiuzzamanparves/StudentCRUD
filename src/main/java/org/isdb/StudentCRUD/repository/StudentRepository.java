package org.isdb.StudentCRUD.repository;

import java.util.List;

import org.isdb.StudentCRUD.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findAllByName(String name);

	List<Student> findAllByNameAndAge(String name, int age);

}
