package com.softlabs.cerebral.controller;

import com.softlabs.cerebral.modal.Student;
import com.softlabs.cerebral.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        Student response = studentService.saveStudent(student);
        return new ResponseEntity<String>(String.format("Student with id %d created",response.getStudentID()), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestBody Student student) {
        if(studentService.deleteStudent(student)) {
            return new ResponseEntity<String>(String.format("Student with id %d deleted",student.getStudentID()),HttpStatus.OK);
        }
        return new ResponseEntity<String>(String.format("Student with id %d not found",student.getStudentID()),HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<String> updateStudent(@RequestBody  Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if(updatedStudent==null) {
            return new ResponseEntity<String>(String.format("Student with id %d not found",student.getStudentID()),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(String.format("Student with id %d updated with name %s",updatedStudent.getStudentID(),updatedStudent.getStudentName()),HttpStatus.CREATED);
    }
}
