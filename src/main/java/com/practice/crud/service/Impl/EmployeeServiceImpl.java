package com.practice.crud.service.Impl;

import com.practice.crud.dto.EmployeeDto;
import com.practice.crud.entity.Employee;
import com.practice.crud.exception.ResourceNotFound;
import com.practice.crud.mapper.EmployeeMapper;
import com.practice.crud.repository.EmpRepo;
import com.practice.crud.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmpRepo empRepo;

    public EmployeeServiceImpl(EmpRepo empRepo) {
        this.empRepo = empRepo;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=empRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Employee employee=empRepo.findById(empId)
                .orElseThrow(()-> new ResourceNotFound("Employee does Not exist with given id"+empId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = empRepo.findAll();
        return employeeList.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updatedEmployee(Long empId, EmployeeDto updatedEmployee) {
        Employee employee = empRepo.findById(empId).orElseThrow(()->new ResourceNotFound("Resource Not Found for this Id"+empId));
        employee.setEmail(updatedEmployee.getEmail());
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        Employee updatedData= empRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedData);
    }

    @Override
    public void deleteEmployee(Long empId) {
        Employee employee = empRepo.findById(empId).orElseThrow(()->new ResourceNotFound("Resource Not Found for this Id"+empId));

        empRepo.deleteById(empId);

    }
}