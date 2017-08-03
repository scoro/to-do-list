package pl.pollub.service;

import org.springframework.stereotype.Service;
import pl.pollub.model.user.DTO.UserDTO;
import pl.pollub.model.user.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
