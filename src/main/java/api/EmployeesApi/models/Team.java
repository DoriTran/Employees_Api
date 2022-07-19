package api.EmployeesApi.models;
import javax.persistence.*;

@Entity
@Table(name="teams")
public class Team {
    // Primary key
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Integer teamNo;

    // Columns
    @Column(nullable = false, unique = true, length = 255)
    private String teamName;

    // Constructor
    public Team() { /* Do nothing */ }

    public Team(int teamNo, String teamName) {
        teamNo = teamNo;
        teamName = teamName;
    }

    // Getter & Setter
    public int getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(int teamNo) {
        teamNo = teamNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        teamName = teamName;
    }

    // Override
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
