package pl.pollub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.model.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
