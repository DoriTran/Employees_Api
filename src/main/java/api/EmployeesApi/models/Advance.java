package api.EmployeesApi.models;

import api.EmployeesApi.models.composite_keys.AdvanceKey;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="advances")
@IdClass(AdvanceKey.class)
public class Advance {
    // Primary key
    @Id
    private Integer employeeNo;

    @Id
    private Integer advanceNo;

    // Columns
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false, length = 20)
    private String money;

    // Constructor
    public Advance() {
    }

    public Advance(Integer employeeNo, Integer advanceNo, LocalDate date, String money) {
        this.employeeNo = employeeNo;
        this.advanceNo = advanceNo;
        this.date = date;
        this.money = money;
    }

    // Getter & Setter
    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Integer employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getAdvanceNo() {
        return advanceNo;
    }

    public void setAdvanceNo(Integer advanceNo) {
        this.advanceNo = advanceNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    // Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advance advance = (Advance) o;
        return employeeNo.equals(advance.employeeNo) && advanceNo.equals(advance.advanceNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, advanceNo);
    }
}
