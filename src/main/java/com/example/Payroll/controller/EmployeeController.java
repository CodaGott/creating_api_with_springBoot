package com.example.Payroll.controller;

import com.example.Payroll.dtos.EmployeeDto;
import com.example.Payroll.exception.EmployeeException;
import com.example.Payroll.model.Employee;
import com.example.Payroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto){
        try{
            employeeService.createEmployee(employeeDto);
            return new ResponseEntity<>("Registered successfully", HttpStatus.CREATED);
        } catch (EmployeeException e) {
            return new ResponseEntity<>("Looks like your not registered with us yet check the email and try again", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> getAllEmployees(){
        employeeService.findAllEmployees();
        return new ResponseEntity<>("List of Employee(s) \n" + employeeService.findAllEmployees() ,HttpStatus.FOUND);
    }

    @PutMapping(value ="/employees/{email}")
    public ResponseEntity<?> updateEmployeeInfo(@RequestBody Employee employee, @PathVariable String email) throws EmployeeException {
        try {
            employeeService.updateEmployee(employee, email);
            return new ResponseEntity<>("Updated successfully", HttpStatus.ACCEPTED);
        }catch (EmployeeException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value ="/employees/{email}")
    public ResponseEntity<?> findEmployeeWithEmail(@RequestBody Employee employee, @PathVariable String email) {
        try {
            employeeService.findByEmail(email);
            return new ResponseEntity<>(employeeService.findByEmail(email), HttpStatus.FOUND);
        } catch (EmployeeException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
