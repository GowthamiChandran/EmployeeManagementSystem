package com.practice.crud.service;

import com.practice.crud.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long empId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updatedEmployee(Long empId,EmployeeDto updatedEmployee);

    void deleteEmployee(Long empId);

}
