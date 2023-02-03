package com.softlabs.cerebral.repository;

import com.softlabs.cerebral.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
