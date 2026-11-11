package com.example.studentcourseregistration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.studentcourseregistration.entity.Student;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    private Long nextId = 1L;

    // Register Student
    public Student registerStudent(Student student) {
        student.setStudentId(nextId++);
        students.add(student);
        return student;
    }

    // View All Students
    public List<Student> getAllStudents() {
        return students;
    }

    // Find Student By ID
    public Student getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getStudentId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found with ID: " + id
                    )
                );
    }

    // Delete Student
    public String deleteStudent(Long id) {
        Student student = getStudentById(id);
        students.remove(student);
        return "Student deleted successfully";
    }
}