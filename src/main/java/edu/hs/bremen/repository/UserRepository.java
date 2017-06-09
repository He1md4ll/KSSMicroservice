package edu.hs.bremen.repository;

import edu.hs.bremen.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUuid(final String uuid);
}