package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Animal")
@Table(name = "animals")
public class AnimalEntity {
    @TableGenerator(name = "genAnimals",
            table = "idAnimals",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genAnimals")
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String noise;
    @Getter
    @Setter
    private String species;
    @ManyToOne(targetEntity = RaceEntity.class)
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    @Getter
    @Setter
    private RaceEntity race;

    public AnimalEntity() {}

    public AnimalEntity(int id, String name, String noise, String species) {
        this.id = id;
        this.name = name;
        this.noise = noise;
        this.species = species;
    }

}
