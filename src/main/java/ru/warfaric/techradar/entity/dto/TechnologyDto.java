package ru.warfaric.techradar.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechnologyDto {

    private Long id;

    private String technology;

    private String ring;

    private String section;

    private String category;

}
