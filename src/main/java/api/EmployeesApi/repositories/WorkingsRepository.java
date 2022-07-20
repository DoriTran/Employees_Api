package api.EmployeesApi.repositories;

import api.EmployeesApi.models.Working;
import api.EmployeesApi.models.composite_keys.WorkingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WorkingsRepository extends JpaRepository<Working, WorkingKey> {
    List<Working> findByEmployeeNo(Integer EmployeeNo);
    void deleteByEmployeeNo(Integer EmployeeNo);

    @Query(value = "SELECT * FROM workings WHERE EmployeeNo = :employeeNo AND WorkingNo = :workingNo", nativeQuery = true)
    List<Working> getWorkingByNo(@Param("employeeNo") Integer EmployeeNo, @Param("workingNo") Integer WorkingNo);

    @Modifying
    @Query(value = "DELETE FROM workings WHERE EmployeeNo = :employeeNo AND WorkingNo = :workingNo", nativeQuery = true)
    void deleteByNo(@Param("employeeNo") Integer EmployeeNo, @Param("workingNo") Integer workingNo);

    @Query(value="SELECT MAX(WorkingNo) FROM workings WHERE EmployeeNo = :selectedNo", nativeQuery = true)
    Integer getMaxWorkingNoByEmployeeNo(@Param("selectedNo") Integer EmployeeNo);
}
