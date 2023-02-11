package com.softlabs.cerebral.component;

import com.softlabs.cerebral.model.Student;

public class StudentUpdatedEvent {
    private final Student student;

    public StudentUpdatedEvent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}