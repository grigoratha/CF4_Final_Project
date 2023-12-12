package com.athgri.finalproject.dtoController;

import java.util.List;
import com.athgri.finalproject.model.Car;
import com.athgri.finalproject.daoService.CarService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The CarController class handles car-related API endpoints.
 */
@Tag(name = "Car API")
@RestController
@RequestMapping("api/car")
public class CarController {
    private final CarService carService;

    /**
     * Constructor for CarController.
     *
     * @param carService the service responsible for car-related operations
     */
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Retrieves a list of all cars.
     *
     * @return ResponseEntity containing the list of cars or a NOT_FOUND status if the list is empty
     */
    @Operation(summary = "Get All Cars", description = "Get the details of all registered cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Error no car details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.findAllCars();

        if (cars.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * Retrieves a car by its unique identifier.
     *
     * @param id the unique identifier of the car to be retrieved
     * @return ResponseEntity containing the car or a NOT_FOUND status if the car is not found
     */
    @Operation(summary = "Get Car By ID", description = "Get the car details of the specified car's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Error no car details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Car> getUserById(@PathVariable("id") Long id) {
        Car car = carService.findCarById(id);

        if (car == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /**
     * Retrieves a list of cars by their unique identifiers.
     *
     * @param ids The list of unique identifiers of the cars.
     * @return A list of cars with the specified IDs.
     */
    @Operation(summary = "Get Car list By IDs", description = "Get a list of car details of the specified cars IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Error no car details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @PostMapping("/find/list")
    public ResponseEntity<List<Car>> findCarsByIds(@RequestBody List<Long> ids) {
        List<Car> cars = carService.findCarByIds(ids);

        if (cars.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * Retrieves a list of cars by their name.
     *
     * @param carName the name of the cars to be retrieved
     * @return ResponseEntity containing the list of cars or a NOT_FOUND status if the list is empty
     */
    @Operation(summary = "Get User By Name", description = "Get the car details of the specified car's name (Brand/Model)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Error no car details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<Car>> getUserById(@PathVariable("name") String carName) {
        List<Car> cars = carService.findCarByName(carName);

        if (cars.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * Adds a new car.
     *
     * @param car the car to be added
     * @return ResponseEntity containing the added car or a NOT_ACCEPTABLE status if the operation fails
     */
    @Operation(summary = "Add Car", description = "Add car details of a new car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Error the car was not created"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @PostMapping(value = "/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {

        if (car.getMileage() == null) {
            car.setMileage(0L);
        }

        if (car.getEngineHP() == null) {
            car.setEngineHP(0L);
        }

        Car newCar = carService.addCar(car);

        if (newCar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    /**
     * Updates an existing car.
     *
     * @param id  the unique identifier of the car to be updated
     * @param car the updated car information
     * @return ResponseEntity containing the updated car or a NOT_ACCEPTABLE status if the operation fails
     */
    @Operation(summary = "Update Car By ID", description = "Update the car details of the specified car's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Error no car details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {

        if (car.getMileage() == null) {
            car.setMileage(0L);
        }

        if (car.getEngineHP() == null) {
            car.setEngineHP(0L);
        }

        Car currentCar = carService.updateCar(id, car);

        if (currentCar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(currentCar, HttpStatus.OK);
    }

    /**
     * Deletes a car by its unique identifier.
     *
     * @param id the unique identifier of the car to be deleted
     * @return ResponseEntity indicating the result of the deletion operation
     */
    @Operation(summary = "Delete Car", description = "Delete the car details of the specified car's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Error no user details found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        boolean deleteOperation = carService.deleteCar(id);

        if (!deleteOperation) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}