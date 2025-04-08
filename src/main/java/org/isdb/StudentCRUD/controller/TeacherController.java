package org.isdb.StudentCRUD.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.isdb.StudentCRUD.model.Teacher;
import org.isdb.StudentCRUD.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
@Tag(name = "Teacher Controller", description = "API for teacher management")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher) {
        Teacher saved = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/addAll")
    public ResponseEntity<?> saveAllTeacher(@RequestBody List<Teacher> teachers) {
        List<Teacher> saved = teacherService.saveAllTeacher(teachers);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Integer id) {
        return teacherService.getTeacher(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id, teacher);
    }
}
