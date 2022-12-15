package com.example.employee.service;

import com.example.employee.api.EmployeesApiDelegate;
import com.example.employee.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeesApiDelegate {

    private List<Employee> employees;

    @Override
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<Employee> findById(String id) {
        Optional<Employee> employee = employees
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        if (!employee.isPresent())
            return ResponseEntity.ok(employee.get());

        return ResponseEntity.notFound().build();
    }

    @PostConstruct
    private void init() {
        employees = List.of(
                createEmployee("1", "Tom", "932332312"),
                createEmployee("2", "Mark", "454545545"),
                createEmployee("3", "Amy", "545454542")
        );
    }

    private Employee createEmployee(String id, String name, String phoneNo) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setPhoneNumber(phoneNo);
        employee.setCreatedate(LocalDate.now());
        return employee;
    }
}
