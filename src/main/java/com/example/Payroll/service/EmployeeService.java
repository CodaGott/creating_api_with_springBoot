package com.example.Payroll.service;

import com.example.Payroll.dtos.EmployeeDto;
import com.example.Payroll.exception.EmployeeException;
import com.example.Payroll.model.Employee;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee createEmployee(EmployeeDto newEmployee) throws EmptyStackException, EmployeeException;

    void updateEmployee(Employee employee, String email) throws EmployeeException;

    void deleteEmployee(String email) throws EmployeeException;

    List<Employee> findAllEmployees();

    Optional<Employee> findByEmail(String email) throws EmployeeException;
}
