package org.isdb.StudentCRUD.service;

import org.isdb.StudentCRUD.dao.ClassTeacherProjection;
import org.isdb.StudentCRUD.dto.StudentClassDTO;
import org.isdb.StudentCRUD.model.StudentClass;
import org.isdb.StudentCRUD.model.Teacher;
import org.isdb.StudentCRUD.repository.StudentClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentClassService {

    private final StudentClassRepository studentClassRepository;
    private final TeacherService teacherService;

    public StudentClassService(StudentClassRepository studentClassRepository,
                               TeacherService teacherService) {
        this.studentClassRepository = studentClassRepository;
        this.teacherService = teacherService;
    }

    public StudentClass saveStudentClass(StudentClassDTO classDTO) {
        Integer teacherId = classDTO.getClassTeacherId();
        Teacher teacher = teacherService.getTeacher(teacherId);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found with id " + teacherId);
        }
        StudentClass studentClass = new StudentClass();
        studentClass.setName(classDTO.getName());
        studentClass.setRoomNumber(classDTO.getRoomNumber());
        studentClass.setClassTeacher(teacher);

        return studentClassRepository.save(studentClass);
    }

    public StudentClass getStudentClass(Integer id) {
        return studentClassRepository.findById(id).orElse(null);
    }

    public void deleteStudentClass(Integer id) {
        studentClassRepository.deleteById(id);
    }

    public List<StudentClass> getAllStudentClass() {
        return studentClassRepository.findAll();
    }

    public StudentClass updateStudentClass(Integer id, StudentClassDTO classDTO) {
        Optional<StudentClass> classById = studentClassRepository.findById(id);

        if (classById.isPresent()) {
            StudentClass aClass = new StudentClass();
            if (classDTO.getName() != null) {
                aClass.setName(classDTO.getName());
            }
            if (classDTO.getRoomNumber() != null) {
                aClass.setRoomNumber(classDTO.getRoomNumber());
            }
            if (classDTO.getClassTeacherId() != null) {
                Integer teacherId = classDTO.getClassTeacherId();
                Teacher teacher = teacherService.getTeacher(teacherId);
                if (teacher == null) {
                    throw new IllegalArgumentException("Teacher not found");
                }
                aClass.setClassTeacher(teacher);
            }
            return studentClassRepository.save(aClass);
        } else {
            throw new IllegalArgumentException("Class not found");
        }
    }

    public List<ClassTeacherProjection> getAllClassTeacher() {
        return studentClassRepository.getAllClassTeacher();
    }
}
