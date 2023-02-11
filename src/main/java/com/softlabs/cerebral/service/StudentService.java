package com.softlabs.cerebral.service;

import com.softlabs.cerebral.model.Student;

import com.softlabs.cerebral.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public boolean deleteStudent(Student student) {
        Optional<Student> entity = getStudent(student);
        if(!entity.isPresent()) {
            return Boolean.FALSE;
        }
        studentRepository.delete(student);
        return Boolean.TRUE;
    }

    public Student updateStudent(Student student) {
        Optional<Student> entity = getStudent(student);
        if(entity.isPresent()) {
            Student persistedEntity = entity.get();
            persistedEntity.setFirstName(student.getFirstName());
            persistedEntity.setLastName(student.getLastName());
            persistedEntity.setDob(student.getDob());
            persistedEntity.setAge(student.getAge());

            studentRepository.save(persistedEntity);
            return persistedEntity;
        }
        return null;
    }

    public Optional<Student> getStudent(Student student) {
        return studentRepository.findById(student.getStudentID());
    }
}
