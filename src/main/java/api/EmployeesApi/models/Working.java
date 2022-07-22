package api.EmployeesApi.models;
import javax.persistence.*;

import api.EmployeesApi.models.composite_keys.WorkingKey;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="workings")
@IdClass(WorkingKey.class)
public class Working {
    // Primary key
    @Id
    private Integer employeeNo;

    @Id
    private Integer workingNo;

    // Columns
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Double hour;

    // Constructor
    public Working() {
    }

    public Working(Integer employeeNo, Integer workingNo, LocalDate date, Double hour) {
        this.employeeNo = employeeNo;
        this.workingNo = workingNo;
        this.date = date;
        this.hour = hour;
    }

    // Getter & Setter
    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Integer employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getWorkingNo() {
        return workingNo;
    }

    public void setWorkingNo(Integer workingNo) {
        this.workingNo = workingNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

    // Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Working working = (Working) o;
        return employeeNo.equals(working.employeeNo) && workingNo.equals(working.workingNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, workingNo);
    }
}
