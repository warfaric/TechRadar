package ru.warfaric.techradar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.warfaric.techradar.entity.TechnologyEntity;
import ru.warfaric.techradar.entity.dto.TechnologyDto;
import ru.warfaric.techradar.mapper.Mapper;
import ru.warfaric.techradar.service.TechnologyService;

@RestController
public class TechnologyController {

    private TechnologyService technologyService;

    private Mapper<TechnologyEntity, TechnologyDto> technologyMapper;

    public TechnologyController(TechnologyService technologyService, Mapper<TechnologyEntity, TechnologyDto> technologyMapper) {
        this.technologyService = technologyService;
        this.technologyMapper = technologyMapper;
    }

    @PostMapping(path = "/api/technology")
    public TechnologyDto addTechnology(@RequestBody TechnologyDto technology) {
        TechnologyEntity technologyEntity = technologyMapper.mapFrom(technology);
        TechnologyEntity savedTechnologyEntity = technologyService.createTechnology(technologyEntity);
        return technologyMapper.mapTo(savedTechnologyEntity);
    }

}
