package org.isdb.StudentCRUD.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.isdb.StudentCRUD.dto.CreateStudentDto;
import org.isdb.StudentCRUD.model.Student;
import org.isdb.StudentCRUD.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired // Field injection
    private StudentService service;

    // Dependency injection
    // Constructor injection
    // public StudentController(StudentService service) {
    // this.service = service;
    // }

    @PostMapping
    public Student saveStudent(@RequestBody CreateStudentDto studentDto) {
        return service.saveStudent(new Student(studentDto));
    }

    @GetMapping
    public List<Student> getStudents() {
        log.info("Calling getStudents");
        return service.getStudents();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody CreateStudentDto studentDto) {
        Optional<Student> existingStudent = service.findStudentById(id);
        Student st = null;
        if (existingStudent.isPresent()) {
            st = existingStudent.get();

            if (st.getName() != studentDto.getName())
                st.setName(studentDto.getName());

            if (st.getAddress() != studentDto.getAddress())
                st.setAddress(studentDto.getAddress());

            if (st.getAge() != studentDto.getAge())
                st.setAge(studentDto.getAge());

            if (st.getClazz() != studentDto.getClazz())
                st.setClazz(studentDto.getClazz());

            if (st.getDob() != studentDto.getDob())
                st.setDob(studentDto.getDob());
        }

        return service.saveStudent(st);
    }

    @GetMapping("/byName")
    public List<Student> getStudentByName(HttpServletRequest request, HttpServletResponse httpResponse,
                                          @RequestParam String name) {
        String hostName = request.getRemoteHost();
        System.out.println("Host Name : " + hostName);
        List<Student> students = service.getStudentsByName(name);
        return students;
    }
}
