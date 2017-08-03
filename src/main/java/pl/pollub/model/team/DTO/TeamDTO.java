package pl.pollub.model.team.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pollub.model.user.User;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamDTO {

    private final List<User> collaborators;
}
