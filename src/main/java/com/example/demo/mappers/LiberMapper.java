package com.example.demo.mappers;

import com.example.demo.entities.LiberEntity;
import com.example.demo.models.LiberDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LiberMapper {

    public LiberEntity toEntity(LiberDTO liberDTO) {
        LiberEntity liberEntity = new LiberEntity();

        liberEntity.setIsbn(liberDTO.getIsbn());
        liberEntity.setTitulli(liberDTO.getTitull());

        return liberEntity;
    }

    public LiberDTO toDTO(LiberEntity liberEntity) {
        LiberDTO liberDTO = new LiberDTO();

        liberDTO.setIsbn(liberEntity.getIsbn());
        liberDTO.setTitull(liberEntity.getTitulli());

        return liberDTO;
    }

    public List<LiberEntity> toEntityList(List<LiberDTO> liberDtoList) {
        List<LiberEntity> liberEntityList = new ArrayList<>();

        for (LiberDTO liberDTO : liberDtoList) {
            liberEntityList.add(toEntity(liberDTO));
        }

        return liberEntityList;
    }

    public List<LiberDTO> toDTOList(List<LiberEntity> liberEntityList) {
        List<LiberDTO> liberDTOList = new ArrayList<>();

        for (LiberEntity liberEntity : liberEntityList) {
            liberDTOList.add(toDTO(liberEntity));
        }

        return liberDTOList;
    }

}
