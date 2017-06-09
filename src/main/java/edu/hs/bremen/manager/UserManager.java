package edu.hs.bremen.manager;

import edu.hs.bremen.model.User;
import edu.hs.bremen.model.dto.UserDto;
import edu.hs.bremen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManager {

    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(final UserDto userDto) {
        return Optional.ofNullable(userRepository
                .findUserByUuid(userDto.getUserUuid()))
                .orElse(createNewUser(userDto));
    }

    private User createNewUser(UserDto userDto) {
        final User user = new User.UserBuilder().withUuid(userDto.getUserUuid()).build();
        userRepository.save(user);
        return user;
    }
}
