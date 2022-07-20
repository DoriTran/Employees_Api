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
        Integer nextAdvanceNo = advancesRepository.getMaxAdvanceNoByEmployeeNo(newAdvance.getEmployeeNo());
        if (nextAdvanceNo != null) newAdvance.setAdvanceNo(nextAdvanceNo + 1);
        else newAdvance.setAdvanceNo(1);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Add new advance to employee successfully", advancesRepository.save(newAdvance))
        );
    }

    // Delete advance day
    @DeleteMapping("delete/profile={employeeNo}/advanceNo={advanceNo}")
    ResponseEntity<ResponseObject> deleteAdvance(@PathVariable("employeeNo") Integer employeeNo, @PathVariable("advanceNo") Integer advanceNo) {
        List<Advance> exists = advancesRepository.getAdvanceByNo(employeeNo, advanceNo);

        if (exists.size() > 0) {
            advancesRepository.deleteByNo(employeeNo, advanceNo);
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
