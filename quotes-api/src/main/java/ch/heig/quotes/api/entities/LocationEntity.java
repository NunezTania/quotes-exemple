package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity(name = "Location")
@Table(name = "locations")
public class LocationEntity {
    @TableGenerator(name = "genLocations",
            table = "idLocations",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genLocations")
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "locations")
    @Getter
    @Setter
    private Set<RaceEntity> races;

    public LocationEntity() {}

    public LocationEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
