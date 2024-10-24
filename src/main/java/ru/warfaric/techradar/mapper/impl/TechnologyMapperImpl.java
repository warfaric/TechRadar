package ru.warfaric.techradar.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.warfaric.techradar.entity.TechnologyEntity;
import ru.warfaric.techradar.entity.dto.TechnologyDto;
import ru.warfaric.techradar.mapper.Mapper;

@Component
public class TechnologyMapperImpl implements Mapper<TechnologyEntity, TechnologyDto> {

    private ModelMapper modelMapper;

    public TechnologyMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TechnologyDto mapTo(TechnologyEntity technologyEntity) {
        return modelMapper.map(technologyEntity, TechnologyDto.class);
    }

    @Override
    public TechnologyEntity mapFrom(TechnologyDto technologyDto) {
        return modelMapper.map(technologyDto, TechnologyEntity.class);
    }
}
