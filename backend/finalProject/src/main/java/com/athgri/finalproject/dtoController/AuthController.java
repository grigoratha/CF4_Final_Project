package com.athgri.finalproject.dtoController;

import com.athgri.finalproject.model.Auth;
import com.athgri.finalproject.daoService.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.athgri.finalproject.utilities.Log.logInfo;

/**
 * The AuthController class handles authentication-related API endpoints.
 */
@Tag(name = "User Authentication API")
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * Constructor for AuthController.
     *
     * @param authService the service responsible for authentication operations
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param authPayload the authentication payload containing username and password
     * @return ResponseEntity indicating the authentication result
     */
    @Operation(summary = "Authenticate user", description = "Authenticate User by specified credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<String> authenticateUser(@RequestBody Auth authPayload) {
        String username = authPayload.getUsername();
        String password = authPayload.getPassword();

        logInfo("AuthController:api/auth/", "Username: " + username + " Password: " + password);

        String authResult = authService.authenticateUser(username, password);

        logInfo("AuthController:api/auth/", "Result: " + authResult);

        if (authResult == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("error");
        }

        return ResponseEntity.status(HttpStatus.OK).body(authResult);
    }

    /**
     * Deletes a user's authentication session.
     *
     * @param id the unique identifier of the user's session to be terminated
     * @return ResponseEntity indicating the result of the termination operation
     */
    @Operation(summary = "Remove user authentication", description = "Remove user authentication by terminating associated session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully terminated"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @DeleteMapping("/terminate/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        boolean deleteOperation = authService.removeUserAuthentication(id);

        if (!deleteOperation) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}