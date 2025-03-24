package org.isdb.StudentCRUD.controller;

import java.util.List;

import org.isdb.StudentCRUD.model.StudentClass;
import org.isdb.StudentCRUD.service.StudentClassService;
import org.isdb.StudentCRUD.dto.StudentClassDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class StudentClassController {

    private final StudentClassService studentClassService;

    public StudentClassController(StudentClassService studentClassService) {
        this.studentClassService = studentClassService;
    }

    @PostMapping
    public ResponseEntity<?> saveStudentClass(@RequestBody StudentClassDTO classDTO) {
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

}
