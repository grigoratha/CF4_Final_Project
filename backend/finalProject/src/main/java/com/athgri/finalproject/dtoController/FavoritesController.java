package com.athgri.finalproject.dtoController;

import java.util.List;

import com.athgri.finalproject.model.Favorites;
import com.athgri.finalproject.daoService.FavoritesService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * The FavoritesController class handles favorites-related API endpoints.
 */
@Tag(name = "Favorites API")
@RestController
@RequestMapping("api/favorites")
public class FavoritesController {
    private final FavoritesService favoritesService;

    /**
     * Constructor for FavoritesController.
     *
     * @param favoritesService the service responsible for favorites-related operations
     */
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    /**
     * Retrieves a list of all user sessions.
     *
     * @return ResponseEntity containing the list of favorites or a NOT_FOUND status if the list is empty
     */
    @Operation(summary = "Get Favorites for all users", description = "Retrieve all favorites details for all active users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Error favorites details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<Favorites>> getAllFavorites() {
        List<Favorites> favorites = favoritesService.findAllFavorites();

        if (favorites.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @Operation(summary = "Get Favorites of User by ID", description = "Retrieve the favorites details of the specified user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Favorites> getFavoritesById(@PathVariable("id") Long id) {
        Favorites favorites = favoritesService.findFavoritesById(id);

        if (favorites == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    /**
     * Adds a new favorites item ID to the specified user ID.
     *
     * @param id the unique identifier of the user associated with the favorites
     * @return ResponseEntity containing true if the item was added or false if the operation fails
     */
    @Operation(summary = "Add item's ID to favorites", description = "Add item's ID to favorites details of the specified user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/add/{id}/{itemId}")
    public ResponseEntity<?> addFavoriteById(@PathVariable("id") Long id, @PathVariable Long itemId) {

        if ( !favoritesService.addItemToId(id, itemId) ) {
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    /**
     * Deletes a user session by its unique identifier.
     *
     * @param id the unique identifier of the user session to be deleted
     * @return ResponseEntity indicating the result of the deletion operation
     */
    @Operation(summary = "Removes item's ID from favorites", description = "Remove item's ID to favorites details of the specified user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed"),
            @ApiResponse(responseCode = "404", description = "Error the specified user does not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @DeleteMapping("/delete/{id}/{itemId}")
    public ResponseEntity<?> deleteUserSession(@PathVariable("id") Long id, @PathVariable Long itemId) {
        boolean deleteOperation = favoritesService.deleteItemFromId(id, itemId);

        if (!deleteOperation) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}