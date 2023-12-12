package com.athgri.finalproject.dtoController;

import java.util.List;
import com.athgri.finalproject.model.UserSession;
import com.athgri.finalproject.daoService.UserSessionService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * The UserSessionController class handles user session-related API endpoints.
 */
@Tag(name = "User Session API")
@RestController
@RequestMapping("api/session")
public class UserSessionController {
    private final UserSessionService userSessionService;

    /**
     * Constructor for UserSessionController.
     *
     * @param userSessionService the service responsible for user session-related operations
     */
    public UserSessionController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    /**
     * Retrieves a list of all user sessions.
     *
     * @return ResponseEntity containing the list of user sessions or a NOT_FOUND status if the list is empty
     */
    @Operation(summary = "Get All Active Sessions", description = "Retrieve all active sessions details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Error no session details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<UserSession>> getAllUsers() {
        List<UserSession> userSessions = userSessionService.findAllSessions();

        if (userSessions.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userSessions, HttpStatus.OK);
    }

    /**
     * Retrieves a user session by its unique identifier.
     *
     * @param id the unique identifier of the user session to be retrieved
     * @return ResponseEntity containing the user session or a NOT_FOUND status if the user session is not found
     */
    @Operation(summary = "Get Session by ID", description = "Retrieve the session details of the specified user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/find/id/{id}")
    public ResponseEntity<UserSession> getSessionById(@PathVariable("id") Long id) {
        UserSession userSession = userSessionService.findSessionById(id);

        if (userSession == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userSession, HttpStatus.OK);
    }

    /**
     * Retrieves a user session by its token.
     *
     * @param token the token of the user session to be retrieved
     * @return ResponseEntity containing the user session or a NOT_FOUND status if the user session is not found
     */
    @Operation(summary = "Get Session by Token", description = "Retrieve the session details of the specified session token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Error the specified token does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/find/token/{token}")
    public ResponseEntity<UserSession> getSessionByToken(@PathVariable("token") String token) {
        UserSession userSession = userSessionService.findSessionByToken(token);

        if (userSession == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userSession, HttpStatus.OK);
    }

    /**
     * Adds a new user session.
     *
     * @param id the unique identifier of the user associated with the session
     * @return ResponseEntity containing the added user session or a NOT_ACCEPTABLE status if the operation fails
     */
    @Operation(summary = "Create session", description = "Create a new session for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/add/{id}")
    public ResponseEntity<UserSession> addUserSession(@PathVariable("id") Long id) {
        UserSession newUserSession = new UserSession(id, "null", "null");

        if (userSessionService.addUserSession(newUserSession) == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(newUserSession, HttpStatus.CREATED);
    }

    /**
     * Updates an existing user session.
     *
     * @param id the unique identifier of the user session to be updated
     * @return ResponseEntity containing the updated user session or a NOT_ACCEPTABLE status if the operation fails
     */
    @Operation(summary = "Update Session by ID", description = "Update the session details of an authenticated user by user's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/update/{id}")
    public ResponseEntity<UserSession> updateUserSession(@PathVariable("id") Long id) {
        UserSession currentUserSession = new UserSession(id, "null", "null");

        if (userSessionService.updateUserSession(currentUserSession) == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(currentUserSession, HttpStatus.OK);
    }

    /**
     * Deletes a user session by its unique identifier.
     *
     * @param id the unique identifier of the user session to be deleted
     * @return ResponseEntity indicating the result of the deletion operation
     */
    @Operation(summary = "Terminate Session by ID", description = "Terminate the session of the specified user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserSession(@PathVariable("id") Long id) {
        boolean deleteOperation = userSessionService.deleteUserSession(id);

        if (!deleteOperation) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}