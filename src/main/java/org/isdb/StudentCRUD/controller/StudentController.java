package org.isdb.StudentCRUD.controller;

import java.util.List;
import java.util.Optional;

import org.isdb.StudentCRUD.dto.CreateStudentDto;
import org.isdb.StudentCRUD.model.Student;
import org.isdb.StudentCRUD.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/student")
public class StudentController {

	@Value("${iqram.env}")
	private String env;

	@Autowired
	private StudentService service;

	@PostMapping
	public Student saveStudent(@RequestBody CreateStudentDto studentDto) {
		// return service.saveStudent(new Student(studentDto));
		return null;
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

			// if (st.getAge() != studentDto.getAge())
			// 	st.setAge(studentDto.getAge());

			// if (st.getClazz() != studentDto.getClazz())
			// 	st.setClazz(studentDto.getClazz());

			// if (st.getDob() != studentDto.getDob())
			// 	st.setDob(studentDto.getDob());
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

	@GetMapping("/hello")
	public String hello() {
		return "Hello " + env;
	}
}
