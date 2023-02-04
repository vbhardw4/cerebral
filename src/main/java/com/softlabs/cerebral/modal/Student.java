package com.softlabs.cerebral.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Student {
    @Id
    long studentID;
    String studentName;
    Date dob;
}
