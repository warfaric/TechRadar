package ru.warfaric.techradar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.warfaric.techradar.entity.TechnologyEntity;
import ru.warfaric.techradar.entity.dto.TechnologyDto;
import ru.warfaric.techradar.mapper.Mapper;
import ru.warfaric.techradar.service.TechnologyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TechnologyController {

    private final TechnologyService technologyService;

    private final Mapper<TechnologyEntity, TechnologyDto> technologyMapper;

    public TechnologyController(TechnologyService technologyService, Mapper<TechnologyEntity, TechnologyDto> technologyMapper) {
        this.technologyService = technologyService;
        this.technologyMapper = technologyMapper;
    }

    @PostMapping(path = "/api/technology")
    public ResponseEntity<String> addTechnology(@RequestBody TechnologyDto technology) {
        try {
            TechnologyEntity technologyEntity = technologyMapper.mapFrom(technology);
            technologyService.save(technologyEntity);
            return new ResponseEntity<>("id: " + technologyEntity.getId() + "\nТехнология успешно добавлена", HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>("Некорректные параметры запроса. Проверьте правильность заполнения всех \n" +
                    "обязательных полей: name, category, section, ring.", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/api/technology")
    public List<TechnologyDto> listTechnologies() {
        List<TechnologyEntity> technologies = technologyService.findall();
        return technologies.stream().map(technologyMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/api/technology/{id}")
    public ResponseEntity<TechnologyDto> getTechnology(@PathVariable("id") Long id) {
        Optional<TechnologyEntity> foundTechnology = technologyService.findOne(id);
        return foundTechnology.map(technologyEntity -> {
            TechnologyDto technologyDto = technologyMapper.mapTo(technologyEntity);
            return new ResponseEntity<>(technologyDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/api/technology/{id}")
    public ResponseEntity<TechnologyDto> fullUpdateTechnology(
            @PathVariable("id") Long id,
            @RequestBody TechnologyDto technologyDto) {
        if(!technologyService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        technologyDto.setId(id);
        TechnologyEntity technologyEntity = technologyMapper.mapFrom(technologyDto);
        TechnologyEntity savedTechnologyEntity = technologyService.save(technologyEntity);
        return new ResponseEntity<>(technologyMapper.mapTo(savedTechnologyEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/api/technology/{id}")
    public ResponseEntity<String> partialUpdateTechnology(
                @PathVariable("id") Long id,
                @RequestBody TechnologyDto technologyDto
    ) {
        if(!technologyService.isExists(id)) {
            return new ResponseEntity<>("The technology with the specified ID does not exist.",HttpStatus.NOT_FOUND);
        }

        TechnologyEntity technologyEntity = technologyMapper.mapFrom(technologyDto);
        technologyService.partialUpdate(id, technologyEntity);
        return new ResponseEntity<>("Технология успешно обновлена", HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/technology/{id}")
    public ResponseEntity<String> deleteTechnology(@PathVariable("id") Long id) {
        try {
            technologyService.delete(id);
            return new ResponseEntity<>("Технология успешно удалена", HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>("The technology with the specified ID does not exist.", HttpStatus.NOT_FOUND);
        }
    }

}
