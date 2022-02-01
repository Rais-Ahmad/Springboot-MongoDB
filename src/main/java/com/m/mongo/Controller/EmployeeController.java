package com.m.mongo.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.m.mongo.Model.Employee;
import com.m.mongo.Repository.EmployeeRepository;
import com.m.mongo.Service.SequenceGeneratorService;
import com.m.mongo.Service.employeeService;
import com.m.mongo.Util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/emp")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    private final SequenceGeneratorService sequenceGeneratorService;
    private final employeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, SequenceGeneratorService sequenceGeneratorService, employeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.employeeService = employeeService;
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<Object> getAllEmployees() {
        return employeeService.AllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity < Object > getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
       return employeeService.addEmployee(employee);
    }

    @PutMapping("/update")
    public ResponseEntity <Object> updateEmployee(@Valid @RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }
}