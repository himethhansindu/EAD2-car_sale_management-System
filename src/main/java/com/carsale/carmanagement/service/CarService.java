package com.carsale.carmanagement.service;

import com.carsale.carmanagement.model.Car;
import com.carsale.carmanagement.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setPrice(carDetails.getPrice());

        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        carRepository.delete(car);
    }

    public Car buyCar(Long id, Long buyerId) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        if (car.getQuantity() <= 0) {
            throw new RuntimeException("Car is out of stock");
        }

        car.setQuantity(car.getQuantity() - 1);
        if (car.getQuantity() == 0) {
            car.setSold(true);
        }
        car.setBuyerId(buyerId);
        return carRepository.save(car);
    }

    public List<Car> getCarsBySellerId(Long sellerId) {
        return carRepository.findBySellerId(sellerId);
    }
}

