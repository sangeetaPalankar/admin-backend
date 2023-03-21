package com.capstone.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.application.model.Tests;

public interface TestRepo extends JpaRepository<Tests, Integer> {

}
