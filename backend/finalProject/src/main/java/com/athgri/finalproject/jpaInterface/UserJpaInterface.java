package com.athgri.finalproject.jpaInterface;

import com.athgri.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The UserJpaInterface provides CRUD operations for the User entity
 * and additional custom queries.
 */
public interface UserJpaInterface extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by its unique identifier.
     *
     * @param id the unique identifier of the user
     * @return the user with the specified ID, or null if not found
     */


    /**
     * Retrieves a user by its unique identifier.
     *
     * @param id the unique identifier of the user
     * @return the user with the specified ID, or null if not found
     */
    User findUserById(Long id);

    User findUserByUsername(String username);
}
