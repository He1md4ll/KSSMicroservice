package edu.hs.bremen.manager;

import edu.hs.bremen.model.UserEntity;
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

    public UserEntity getUser(final String userUuid) {
        return Optional.ofNullable(userRepository
                .findByUuid(userUuid))
                .orElseGet(() -> createNewUser(userUuid));
    }

    private UserEntity createNewUser(String userUuid) {
        final UserEntity userEntity = new UserEntity.UserBuilder().withUuid(userUuid).build();
        userRepository.save(userEntity);
        return userEntity;
    }
}
