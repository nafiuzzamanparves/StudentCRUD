package org.isdb.StudentCRUD.repository;

import org.isdb.StudentCRUD.dao.ClassTeacherDTO;
import org.isdb.StudentCRUD.dao.ClassTeacherProjection;
import org.isdb.StudentCRUD.dao.ClassTeacherRecord;
import org.isdb.StudentCRUD.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JPA Specification
// How to call store procedure
public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {

    @Query("SELECT sc.name AS className, sc.classTeacher.name AS teacherName FROM StudentClass sc")
    List<ClassTeacherProjection> getAllClassTeacher();

    @Query("SELECT new org.isdb.StudentCRUD.dao.ClassTeacherDTO(sc.name, sc.classTeacher.name) FROM StudentClass sc")
    List<ClassTeacherDTO> fetchAllClassTeacherDTOs();

    @Query("SELECT new org.isdb.StudentCRUD.dao.ClassTeacherRecord(sc.name, sc.classTeacher.name) FROM StudentClass sc")
    List<ClassTeacherRecord> fetchAllClassTeacherRecords();
}
