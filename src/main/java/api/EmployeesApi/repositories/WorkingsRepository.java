package api.EmployeesApi.repositories;

import api.EmployeesApi.models.Working;
import api.EmployeesApi.models.composite_keys.WorkingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkingsRepository extends JpaRepository<Working, WorkingKey> {
    List<Working> findByEmployeeNo(Integer EmployeeNo);
    void deleteByEmployeeNo(Integer EmployeeNo);
}
