package ru.warfaric.techradar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "technologies")
public class TechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "technology_id_seq")
    private Long id;

    private String technology;

    private String ring;

    private String section;

    private String category;


}
