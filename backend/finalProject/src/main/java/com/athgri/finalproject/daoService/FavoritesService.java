package com.athgri.finalproject.daoService;

import java.util.List;
import java.util.Collections;
import com.athgri.finalproject.model.User;
import com.athgri.finalproject.model.Favorites;
import com.athgri.finalproject.jpaInterface.UserJpaInterface;
import com.athgri.finalproject.jpaInterface.FavoritesJpaInterface;

import com.athgri.finalproject.utilities.GsonWrapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athgri.finalproject.utilities.GsonWrapper.*;

/**
 * The FavoritesService class provides services related to favorites.
 */
@Service
public class FavoritesService {
    private final UserJpaInterface userJpaInterface;
    private final FavoritesJpaInterface favoritesJpaInterface;

    /**
     * Constructor for FavoritesService.
     *
     * @param userJpaInterface  the JPA interface for user entities
     * @param favoritesJpaInterface the JPA interface for favorites entities
     */
    @Autowired
    public FavoritesService(UserJpaInterface userJpaInterface, FavoritesJpaInterface favoritesJpaInterface) {
        this.userJpaInterface = userJpaInterface;
        this.favoritesJpaInterface = favoritesJpaInterface;
    }

    /**
     * Retrieves all Favorites entities.
     *
     * @return List of Favorites entities.
     */
    public List<Favorites> findAllFavorites() {
        return favoritesJpaInterface.findAll();
    }

    /**
     * Retrieves a Favorites entity by its ID.
     *
     * @param id The ID of the Favorites entity to retrieve.
     * @return A Favorites entity, or null if not found.
     */
    public Favorites findFavoritesById(Long id) {

        if(!favoritesJpaInterface.existsById(id)) {
            return null;
        }

        return favoritesJpaInterface.findFavoritesById(id);
    }


    /**
     * Adds a single item ID to the Favorites entity with the specified ID.
     *
     * @param id     The ID of the Favorites entity to update.
     * @param itemId The ID of the item to add to the Favorites entity.
     */
    public boolean addItemToId(Long id, Long itemId) {
        //Checks if a user with the specified id exists
        User user = userJpaInterface.findUserById(id);

        if(user == null) {
            return false;
        }

        Favorites currentFavorites = favoritesJpaInterface.findFavoritesById(id);

        //Create favorites information
        if(currentFavorites == null) {

            List<Long> newItemList = Collections.singletonList(itemId);

            String jsonItemList = GsonWrapper.listToJson(newItemList);

            currentFavorites = new Favorites(id, jsonItemList);

            favoritesJpaInterface.save(currentFavorites);
            return true;
        }

        //Update favorites information
        String currentItemList = currentFavorites.getItemList();

        List<Long> itemList = GsonWrapper.jsonToList( currentFavorites.getItemList(), Long.class);

        if( !itemList.contains(itemId) ) {

            itemList.add(itemId);

            String jsonItemList = GsonWrapper.listToJson(itemList);

            currentFavorites.setItemList(jsonItemList);

            favoritesJpaInterface.save(currentFavorites);
            return true;
        }

        return false;
    }

    /**
     * Deletes a specific item from the Favorites entity with the specified ID.
     *
     * @param id     The ID of the Favorites entity to update.
     * @param itemId The ID of the item to delete from the Favorites entity.
     */
    public boolean deleteItemFromId(Long id, Long itemId) {
        //Checks if a user with the specified id exists
        User user = userJpaInterface.findUserById(id);

        if(user == null) {
            return false;
        }

        Favorites currentFavorites = favoritesJpaInterface.findFavoritesById(id);

        if(currentFavorites != null) {

            List<Long> itemList = GsonWrapper.jsonToList(currentFavorites.getItemList(), Long.class);

            itemList.remove(itemId);

            String jsonItemList = GsonWrapper.listToJson(itemList);

            currentFavorites.setItemList(jsonItemList);

            favoritesJpaInterface.save(currentFavorites);
            return true;
        }

        return false;
    }
}
