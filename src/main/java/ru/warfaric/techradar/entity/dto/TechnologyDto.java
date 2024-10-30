package ru.warfaric.techradar.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechnologyDto {

    private Long id;

    private String name;

    private String description;

    private String ring;

    private String section;

    private String category;

}
