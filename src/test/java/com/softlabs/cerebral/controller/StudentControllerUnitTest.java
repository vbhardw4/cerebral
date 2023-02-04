package com.softlabs.cerebral.controller;


import com.softlabs.cerebral.modal.Student;
import com.softlabs.cerebral.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setStudentID(12L);
        String firstName = "first";
        String lastName = "name";
        student.setStudentName(firstName+" "+lastName);
        student.setDob(new Date());

        when(studentService.saveStudent(student)).thenReturn(student);
        ResponseEntity<String> responseEntity = studentController.addStudent(student);
        assertEquals(String.format("Student with id %d created",student.getStudentID()),responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

}
