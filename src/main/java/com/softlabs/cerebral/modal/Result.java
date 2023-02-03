package com.softlabs.cerebral.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Result {
    @Id
    long resultID;
    String studentName;
    char grade;
}
