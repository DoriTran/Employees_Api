package api.EmployeesApi.controllers;

import api.EmployeesApi.models.Advance;
import api.EmployeesApi.models.ResponseObject;
import api.EmployeesApi.models.composite_keys.AdvanceKey;
import api.EmployeesApi.repositories.AdvancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/advance")
public class AdvanceController {
    // DI = Dependency Injection
    @Autowired
    private AdvancesRepository advancesRepository;

    // Get all advances by employee is
    @GetMapping("/profile={employeeNo}/all")
    List<Advance> getAdvanceByEmployeeID(@PathVariable Integer employeeNo) {
        return advancesRepository.findByEmployeeNo(employeeNo);
    }

    // Add new advance day to employee
    @PostMapping("/new")
    ResponseEntity<ResponseObject> insertAdvance(@RequestBody Advance newAdvance) {
        // CheckID
        Optional<Advance> foundAdvance = advancesRepository.findById(new AdvanceKey(newAdvance.getEmployeeNo(), newAdvance.getAdvanceNo()));
        return foundAdvance.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("fail", "Advance if of this employee already exist", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Add new advance to employee successfully", advancesRepository.save(newAdvance))
                );
    }

    // Delete advance day
    @DeleteMapping("delete/profile={employeeNo}/workingNo={workingNo}")
    ResponseEntity<ResponseObject> deleteAdvance(@PathVariable("employeeNo") Integer employeeNo, @PathVariable("advanceNo") Integer advanceNo) {
        boolean exists = advancesRepository.existsById(new AdvanceKey(employeeNo, advanceNo));
        if (exists) {
            advancesRepository.deleteById(new AdvanceKey(employeeNo, advanceNo));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete advance day successfully", "")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find advance day", "")
            );
        }
    }
}
