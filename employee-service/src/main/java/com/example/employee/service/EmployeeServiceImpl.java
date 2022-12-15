package com.example.employee.service;

import com.example.employee.api.EmployeesApiDelegate;
import com.example.employee.model.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeesApiDelegate {

    private List<EmployeeDto> employees;

//    @Override
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<EmployeeDto> findById(String id) {
        Optional<EmployeeDto> employee = employees
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        return employee
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostConstruct
    private void init() {
        employees = List.of(
                createEmployee("1", "Tom", "932332312"),
                createEmployee("2", "Mark", "454545545"),
                createEmployee("3", "Amy", "545454542")
        );
    }

    private EmployeeDto createEmployee(String id, String name, String phoneNo) {
        EmployeeDto employee = new EmployeeDto();
        employee.setId(id);
        employee.setName(name);
        employee.setPhoneNumber(phoneNo);
        employee.setCreatedate(LocalDate.now());
        return employee;
    }
}
