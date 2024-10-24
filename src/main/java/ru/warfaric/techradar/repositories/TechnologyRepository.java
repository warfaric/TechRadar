package ru.warfaric.techradar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.warfaric.techradar.entity.TechnologyEntity;

@Repository
public interface TechnologyRepository extends CrudRepository<TechnologyEntity, Long> {
}
