package pl.pollub.service;

import pl.pollub.model.user.DTO.UserDTO;
import pl.pollub.model.user.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);

    List<User> getAllUsers();
}
