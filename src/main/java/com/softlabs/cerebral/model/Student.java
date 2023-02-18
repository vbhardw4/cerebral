package com.softlabs.cerebral.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long studentID;
    String firstName;
    String lastName;
    Date dob;
    @Column(nullable=true)
    int age;
}
