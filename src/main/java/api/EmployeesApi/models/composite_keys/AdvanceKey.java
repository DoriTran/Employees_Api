package api.EmployeesApi.models.composite_keys;

import java.io.Serializable;
import java.util.Objects;

public class AdvanceKey implements Serializable {
    private Integer employeeNo;
    private Integer advanceNo;

    public AdvanceKey() {
    }

    public AdvanceKey(Integer employeeNo, Integer advanceNo) {
        employeeNo = employeeNo;
        advanceNo = advanceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvanceKey that = (AdvanceKey) o;
        return employeeNo.equals(that.employeeNo) && advanceNo.equals(that.advanceNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, advanceNo);
    }
}
