package ru.warfaric.techradar.service.impl;

import org.springframework.stereotype.Service;
import ru.warfaric.techradar.entity.TechnologyEntity;
import ru.warfaric.techradar.repositories.TechnologyRepository;
import ru.warfaric.techradar.service.TechnologyService;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private TechnologyRepository technologyRepository;

    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public TechnologyEntity createTechnology(TechnologyEntity technologyEntity) {
        return technologyRepository.save(technologyEntity);
    }
}
