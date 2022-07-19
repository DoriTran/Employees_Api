package api.EmployeesApi.repositories;

import api.EmployeesApi.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamsRepository extends JpaRepository<Team, Integer> {
    List<Team> findByTeamName(String TeamName);
}
