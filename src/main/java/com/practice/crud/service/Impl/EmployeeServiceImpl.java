package com.practice.crud.service.Impl;

import com.practice.crud.dto.AddressResponseDto;
import com.practice.crud.dto.EmployeeDto;
import com.practice.crud.entity.Employee;
import com.practice.crud.exception.EmployeeAllReadyExistsException;
import com.practice.crud.exception.ResourceNotFound;
import com.practice.crud.mapper.EmployeeMapper;
import com.practice.crud.repository.EmpRepo;
import com.practice.crud.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmpRepo empRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    public EmployeeServiceImpl(EmpRepo empRepo) {
        this.empRepo = empRepo;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        if (empRepo.findByFirstNameAndEmailAddress(employee.getFirstName(), employee.getEmail()).isPresent()) {
            throw new EmployeeAllReadyExistsException("Employee with name " + employeeDto.getFirstName() + " and email " + employeeDto.getEmail() + " already exists.");
        }
        Employee savedEmployee=empRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Employee employee=empRepo.findById(empId)
                .orElseThrow(()-> new ResourceNotFound("Employee does Not exist "+empId));
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

    @Override
    @Transactional
    public EmployeeDto createEmployeeWithSpecificId(EmployeeDto employeeDto, Long id) {
        Employee existingEmployee = empRepo.findById(id).orElse(null);
        if (existingEmployee != null) {
            throw new EmployeeAllReadyExistsException("Employee with ID " + id + " already exists.");
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employee.setId(id); // Manually set the ID
        Employee savedEmployee = empRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmpAddressById(Long id) {

        Employee employee =empRepo.findById(id).get();
        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
        AddressResponseDto addressResponseDto =restTemplate.getForObject("http://localhost:9091/api/address/{id}",AddressResponseDto.class,id);
        employeeDto.setAddressResponseDto(addressResponseDto);
        return employeeDto;
    }


}