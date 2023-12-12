package com.athgri.finalproject.jpaInterface;

import com.athgri.finalproject.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The UserSessionJpaInterface provides CRUD operations for the UserSession entity
 * and additional custom queries.
 */
public interface UserSessionJpaInterface extends JpaRepository<UserSession, Long> {

    /**
     * Retrieves a user session by its unique identifier.
     *
     * @param id the unique identifier of the user session
     * @return the user session with the specified ID, or null if not found
     */
    UserSession findSessionById(Long id);

    /**
     * Retrieves a user session by its authentication token.
     *
     * @param token the authentication token of the user session to retrieve
     * @return the user session with the specified token, or null if not found
     */
    UserSession findSessionByToken(String token);
}