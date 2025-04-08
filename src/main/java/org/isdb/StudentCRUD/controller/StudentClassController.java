package org.isdb.StudentCRUD.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.isdb.StudentCRUD.dao.ClassTeacherProjection;
import org.isdb.StudentCRUD.dto.StudentClassDTO;
import org.isdb.StudentCRUD.model.StudentClass;
import org.isdb.StudentCRUD.service.StudentClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@Tag(name = "Class Controller", description = "API for class management")
public class StudentClassController {

    private final StudentClassService studentClassService;

    public StudentClassController(StudentClassService studentClassService) {
        this.studentClassService = studentClassService;
    }

    @PostMapping
    public ResponseEntity<?> saveStudentClass(@Valid @RequestBody StudentClassDTO classDTO) {
        StudentClass saved = studentClassService.saveStudentClass(classDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public StudentClass getStudentClass(@PathVariable Integer id) {
        return studentClassService.getStudentClass(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentClass(@PathVariable Integer id) {
        studentClassService.deleteStudentClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<StudentClass> getAllStudentClasses() {
        return studentClassService.getAllStudentClass();
    }

    @PutMapping("/{id}")
    public StudentClass updateStudentClass(@PathVariable Integer id, @RequestBody StudentClassDTO classDTO) {
        return studentClassService.updateStudentClass(id, classDTO);
    }

    @GetMapping("/getAllClassTeacher")
    public List<ClassTeacherProjection> getAllClassTeachers() {
        return studentClassService.getAllClassTeacher();
    }

}
