package ru.warfaric.techradar;

import org.springframework.boot.SpringApplication;

public class TestTechRadarApplication {

    public static void main(String[] args) {
        SpringApplication.from(TechRadarApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
