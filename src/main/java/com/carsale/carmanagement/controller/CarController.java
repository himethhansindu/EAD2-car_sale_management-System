package com.carsale.carmanagement.controller;

import com.carsale.carmanagement.model.Car;
import com.carsale.carmanagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Car updatedCar = carService.updateCar(id, carDetails);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<Car> buyCar(@PathVariable Long id, @RequestParam Long buyerId) {
        Car boughtCar = carService.buyCar(id, buyerId);
        return ResponseEntity.ok(boughtCar);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Car> getCarsBySellerId(@PathVariable Long sellerId) {
        return carService.getCarsBySellerId(sellerId);
    }
}

