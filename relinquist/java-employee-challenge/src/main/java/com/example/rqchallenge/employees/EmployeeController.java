package com.example.rqchallenge.employees;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController implements IEmployeeController{

    private final String BASE_URL = "https://dummy.restapiexample.com/api/v1";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        String url = BASE_URL + "/employees";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
        List<Employee> employeeList = Arrays.asList(response.getBody());
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) {
        String url = BASE_URL + "/employees";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
        List<Employee> filteredEmployees = Arrays.stream(response.getBody())
                .filter(emp -> emp.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        String url = BASE_URL + "/employee/" + id;
        ResponseEntity<Employee> response = restTemplate.getForEntity(url, Employee.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        String url = BASE_URL + "/employees";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
        Integer highestSalary = Arrays.stream(response.getBody())
                .map(Employee::getSalary)
                .map(Integer::valueOf)
                .max(Integer::compareTo)
                .orElse(0);
        return ResponseEntity.ok(highestSalary);
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        String url = BASE_URL + "/employees";

        ResponseEntity<Employee[]> response = restTemplate.getForEntity(url, Employee[].class);
        Employee[] employees = response.getBody();

        if (employees == null) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employees));

        employeeList.sort((emp1, emp2) -> {
            int salary1 = Integer.parseInt(emp1.getSalary());
            int salary2 = Integer.parseInt(emp2.getSalary());
            return Integer.compare(salary2, salary1);
        });

        List<String> topTenEmployees = new ArrayList<>();

        for (int i = 0; i < Math.min(10, employeeList.size()); i++) {
            topTenEmployees.add(employeeList.get(i).getName());
        }

        return ResponseEntity.ok(topTenEmployees);
    }

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput) {
        String url = BASE_URL + "/create";
        ResponseEntity<Employee> response = restTemplate.postForEntity(url, employeeInput, Employee.class);
        return ResponseEntity.ok(response.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
        String url = BASE_URL + "/delete/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("Employee with id " + id + " deleted successfully.");
    }

}
