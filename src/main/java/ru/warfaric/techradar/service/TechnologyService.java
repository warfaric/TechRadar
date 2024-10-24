package ru.warfaric.techradar.service;

import ru.warfaric.techradar.entity.TechnologyEntity;

import java.util.List;
import java.util.Optional;

public interface TechnologyService {

    TechnologyEntity save(TechnologyEntity technologyEntity);

    List<TechnologyEntity> findall();

    Optional<TechnologyEntity> findOne(Long id);

    boolean isExists(Long id);

    TechnologyEntity partialUpdate(Long id, TechnologyEntity technologyEntity);

    void delete(Long id);
}
