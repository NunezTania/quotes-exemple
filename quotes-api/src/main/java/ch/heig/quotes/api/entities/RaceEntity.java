package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity(name = "Race")
@Table(name = "races")
public class RaceEntity {
    @TableGenerator(name = "genRaces",
            table = "idRaces",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genRaces")
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;

    @ManyToMany(targetEntity = LocationEntity.class)
    @JoinTable(name="locations_races",
            joinColumns = @JoinColumn(name="race_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="location_id", referencedColumnName = "id"))
    private Set<LocationEntity> locations;

    @OneToMany(mappedBy = "race")
    @Getter
    @Setter
    private Set<AnimalEntity> animals;

    public RaceEntity() {}

    public RaceEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
