package com.practice.crud.repository;

import com.practice.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpRepo extends JpaRepository<Employee,Long> {

    @Query(value = "SELECT * FROM empsdata e WHERE e.first_name = :firstName AND e.email = :email", nativeQuery = true)
    Optional<Employee> findByFirstNameAndEmailAddress(@Param("firstName") String firstName, @Param("email") String email);
}
