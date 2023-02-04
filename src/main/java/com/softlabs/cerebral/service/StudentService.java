package com.softlabs.cerebral.service;

import com.softlabs.cerebral.modal.Student;

import com.softlabs.cerebral.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
}
