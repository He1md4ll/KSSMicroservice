package edu.hs.bremen.manager;

import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Manager to handle all user related operations.
 */
@Service
public class UserManager {

    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets user with unique user id.
     * If user does not exits a new one is created.
     * @param userUuid unique id of user
     * @return user
     */
    public UserEntity getUser(final String userUuid) {
        return Optional.ofNullable(userRepository
                .findByUuid(userUuid))
                .orElseGet(() -> createNewUser(userUuid));
    }

    /**
     * Creates and saves new user with unique id.
     * @param userUuid unique id of user
     * @return new user
     */
    private UserEntity createNewUser(String userUuid) {
        final UserEntity userEntity = new UserEntity.UserBuilder().withUuid(userUuid).build();
        userRepository.save(userEntity);
        return userEntity;
    }
}
