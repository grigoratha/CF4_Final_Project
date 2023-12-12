package com.athgri.finalproject.daoService;

import java.sql.SQLOutput;
import java.util.List;
import com.athgri.finalproject.model.User;
import com.athgri.finalproject.model.UserSession;
import com.athgri.finalproject.jpaInterface.UserJpaInterface;
import com.athgri.finalproject.jpaInterface.UserSessionJpaInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.athgri.finalproject.utilities.Log.logInfo;

/**
 * The UserSessionService class provides services related to user sessions.
 */
@Service
public class UserSessionService {
    private final UserJpaInterface userJpaInterface;
    private final UserSessionJpaInterface userSessionJpaInterface;

    /**
     * Constructor for UserSessionService.
     *
     * @param userJpaInterface        the JPA interface for User entities
     * @param userSessionJpaInterface the JPA interface for UserSession entities
     */
    @Autowired
    public UserSessionService(UserJpaInterface userJpaInterface, UserSessionJpaInterface userSessionJpaInterface) {
        this.userJpaInterface = userJpaInterface;
        this.userSessionJpaInterface = userSessionJpaInterface;
    }

    /**
     * Retrieves a list of all user sessions.
     *
     * @return a list of all user sessions
     */
    public List<UserSession> findAllSessions() {
        return userSessionJpaInterface.findAll();
    }

    /**
     * Retrieves a user session by its unique identifier.
     *
     * @param id the unique identifier of the user session
     * @return the user session with the specified ID, or null if not found
     */
    public UserSession findSessionById(Long id) {

        if(!userSessionJpaInterface.existsById(id)) {
            return null;
        }

        return userSessionJpaInterface.findSessionById(id);
    }

    /**
     * Retrieves a user session by its authentication token.
     *
     * @param token the authentication token of the user session to retrieve
     * @return the user session with the specified token, or null if not found
     */
    public UserSession findSessionByToken(String token) {

        return userSessionJpaInterface.findSessionByToken(token);
    }

    /**
     * Adds a new user session.
     *
     * @param userSession the user session to add
     * @return the added user session, or null if the specified user ID does not exist
     */
    public UserSession addUserSession(UserSession userSession) {
        User user = userJpaInterface.findUserById( userSession.getId() );

        if(user == null) {
            logInfo("UserSessionService::addUserSession", "User not found");
            //A session is saved only when the specified user ID exists
            return null;
        }

        userSession.setUsername( user.getUsername() );
        userSession.setRole( user.getRole() );

        logInfo("UserSessionService::addUserSession", "ID: " + userSession.getId());
        logInfo("UserSessionService::addUserSession", "Username: " + userSession.getUsername());
        logInfo("UserSessionService::addUserSession", "Token: " + userSession.getToken());

        List<UserSession> activeSessions = findAllSessions();

        Long currentId = 0L;
        Long thisId = userSession.getId();

        if(!activeSessions.isEmpty()) {
            for (UserSession currentUserSession: activeSessions) {

                currentId = currentUserSession.getId();

                if( thisId.equals(currentId) ) {

                    logInfo("UserSessionService::addUserSession", "A session ID already exists for this user");
                    return null;
                }
            }
        }

        return userSessionJpaInterface.save(userSession);
    }

    /**
     * Updates an existing user session.
     *
     * @param userSession the user session to update
     * @return the updated user session, or null if the specified user ID does not exist
     */
    public UserSession updateUserSession(UserSession userSession) {
        User user = userJpaInterface.findUserById( userSession.getId() );

        if(user == null) {
            //A session is updated only when the specified user ID exists
            return null;
        }

        userSession.setRole( user.getRole() );

        Long currentId = userSession.getId();

        if(!userSessionJpaInterface.existsById(currentId)) {
            return null;
        }

        userSession.setId(currentId);

        return userSessionJpaInterface.save(userSession);
    }

    /**
     * Deletes a user session by its unique identifier.
     *
     * @param id the unique identifier of the user session to delete
     * @return true if the user session is successfully deleted, false otherwise
     */
    public boolean deleteUserSession(Long id) {

        if(!userSessionJpaInterface.existsById(id)) {
            return false;
        }

        userSessionJpaInterface.deleteById(id);
        return true;
    }
}
