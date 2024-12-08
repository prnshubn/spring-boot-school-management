package com.spring.hackathon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.spring.hackathon.entity.Student;
import com.spring.hackathon.service.StudentService;


@RestController
@RequestMapping("/api")
public class StudentController
{
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> listStudents()
	{
		List<Student> students = studentService.getStudents();
		if(students.size()==0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(students));
	}
	
	@PostMapping("/student")
	public  ResponseEntity<Student> createStudent(@RequestBody Student student)
	{
		Student  updatedStudent = null;
		try
		{
			updatedStudent = studentService.addStudent(student);
			System.out.println(updatedStudent);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Student> deleteStudnetValue(@PathVariable Long id)
	{	
		try
		{
			studentService.deleteStudent(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} 
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentByIdValue(@PathVariable Long id)
	{
		Student student = studentService.getStudentById(id);
		if(student==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(student));
	}
	
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateTheStudents(@PathVariable Long id, @RequestBody Student student)
	{
		return studentService.updateStudent(id, student);
	}
}
