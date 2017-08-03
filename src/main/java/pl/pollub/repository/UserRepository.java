package pl.pollub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
