package com.softlabs.cerebral.controller;


import com.softlabs.cerebral.model.Student;
import com.softlabs.cerebral.service.StudentService;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerUnitTest {

    @Autowired
    StudentController studentController;
    @MockBean
    private StudentService studentService;

    private Student student;

    @Before
    public void createStudent() {
        Student student = new Student();
        student.setStudentID(12L);
        String firstName = "first";
        String lastName = "name";
        student.setStudentName(firstName+" "+lastName);
        student.setDob(new Date());

        this.student = student;
    }
    @Test
    public void testAddStudent() {

        when(studentService.saveStudent(student)).thenReturn(student);
        ResponseEntity<String> responseEntity = studentController.addStudent(student);
        assertEquals(String.format("Student with id %d created",student.getStudentID()),responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void testUpdateStudent() {
        student.setStudentName("New Name");
        when(studentService.updateStudent(student)).thenReturn(student);
        ResponseEntity<String> responseEntity = studentController.updateStudent(student);
        assertEquals(String.format(String.format("Student with id %d updated with name %s",student.getStudentID(),student.getStudentName())),responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void testDeleteStudent() {
        when(studentService.deleteStudent(student)).thenReturn(Boolean.TRUE);
        ResponseEntity<String> responseEntity = studentController.deleteStudent(student);
        assertEquals(String.format("Student with id %d deleted",student.getStudentID()),responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

}
