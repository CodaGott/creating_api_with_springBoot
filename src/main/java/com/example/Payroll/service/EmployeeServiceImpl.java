package com.example.Payroll.service;

import com.example.Payroll.dtos.EmployeeDto;
import com.example.Payroll.exception.EmployeeException;
import com.example.Payroll.model.Employee;
import com.example.Payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) throws EmployeeException {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(employeeDto.getEmail());

        Employee employee = new Employee();

        if (optionalEmployee.isPresent()){
            throw new EmployeeException("User already exist");
        }else{
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee, String email) {
//        Optional<Employee> employeeToUpdate = employeeRepository.findEmployeeByEmail(email);

        Employee updateThis = employeeRepository.findEmployeeByEmail(email) //
        .map(employeeToUpdate -> {
            employeeToUpdate.setFirstName(employee.getFirstName());
            employeeToUpdate.setLastName(employee.getLastName());
            employeeToUpdate.setEmail(employee.getEmail());
            return employeeRepository.save(employeeToUpdate);
        }) //
        .orElseGet(() ->{
            employee.setEmail(email);
            return employeeRepository.save(employee);
        });




//        if(employeeToUpdate.isEmpty()){
//            throw new NoSuchElementException("Looks like your not registered with us yet check the email and try again");
//        }else {
//            employee.setFirstName(employee.getFirstName());
//            employee.setLastName(employee.getLastName());
//            employee.setEmail(employee.getEmail());
//            employeeRepository.save(employee);
//        }
//        employeeRepository.save(employeeToUpdate.get());
        //TODO 2 ways of updating
//        employeeToUpdate.ifPresent(
//                editEmployee ->{
//                    if (employee.getFirstName() != null)
//                        employee.setFirstName(employee.getFirstName());
//
//                    if(employee.getLastName() != null)
//                        employee.setLastName(employee.getLastName());
//                    if (employee.getEmail() != null)
//                        employee.setEmail(employee.getEmail());
//                }
//        );
//        employeeRepository.save(employeeToUpdate.get());
    }

    @Override
    public void deleteEmployee(String email) throws EmployeeException {
        Optional<Employee>  employeeToDelete = employeeRepository.findEmployeeByEmail(email);
        if(employeeToDelete.isPresent()){
            employeeRepository.deleteEmployeeByEmail(email);
        }else{
            throw new EmployeeException("Employee does not exist");
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findByEmail(String email) throws EmployeeException {
        if(email.isEmpty()){
            throw new EmployeeException("User with email does not exist");
        }
        return employeeRepository.findEmployeeByEmail(email);
    }
}
