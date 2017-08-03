package pl.pollub.model.user.converter;

import org.springframework.core.convert.converter.Converter;
import pl.pollub.model.project.DTO.ProjectDTO;
import pl.pollub.model.project.Project;
import pl.pollub.model.user.DTO.UserDTO;
import pl.pollub.model.user.User;

public class UserDtoToEntityConverter implements Converter<UserDTO,User> {

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        return user;
    }
}
