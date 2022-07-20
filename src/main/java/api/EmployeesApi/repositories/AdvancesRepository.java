package api.EmployeesApi.repositories;

import api.EmployeesApi.models.Advance;
import api.EmployeesApi.models.composite_keys.AdvanceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AdvancesRepository extends JpaRepository<Advance, AdvanceKey> {
    List<Advance> findByEmployeeNo(Integer EmployeeNo);
    void deleteByEmployeeNo(Integer EmployeeNo);

    @Query(value = "SELECT * FROM advances WHERE EmployeeNo = :employeeNo AND AdvanceNo = :advanceNo", nativeQuery = true)
    List<Advance> getAdvanceByNo(@Param("employeeNo") Integer EmployeeNo, @Param("advanceNo") Integer AdvanceNo);

    @Modifying
    @Query(value = "DELETE FROM advances WHERE EmployeeNo = :employeeNo AND AdvanceNo = :advanceNo", nativeQuery = true)
    void deleteByNo(@Param("employeeNo") Integer EmployeeNo, @Param("advanceNo") Integer AdvanceNo);

    @Query(value = "SELECT MAX(AdvanceNo) FROM advances WHERE EmployeeNo = :selectedNo", nativeQuery = true)
    Integer getMaxAdvanceNoByEmployeeNo(@Param("selectedNo") Integer EmployeeNo);
}
