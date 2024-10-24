package ru.warfaric.techradar;

import ru.warfaric.techradar.entity.TechnologyEntity;
import ru.warfaric.techradar.entity.dto.TechnologyDto;

public final class TestDataUtil {

    private TestDataUtil() {

    }

    public static TechnologyEntity createTestTechnology(){
        return TechnologyEntity.builder()
                .id(1L)
                .technology("Java")
                .ring("Adopt")
                .section("Languages")
                .category("Backend")
                .build();
    }
    public static TechnologyDto createTestDtoTechnology(){
        return TechnologyDto.builder()
                .id(1L)
                .technology("Java")
                .ring("Adopt")
                .section("Languages")
                .category("Backend")
                .build();
    }
}
