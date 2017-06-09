package edu.hs.bremen.repository;

import edu.hs.bremen.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUuid(final String uuid);
}