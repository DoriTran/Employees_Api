package api.EmployeesApi.controllers;

import api.EmployeesApi.models.Advance;
import api.EmployeesApi.models.ResponseObject;
import api.EmployeesApi.models.Working;
import api.EmployeesApi.models.composite_keys.WorkingKey;
import api.EmployeesApi.repositories.WorkingsRepository;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/working")
public class WorkingController {
    // DI = Dependency Injection
    @Autowired
    private WorkingsRepository workingsRepository;

    // Get all workings by employee id
    @GetMapping("/profile={employeeNo}/all")
    List<Working> getWorkingByEmployeeID(@PathVariable Integer employeeNo) {
        return workingsRepository.findByEmployeeNo(employeeNo);
    }

    // Add new working day to employee
    @PostMapping("/new")
    ResponseEntity<ResponseObject> insertWorking(@RequestBody Working newWorking) {
        Integer nextWorkingNo = workingsRepository.getMaxWorkingNoByEmployeeNo(newWorking.getEmployeeNo());
        if (nextWorkingNo != null) newWorking.setWorkingNo(nextWorkingNo + 1);
        else newWorking.setWorkingNo(1);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Add new working to employee successfully", workingsRepository.save(newWorking))
        );
    }

    // Delete working day
    @DeleteMapping("/delete/profile={employeeNo}/workingNo={workingNo}")
    ResponseEntity<ResponseObject> deleteWorking(@PathVariable("employeeNo") Integer employeeNo, @PathVariable("workingNo") Integer workingNo) {
        List<Working> exists = workingsRepository.getWorkingByNo(employeeNo, workingNo);
        if (exists.size() > 0) {
            workingsRepository.deleteByNo(employeeNo, workingNo);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete working day successfully", "")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find working day", "")
            );
        }
    }
}
