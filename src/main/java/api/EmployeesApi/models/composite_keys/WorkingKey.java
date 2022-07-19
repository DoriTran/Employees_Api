package api.EmployeesApi.models.composite_keys;

import java.io.Serializable;
import java.util.Objects;

public class WorkingKey implements Serializable {
    private Integer employeeNo;
    private Integer workingNo;

    public WorkingKey() {
    }

    public WorkingKey(Integer employeeNo, Integer workingNo) {
        employeeNo = employeeNo;
        workingNo = workingNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingKey that = (WorkingKey) o;
        return employeeNo.equals(that.employeeNo) && workingNo.equals(that.workingNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, workingNo);
    }
}
