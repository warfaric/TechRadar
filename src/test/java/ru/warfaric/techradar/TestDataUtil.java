package ru.warfaric.techradar;

import ru.warfaric.techradar.entity.TechnologyEntity;

public class TestDataUtil {

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
}
