package org.example.service;

import org.example.dto.CarModelDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarModelService {
    List<CarModelDTO> load();
    List<CarModelDTO> getAllCarDTOs(String brand);
    Optional<CarModelDTO> findCarById(CarModelDTO car);
    Map<String, Integer> getCarModelGroupByModel(String brand);

}