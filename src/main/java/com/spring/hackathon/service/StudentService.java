package com.spring.hackathon.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.hackathon.entity.Student;
import com.spring.hackathon.repository.StudentRepository;


@Service
public class StudentService
{

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getStudents()
	{
		return studentRepository.findAll();
	}
	
	public Student addStudent(Student student)
	{
		return studentRepository.save(student);
	}
	
	public Student getStudentById(Long id)
	{
		Student student = null;
		try
		{
			student = studentRepository.findById(id).orElseThrow(null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return student;
	}
	public ResponseEntity<Student> updateStudent(Long id, Student studentDetails)
	{
		Student student = null;
		try
		{
			student = studentRepository.findById(id).orElseThrow(null);
			student.setFirstname(studentDetails.getFirstname());
			student.setLastname(studentDetails.getLastname());
			student.setEmail(studentDetails.getEmail());
			Student updatedStudent = studentRepository.save(student);
			return ResponseEntity.ok(updatedStudent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	public void deleteStudent(Long id)
	{
		Student student = studentRepository.findById(id).orElseThrow(null);
		studentRepository.delete(student);	
	}
}
