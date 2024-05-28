package com.rgarcia.w2m.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "spaceships")
public class SpaceShipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "spaceships_seq")
    @SequenceGenerator(sequenceName="spaceships_seq", name="spaceships_seq", allocationSize=1)
    private Long id;

    @Column(unique=true, nullable = false, length = 100, name = "name")
    String name;

    @Column(nullable = false, name = "related_to", length = 255)
    String relatedTo;

}
