package api.EmployeesApi.models;
import javax.persistence.*;

import api.EmployeesApi.models.composite_keys.WorkingKey;

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
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 // increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Integer workingNo;

    // Columns
    @Column(nullable = false)
    private java.util.Date date;
    @Column(nullable = false)
    private Double hour;

    // Constructor
    public Working() {
    }

    public Working(Integer employeeNo, Integer workingNo, Date date, Double hour) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
