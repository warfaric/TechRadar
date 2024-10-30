package ru.warfaric.techradar.service.impl;

import org.springframework.stereotype.Service;
import ru.warfaric.techradar.entity.TechnologyEntity;
import ru.warfaric.techradar.repositories.TechnologyRepository;
import ru.warfaric.techradar.service.TechnologyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private TechnologyRepository technologyRepository;

    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public TechnologyEntity save(TechnologyEntity technologyEntity) {
        if (technologyEntity.getName().isEmpty()) {
            throw new NullPointerException();
        }
        if (technologyEntity.getRing().isEmpty()) {
            throw new NullPointerException();
        }
        if (technologyEntity.getSection().isEmpty()) {
            throw new NullPointerException();
        }
        if (technologyEntity.getCategory().isEmpty()) {
            throw new NullPointerException();
        }
        return technologyRepository.save(technologyEntity);
    }

    @Override
    public List<TechnologyEntity> findall(){
        return StreamSupport.stream(technologyRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TechnologyEntity> findOne(Long id) {
        return technologyRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return technologyRepository.existsById(id);
    }

    @Override
    public TechnologyEntity partialUpdate(Long id, TechnologyEntity technologyEntity) {
        technologyEntity.setId(id);

        return technologyRepository.findById(id).map(existingTechnology -> {
            Optional.ofNullable(technologyEntity.getName()).ifPresent(existingTechnology::setName);
            Optional.ofNullable(technologyEntity.getDescription()).ifPresent(existingTechnology::setDescription);
            Optional.ofNullable(technologyEntity.getRing()).ifPresent(existingTechnology::setRing);
            Optional.ofNullable(technologyEntity.getSection()).ifPresent(existingTechnology::setSection);
            Optional.ofNullable(technologyEntity.getCategory()).ifPresent(existingTechnology::setCategory);
            return technologyRepository.save(existingTechnology);
        }).orElseThrow(() -> new RuntimeException("Technology Not Found"));
    }

    @Override
    public void delete(Long id) {
        technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Technology Not Found"));
        technologyRepository.deleteById(id);
    }
}
