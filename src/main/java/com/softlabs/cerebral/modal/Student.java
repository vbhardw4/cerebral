package com.softlabs.cerebral.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Student {
    @Id
    long studentID;
    String studentName;
    Date dob;
}
