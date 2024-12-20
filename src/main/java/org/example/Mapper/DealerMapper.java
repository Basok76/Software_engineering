package org.example.Mapper;


import org.example.dto.DealerCenterDTO;
import org.example.entity.DealerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DealerMapper {
    DealerMapper INSTANCE = Mappers.getMapper(DealerMapper.class);

    DealerCenterDTO dealerEntityToDealerDTO(DealerEntity dealerEntity);

    DealerEntity dealerDTOToDealerEntity(DealerCenterDTO dealerDTO);
}