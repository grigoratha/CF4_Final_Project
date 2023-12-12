package com.athgri.finalproject.dtoController;

import java.util.List;
import com.athgri.finalproject.model.User;
import com.athgri.finalproject.daoService.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * The UserController class handles user-related API endpoints.
 */
@Tag(name = "User API")
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService the service responsible for user-related operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing the list of users or a NOT_FOUND status if the list is empty
     */
    @Operation(summary = "Get All Users", description = "Get the details of all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Error no user details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to be retrieved
     * @return ResponseEntity containing the user or a NOT_FOUND status if the user is not found
     */
    @Operation(summary = "Get User By ID", description = "Get the user details of the specified user's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Error no user details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/find/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to be retrieved
     * @return ResponseEntity containing the user or a NOT_FOUND status if the user is not found
     */
    @Operation(summary = "Get User By Username", description = "Get the user details of the specified user's Username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Error no user details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/find/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Adds a new user.
     *
     * @param user the user to be added
     * @return ResponseEntity containing the added user or a NOT_ACCEPTABLE status if the operation fails
     */
    @Operation(summary = "Add User", description = "Add user details of a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Error the user was not created"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);

        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Updates an existing user.
     *
     * @param id   the unique identifier of the user to be updated
     * @param user the updated user information
     * @return ResponseEntity containing the updated user or a NOT_ACCEPTABLE status if the operation fails
     */
    @Operation(summary = "Update User By ID", description = "Update the user details of the specified user's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Error no user details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User currentUser = userService.updateUser(id, user);

        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to be deleted
     * @return ResponseEntity indicating the result of the deletion operation
     */
    @Operation(summary = "Delete User", description = "Delete the user details of the specified user's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Error no user details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        boolean deleteOperation = userService.deleteUser(id);

        if (!deleteOperation) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}