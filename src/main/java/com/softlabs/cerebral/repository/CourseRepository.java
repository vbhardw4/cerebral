package com.softlabs.cerebral.repository;

import com.softlabs.cerebral.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
