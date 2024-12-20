package org.example.Mapper;

import org.example.dto.CarDTO;
import org.example.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO carEntityToCarDTO(CarEntity carEntity);

    CarEntity carDTOToCarEntity(CarDTO carDTO);
}