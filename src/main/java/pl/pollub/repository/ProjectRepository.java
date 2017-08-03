package pl.pollub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.model.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
