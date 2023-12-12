package com.athgri.finalproject.daoService;

import java.util.List;
import com.athgri.finalproject.model.User;
import com.athgri.finalproject.jpaInterface.UserJpaInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athgri.finalproject.exceptions.UserNotFoundException;

/**
 * The UserService class provides services related to user management.
 */
@Service
public class UserService {
    private final UserJpaInterface userJpaInterface;

    /**
     * Constructor for UserService.
     *
     * @param userJpaInterface the JPA interface for User entities
     */
    @Autowired
    public UserService(UserJpaInterface userJpaInterface) {

        this.userJpaInterface = userJpaInterface;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users
     */
    public List<User> findAllUsers() {
        return userJpaInterface.findAll();
    }

    /**
     * Retrieves a user by its unique identifier.
     *
     * @param id the unique identifier of the user
     * @return the user with the specified ID, or null if not found
     */
    public User findUserById(Long id) {

        if(!userJpaInterface.existsById(id)) {
            return null;
        }

        return userJpaInterface.findUserById(id);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to retrieve
     * @return the user with the specified username, or null if not found
     */
    public User findUserByUsername(String username) {

        return userJpaInterface.findUserByUsername(username);
    }

    /**
     * Adds a new user.
     *
     * @param user the user to add
     * @return the added user, or null if the specified username or email already exists
     */
    public User addUser(User user) {
        List<User> activeUsers = findAllUsers();

        String thisUsername = user.getUsername();
        String thisEmail = user.getEmail();

        String currentUsername = "";
        String currentEmail = "";

        if(!activeUsers.isEmpty()) {
            for (User currentUser: activeUsers) {
                currentUsername = currentUser.getUsername();
                currentEmail = currentUser.getEmail();

                if(thisUsername.equals(currentUsername) || thisEmail.equals(currentEmail)) {
                    return null;
                }
            }
        }

        String hashedPassword = "";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userJpaInterface.save(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id   the unique identifier of the user to update
     * @param user the user data to update
     * @return the updated user, or null if the specified user ID does not exist
     */
    public User updateUser(Long id, User user) {

        if(!userJpaInterface.existsById(id)) {

            return null;
        }

        //This logic implements the ability to be
        //able to update individual properties of the entity
        User currentUser = findUserById(id);

        if(user.getUsername() == null) {
            user.setUsername( currentUser.getUsername() );
        }

        if(user.getPassword() == null) {
            user.setPassword( currentUser.getPassword() );
        }
        else {
            String hashedPassword = "";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
        }

        if(user.getEmail() == null) {
            user.setEmail( currentUser.getEmail() );
        }

        if(user.getRole() == null) {
            user.setRole( currentUser.getRole() );
        }

        user.setId(id);

        return userJpaInterface.save(user);
    }

    /**
     * Deletes a user by its unique identifier.
     *
     * @param id the unique identifier of the user to delete
     * @return true if the user is successfully deleted, false otherwise
     */
    public boolean deleteUser(Long id) {

        if(!userJpaInterface.existsById(id)) {
            return false;
        }

        userJpaInterface.deleteById(id);
        return true;
    }
}