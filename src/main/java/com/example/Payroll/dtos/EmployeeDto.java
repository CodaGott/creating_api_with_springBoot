package com.example.Payroll.dtos;



import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
}
