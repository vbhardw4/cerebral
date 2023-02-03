package com.softlabs.cerebral.controller;

import com.softlabs.cerebral.modal.Result;
import com.softlabs.cerebral.modal.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/results")
public class ResultsController {

    @PostMapping
    public void addResults(@RequestBody Result result) {

    }

    @GetMapping("/{studentID}")
    public ResponseEntity<Student> fetchStudentResult(@PathVariable Long studentID) {
        return null;
    }
}
