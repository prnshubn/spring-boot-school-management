package com.spring.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.hackathon.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{

}