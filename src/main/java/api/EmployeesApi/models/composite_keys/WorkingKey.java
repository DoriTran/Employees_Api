package api.EmployeesApi.models.composite_keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WorkingKey implements Serializable {
    private static final long serialVersionUID = 1L;

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
        return Objects.equals(employeeNo, that.employeeNo) && Objects.equals(workingNo, that.workingNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, workingNo);
    }
}
