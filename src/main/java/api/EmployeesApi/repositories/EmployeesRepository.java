package api.EmployeesApi.repositories;

import api.EmployeesApi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employee, Integer> {
}
