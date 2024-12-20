package org.example.Mapper;

import org.example.dto.CarModelDTO;
import org.example.entity.CarModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarModelMapper {
    CarModelMapper INSTANCE = Mappers.getMapper(CarModelMapper.class);

    CarModelDTO carModelEntityToCarModelDTO(CarModelEntity carModelEntity);

    CarModelEntity carModelDTOToCarModelEntity(CarModelDTO carModelDTO);
}