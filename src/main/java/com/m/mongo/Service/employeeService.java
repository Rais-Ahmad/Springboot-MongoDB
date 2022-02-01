package com.m.mongo.Service;

import com.m.mongo.Model.Employee;
import com.m.mongo.Repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class employeeService {
    private final EmployeeRepository employeeRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public employeeService(EmployeeRepository employeeRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.employeeRepository = employeeRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }


    public ResponseEntity<Object> AllUsers(){
      List<Employee> empList =  employeeRepository.findAll();
        return new ResponseEntity<>(empList, HttpStatus.OK);
    }

    public ResponseEntity<Object> findById(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent())
        return new ResponseEntity<>(employee, HttpStatus.OK);
        else  return new ResponseEntity<>("Employee not found!", HttpStatus.OK);

    }

    public ResponseEntity<Object> addEmployee(Employee employee){
       employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        employeeRepository.save(employee);
        return  new ResponseEntity<>("Employee: " + employee.getFirstName() + " is added", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateEmployee(Employee employee){
        employeeRepository.save(employee);
        return  new ResponseEntity<>("Employee has been updated!", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteEmployee(long id){
        employeeRepository.deleteById(id);
        return new ResponseEntity<>("Employee with id: " + id + " has been deleted successfully", HttpStatus.OK);
    }

}
