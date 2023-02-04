package com.softlabs.cerebral.repository;

import com.softlabs.cerebral.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRepository extends JpaRepository<Result, Long> {
}
