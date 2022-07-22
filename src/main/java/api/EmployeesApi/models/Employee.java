package api.EmployeesApi.models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="employees")
public class Employee {
    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    // Foreign Key
    private Integer teamNo;

    // Columns
    @Column(nullable = false, length = 255)
    private String fullName;
    @Column(nullable = false, length = 12)
    private String phone;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, length = 10)
    private String sex;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false, length = 255)
    private String address;
    @Column(nullable = false)
    private double moneyHour;
    @Column
    private String avatar;

    // Constructor
    public Employee() { /* Do nothing */ }

    public Employee(Integer no, Integer teamNo, String fullName, String phone, int age, String sex, Date startDate, String address, double moneyHour) {
        this.no = no;
        this.teamNo = teamNo;
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.startDate = startDate;
        this.address = address;
        this.moneyHour = moneyHour;
    }

    public Employee(Integer no, Integer teamNo, String fullName, String phone, int age, String sex, Date startDate, String address, double moneyHour, String avatar) {
        this.no = no;
        this.teamNo = teamNo;
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.startDate = startDate;
        this.address = address;
        this.moneyHour = moneyHour;
        this.avatar = avatar;
    }

    // Getter & Setter
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(Integer teamNo) {
        this.teamNo = teamNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMoneyHour() {
        return moneyHour;
    }

    public void setMoneyHour(double moneyHour) {
        this.moneyHour = moneyHour;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    // Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return no == employee.no;
    }
}
