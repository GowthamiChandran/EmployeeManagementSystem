package com.practice.crud.controller;

import com.practice.crud.dto.EmployeeDto;
import com.practice.crud.exception.ResourceNotFound;
import com.practice.crud.service.EmployeeService;
import com.practice.crud.service.Impl.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    private EmployeeServiceImpl employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<EmployeeDto> createEmployeeWithSpecificId(@RequestBody EmployeeDto employeeDto,@PathVariable("id") Long id){
        EmployeeDto savedEmployeeDto=employeeService.createEmployeeWithSpecificId(employeeDto,id);
        return new ResponseEntity<>(savedEmployeeDto,HttpStatus.CREATED);
    }

    //Build Get REST API
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long empId)  {
        EmployeeDto employeeDto= employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(employeeDto);
    }

   //Build GetAllEmployees REST API
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return  ResponseEntity.ok(employeeDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmpDetails(@PathVariable("id") Long empId,@RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployee = employeeService.updatedEmployee(empId,employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId){
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee got removed");
    }

    @GetMapping("/empaddress/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeAddressById(@PathVariable("id") Long id){

        EmployeeDto employeeDto = employeeService.getEmpAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

}
