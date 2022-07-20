package api.EmployeesApi.controllers;

import api.EmployeesApi.models.Employee;
import api.EmployeesApi.models.ResponseObject;
import api.EmployeesApi.repositories.AdvancesRepository;
import api.EmployeesApi.repositories.EmployeesRepository;
import api.EmployeesApi.repositories.WorkingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    // DI = Dependency Injection
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private WorkingsRepository workingsRepository;
    @Autowired
    private AdvancesRepository advancesRepository;

    // Get all employees
    @GetMapping("/all")
    List<Employee> getAllEmployees() { return employeesRepository.findAll(); }

    // Get detail of an employee
    @GetMapping("/profile/id={id}")
    ResponseEntity<ResponseObject> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> foundEmployee = employeesRepository.findById(id);

        return foundEmployee.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Found employee has id = " + id, foundEmployee)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("fail", "Cant find employee has id = " + id, "")
                );
    }

    // Insert new employee
    @PostMapping("/new")
    ResponseEntity<ResponseObject> insertEmployee(@RequestBody Employee newEmployee) {

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Add new employee successfully", employeesRepository.save(newEmployee))  );
    }

    // Update employee
    @PutMapping("/update/id={id}")
    ResponseEntity<ResponseObject> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {
        Optional<Employee> updateEmployee = employeesRepository.findById(id)
                .map(employee -> {
                    employee.setFullName(newEmployee.getFullName());
                    employee.setAddress(newEmployee.getAddress());
                    employee.setAge(newEmployee.getAge());
                    employee.setMoneyHour(newEmployee.getMoneyHour());
                    employee.setPhone(newEmployee.getPhone());
                    employee.setSex(newEmployee.getSex());
                    employee.setStartDate(newEmployee.getStartDate());
                    employee.setTeamNo(newEmployee.getTeamNo());
                    return employeesRepository.save(employee);
                });

        if (updateEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update employee successfully", updateEmployee)
            );
        }
        else {
            newEmployee.setNo(id);
            return insertEmployee(newEmployee);
        }
    }

    // Delete employee
    @DeleteMapping("delete/id={id}")
    ResponseEntity<ResponseObject> deleteEmployee(@PathVariable Integer id) {
        boolean exists = employeesRepository.existsById(id);
        if (exists) {
            // Delete Working
            workingsRepository.deleteByEmployeeNo(id);
            //> Delete Advance
            advancesRepository.deleteByEmployeeNo(id);

            // Delete Employee
            employeesRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete employee successfully", "")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find employee has id = " + id, "")
            );
        }
    }
}
