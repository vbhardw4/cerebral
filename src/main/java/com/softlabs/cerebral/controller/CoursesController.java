package com.softlabs.cerebral.controller;

import com.softlabs.cerebral.modal.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {

    @PostMapping
    public void addCourse(@RequestBody Course course) {

    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return null;
    }
}
