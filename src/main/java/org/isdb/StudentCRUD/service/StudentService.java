package org.isdb.StudentCRUD.service;

import lombok.extern.slf4j.Slf4j;
import org.isdb.StudentCRUD.dto.StudentDTO;
import org.isdb.StudentCRUD.model.Student;
import org.isdb.StudentCRUD.model.StudentClass;
import org.isdb.StudentCRUD.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentClassService studentClassService;

    public StudentService(StudentRepository repository, StudentClassService studentClassService) {
        this.repository = repository;
        this.studentClassService = studentClassService;
    }

    public Student saveStudent(StudentDTO studentDTO) {
        // TODO: In next class add books to student (For now skip the part)
        Integer classId = studentDTO.getClassId();
        StudentClass clazz = studentClassService.getStudentClass(classId);

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        if (clazz != null)
            student.setClazz(clazz);
        student.setRoll(studentDTO.getRoll());
        student.setPhone(studentDTO.getPhone());
        student.setAddress(studentDTO.getAddress());
        student.setGender(studentDTO.getGender());
        student.setDob(studentDTO.getDob());

        return repository.save(student);
    }

    public Student getStudent(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }

    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    public Student updateStudent(Integer id, StudentDTO studentDTO) {
        Optional<Student> studentById = repository.findById(id);

        if (studentById.isPresent()) {
            Student aStudent = new Student();
            if (studentDTO.getName() != null) {
                aStudent.setName(studentDTO.getName());
            }
            if (studentDTO.getEmail() != null) {
                aStudent.setEmail(studentDTO.getEmail());
            }
            if (studentDTO.getClassId() != null) {
                Integer classId = studentDTO.getClassId();
                StudentClass clazz = studentClassService.getStudentClass(classId);
                if (clazz == null) {
                    throw new IllegalArgumentException("Class not found");
                }
                aStudent.setClazz(clazz);
            }
            if (studentDTO.getRoll() != null) {
                aStudent.setRoll(studentDTO.getRoll());
            }
            // if (studentDTO.getBookIds() != null) {
            // aStudent.setBooks(studentDTO.getBookIds());
            // }
            if (studentDTO.getPhone() != null) {
                aStudent.setPhone(studentDTO.getPhone());
            }
            if (studentDTO.getAddress() != null) {
                aStudent.setAddress(studentDTO.getAddress());
            }
            if (studentDTO.getGender() != null) {
                aStudent.setGender(studentDTO.getGender());
            }
            if (studentDTO.getDob() != null) {
                aStudent.setDob(studentDTO.getDob());
            }

            return repository.save(aStudent);
        } else {
            throw new IllegalArgumentException("Student not found");
        }
    }
}
