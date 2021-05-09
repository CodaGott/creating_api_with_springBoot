package com.example.Payroll.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="Employee_table")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;


//    TODO Create new Employees, Update existing ones,  Delete Employees
//    TODO Find Employees (one, all, or search by simple or complex properties)
}
