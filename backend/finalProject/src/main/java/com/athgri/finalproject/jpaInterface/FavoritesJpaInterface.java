package com.athgri.finalproject.jpaInterface;

import com.athgri.finalproject.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The FavoritesJpaInterface provides CRUD operations for the Favorites entity
 * and additional custom queries.
 */
public interface FavoritesJpaInterface extends JpaRepository<Favorites, Long> {

    /**
     * Retrieves favorites by user's unique identifier.
     *
     * @param id the unique user identifier
     * @return the favorites of the specified user ID, or null if not found
     */
    Favorites findFavoritesById(Long id);
}