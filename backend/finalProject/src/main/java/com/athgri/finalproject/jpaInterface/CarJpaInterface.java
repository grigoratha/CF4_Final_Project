package com.athgri.finalproject.jpaInterface;

import java.util.List;
import com.athgri.finalproject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

/**
 * The CarJpaInterface provides CRUD operations for the Car entity
 * and additional custom queries.
 */
public interface CarJpaInterface extends JpaRepository<Car, Long> {

    /**
     * Retrieves a car by its unique identifier.
     *
     * @param id the unique identifier of the car
     * @return the car with the specified ID, or null if not found
     */
    Car findCarById(Long id);

    /**
     * Retrieves a list of cars by their unique identifiers.
     *
     * @param ids the set of unique identifiers of the cars
     * @return a list of cars with the specified IDs
     */
    @Query("SELECT car FROM Car car WHERE car.id IN :ids")
    List<Car> findCarsByIds(@Param("ids") List<Long> ids);

    /**
     * Retrieves a list of cars by searching for a given brand or model name.
     *
     * @param carName the brand or model name to search for
     * @return a list of cars matching the specified brand or model name
     */
    @Query("SELECT car FROM Car car WHERE car.brand LIKE %:carName% OR car.model LIKE %:carName%")
    List<Car> findCarByName(@Param("carName") String carName);
}
