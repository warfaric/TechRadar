package ru.warfaric.techradar.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.warfaric.techradar.entity.TechnologyEntity;

public interface TechnologyRepository extends CrudRepository<TechnologyEntity, Long> {
}
