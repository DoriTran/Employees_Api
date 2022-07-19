package api.EmployeesApi.repositories;

import api.EmployeesApi.models.Advance;
import api.EmployeesApi.models.composite_keys.AdvanceKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvancesRepository extends JpaRepository<Advance, AdvanceKey> {
    List<Advance> findByEmployeeNo(Integer EmployeeNo);
    void deleteByEmployeeNo(Integer EmployeeNo);
}
