package com.softlabs.cerebral.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    long courseID;

    String courseName;
}
