package com.example.Payroll.repository;

import com.example.Payroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    void deleteEmployeeByEmail(String email);
    Optional<Employee> findEmployeeByEmail(String email);
    List<Employee> findEmployeesByFirstNameOrOrderByFirstName(String name);
}
