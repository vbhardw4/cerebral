package com.softlabs.cerebral.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlabs.cerebral.CerebralApplication;
import com.softlabs.cerebral.model.Student;

import com.softlabs.cerebral.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CerebralApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private Student student;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
         this.student = createFakeStudent();
    }

    @AfterEach
    public void tearDown() {
        // Remove student from DB
        studentRepository.delete(student);
    }
    public Student createFakeStudent() {

        Student student = new Student();
        student.setStudentID(12L);
        String firstName = "first";
        String lastName = "name";
        student.setStudentName(firstName+" "+lastName);
        student.setDob(new Date());

        return student;
    }

    public void addStudent() {
        // To Add Student in the DB
        studentRepository.save(student);
    }

    @Test
    public void testAddStudent() throws JsonProcessingException {
        String studentJSON = new ObjectMapper().writeValueAsString(student);
        // send a POST request to the endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(studentJSON, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/students", HttpMethod.POST ,request, String.class);

        // verify the response status and body
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(String.format("Student with id %d created",student.getStudentID()));

    }

    @Test
    public void testUpdateStudent() throws JsonProcessingException {
        addStudent();
        // Updates the first name of the student
        student.setStudentName("updated First Name");
        String studentJSON = new ObjectMapper().writeValueAsString(student);
        // send a PUT request to the endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(studentJSON, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/students",HttpMethod.PUT,request,String.class);
        // verify the response status and body
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(String.format("Student with id %d updated with name %s",student.getStudentID(),student.getStudentName()));

    }

    @Test
    public void testDeleteStudent() throws JsonProcessingException {
        addStudent();
        String studentJSON = new ObjectMapper().writeValueAsString(student);
        // send a DELETE request to the endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(studentJSON, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/students",HttpMethod.DELETE,request,String.class);
        // verify the response status and body
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(String.format("Student with id %d deleted",student.getStudentID()));

    }

    @Test
    public void testUpdateStudent_studentNotFound() throws JsonProcessingException {

        String studentJSON = new ObjectMapper().writeValueAsString(student);
        // send a PUT request to the endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(studentJSON, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/students",HttpMethod.PUT,request,String.class);
        // verify the response status and body
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo(String.format("Student with id %d not found",student.getStudentID()));

    }

    @Test
    public void testDeleteStudent_studentNotFound() throws JsonProcessingException {

        String studentJSON = new ObjectMapper().writeValueAsString(student);
        // send a PUT request to the endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(studentJSON, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/students",HttpMethod.DELETE,request,String.class);
        // verify the response status and body
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo(String.format("Student with id %d not found",student.getStudentID()));

    }
}
