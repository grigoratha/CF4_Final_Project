package com.athgri.finalproject.daoService;

import java.util.List;
import com.athgri.finalproject.model.Car;
import com.athgri.finalproject.jpaInterface.CarJpaInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.athgri.finalproject.utilities.Log.logWarning;

/**
 * The CarService class provides services related to car management.
 */
@Service
public class CarService {
    private final CarJpaInterface carJpaInterface;

    /**
     * Constructor for CarService.
     *
     * @param carJpaInterface the JPA interface for Car entities
     */
    @Autowired
    public CarService(CarJpaInterface carJpaInterface) {

        this.carJpaInterface = carJpaInterface;
    }

    /**
     * Retrieves a list of all cars.
     *
     * @return a list of all cars
     */
    public List<Car> findAllCars() {
        return carJpaInterface.findAll();
    }

    /**
     * Retrieves a car by its unique identifier.
     *
     * @param id the unique identifier of the car
     * @return the car with the specified ID, or null if not found
     */
    public Car findCarById(Long id) {

        if (!carJpaInterface.existsById(id)) {

            logWarning("CarService::findCarById", "The vehicle with id: "  + id + " does not exist in the database");
            return null;
        }

        return carJpaInterface.findCarById(id);
    }

    public List<Car> findCarByIds(List<Long> ids) {

        return carJpaInterface.findCarsByIds(ids);
    }

    /**
     * Retrieves a list of cars based on their brand or model.
     *
     * @param carName the brand or model of the cars to retrieve
     * @return a list of cars with the specified brand or model
     */
    public List<Car> findCarByName(String carName) {

        return carJpaInterface.findCarByName(carName);
    }

    /**
     * Adds a new car.
     *
     * @param car the car to add
     * @return the added car, or null if the specified car information already exists in the database
     */
    public Car addCar(Car car) {
        List<Car> activeCars = findAllCars();

        String thisBrand = car.getBrand();
        String thisModel = car.getModel();
        Long thisEngineCC = car.getEngineCC();
        Long thisYear = car.getYear();

        String currentBrand = "";
        String currentModel = "";
        Long currentEngineCC = 0L;
        Long currentYear = 0L;

        if (!activeCars.isEmpty()) {
            for (Car currentCar : activeCars) {
                currentBrand = currentCar.getBrand();
                currentModel = currentCar.getModel();
                currentEngineCC = currentCar.getEngineCC();
                currentYear = currentCar.getYear();

                if (thisBrand.equals(currentBrand) && thisModel.equals(currentModel) &&
                        thisEngineCC.equals(currentEngineCC) && thisYear.equals(currentYear)) {

                    logWarning("CarService::addCar", "The vehicle information supplied already exists in the database");
                    return null;
                }
            }
        }

        return carJpaInterface.save(car);
    }

    /**
     * Updates an existing car.
     *
     * @param id  the unique identifier of the car to update
     * @param car the car data to update
     * @return the updated car, or null if the specified car ID does not exist in the database
     */
    public Car updateCar(Long id, Car car) {

        if (!carJpaInterface.existsById(id)) {
            logWarning("CarService::updateCar", "The car to be updated does not exist in the database");
            return null;
        }

        car.setId(id);
        return carJpaInterface.save(car);
    }

    /**
     * Deletes a car by its unique identifier.
     *
     * @param id the unique identifier of the car to delete
     * @return true if the car is successfully deleted, false otherwise
     */
    public boolean deleteCar(Long id) {

        if (!carJpaInterface.existsById(id)) {
            logWarning("CarService::deleteCar", "The car to be deleted does not exist in the database");
            return false;
        }

        carJpaInterface.deleteById(id);
        return true;
    }
}