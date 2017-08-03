package pl.pollub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.model.team.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
