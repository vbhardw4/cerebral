package com.softlabs.cerebral.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Result {
    @Id
    long resultID;
    String studentName;
    String courseName;
    char grade;
}
