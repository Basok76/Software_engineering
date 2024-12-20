package org.example.service;

import org.example.dto.CarModelDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileSystemCarModelService implements CarModelService {

    @Override
    public List<CarModelDTO> load() {
        return List.of(
                new CarModelDTO(1, "Toyota", "Corolla", "JP", "Japan"),
                new CarModelDTO(2, "Honda", "Accord", "JP", "Japan"),
                new CarModelDTO(3, "Toyota", "Camry", "JP", "Japan"),
                new CarModelDTO(4, "Honda", "Civic", "JP", "Japan"),
                new CarModelDTO(5, "Toyota", "Corolla", "JP", "Japan")
        );
    }

    @Override
    public List<CarModelDTO> getAllCarDTOs(String brand) {
        return load().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<CarModelDTO> findCarById(CarModelDTO car) {
        return load().stream()
                .filter(c -> c.getId().equals(car.getId()))
                .findFirst();
    }

    @Override
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        return load().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.groupingBy(
                        CarModelDTO::getModelName,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }
}