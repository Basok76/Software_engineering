package org.example.service;
import org.example.dto.CarModelDTO;

import java.util.*;
import java.util.stream.Collectors;

public class CarService {

    public List<CarModelDTO> load() {
        return List.of(
                new CarModelDTO(1, "Toyota", "Corolla", "JP", "Japan"),
                new CarModelDTO(2, "Honda", "Accord", "JP", "Japan"),
                new CarModelDTO(3, "Toyota", "Camry", "JP", "Japan"),
                new CarModelDTO(4, "Honda", "Civic", "JP", "Japan"),
                new CarModelDTO(5, "Ford", "Focus", "US", "USA"),
                new CarModelDTO(6, "Toyota", "RAV4", "JP", "Japan")
        );
    }

    public Set<String> getUniqueBrands() {
        return load().stream()
                .map(CarModelDTO::getBrand)
                .collect(Collectors.toSet());
    }

    public List<CarModelDTO> getCarsByBrand(String brand) {
        return load().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> groupCarsByBrand() {
        return load().stream()
                .collect(Collectors.groupingBy(
                        CarModelDTO::getBrand,
                        Collectors.summingInt(car -> 1)
                ));
    }
    public class CarServicePerformance {

        public static long measureExecutionTime(Runnable task) {
            long start = System.nanoTime();
            task.run();
            long end = System.nanoTime();
            return end - start;
        }
    }
}