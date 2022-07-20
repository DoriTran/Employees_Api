package api.EmployeesApi.models;
import javax.persistence.*;

@Entity
@Table(name="teams")
public class Team {
    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamNo;

    // Columns
    @Column(nullable = false, unique = true, length = 255)
    private String teamName;

    // Constructor
    public Team() { /* Do nothing */ }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(Integer teamNo, String teamName) {
        teamNo = teamNo;
        teamName = teamName;
    }

    // Getter & Setter
    public Integer getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(Integer teamNo) {
        this.teamNo = teamNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    // Override
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
