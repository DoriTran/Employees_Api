package api.EmployeesApi.repositories;

import api.EmployeesApi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT * FROM employees WHERE TeamNo = :teamNo", nativeQuery = true)
    List<Employee> getAllEmployeesByTeamNo(@Param("teamNo") Integer TeamNo);
}
