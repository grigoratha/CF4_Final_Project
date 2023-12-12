package com.athgri.finalproject.daoService;

import com.athgri.finalproject.model.User;
import com.athgri.finalproject.model.UserSession;
import com.athgri.finalproject.jpaInterface.UserJpaInterface;
import com.athgri.finalproject.jpaInterface.UserSessionJpaInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import static com.athgri.finalproject.utilities.Log.logInfo;

/**
 * The AuthService class provides authentication services for users.
 */
@Service
public class AuthService {
    private final UserJpaInterface userJpaInterface;
    private final UserSessionJpaInterface userSessionJpaInterface;

    /**
     * Constructor for AuthService.
     *
     * @param userJpaInterface        the JPA interface for User entities
     * @param userSessionJpaInterface the JPA interface for UserSession entities
     */
    @Autowired
    public AuthService(UserJpaInterface userJpaInterface, UserSessionJpaInterface userSessionJpaInterface) {
        this.userJpaInterface = userJpaInterface;
        this.userSessionJpaInterface = userSessionJpaInterface;
    }

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username the username of the user to authenticate
     * @param password the password of the user to authenticate
     * @return a JSON string containing user information if authentication is successful,
     *         "authentication failed" if the provided password is incorrect,
     *         "user not found" if the user does not exist
     */
    public String authenticateUser(String username, String password) {
        User currentUser = userJpaInterface.findUserByUsername(username);

        if(currentUser != null) {

            logInfo("AuthService::authenticateUser()", "User Details: \n" + currentUser.toString());

            Long currentId = currentUser.getId();
            String currentRole = currentUser.getRole();

            String storedHash = currentUser.getPassword();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if ( passwordEncoder.matches(password, storedHash) ) {

                UserSession newUserSession = new UserSession(currentUser.getId(), currentUser.getUsername(), currentUser.getRole());

                logInfo("AuthService::authenticateUser()", "Session Details: \n" + newUserSession);

                userSessionJpaInterface.save(newUserSession);

                JSONObject jsonObject = new JSONObject();

                try {

                    jsonObject.put("id", currentId);
                    jsonObject.put("token", newUserSession.getToken());
                    jsonObject.put("role", currentRole);
                }
                catch (JSONException e) {

                    e.printStackTrace();

                    return "json error";
                }

                return jsonObject.toString();
            }
            else {

                return "authentication failed";
            }
        }

        return "user not found";
    }

    /**
     * Removes user authentication based on the provided user session ID.
     *
     * @param id the ID of the user session to remove
     * @return true if the user session is successfully removed, false otherwise
     */
    public boolean removeUserAuthentication(Long id) {

        if(!userSessionJpaInterface.existsById(id)) {
            return false;
        }

        userSessionJpaInterface.deleteById(id);
        return true;
    }
}
