package com.softlabs.cerebral.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlabs.cerebral.CerebralApplication;
import com.softlabs.cerebral.modal.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CerebralApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testAddStudent() throws JsonProcessingException {
        Student student = new Student();
        student.setStudentID(12L);
        student.setStudentName("Full Name");
        student.setDob(new Date());

        String studentJSON = new ObjectMapper().writeValueAsString(student);
        // send a POST request to the endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(studentJSON, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/students", request, String.class);

        // verify the response status and body
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(String.format("Student with id %d created",student.getStudentID()));

    }
}
