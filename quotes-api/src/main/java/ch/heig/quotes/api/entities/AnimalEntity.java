package ch.heig.quotes.api.entities;

import jakarta.persistence.*;

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

    private int id;

    private String name;

    private String noise;

    private String species;

    public AnimalEntity() {}

    public AnimalEntity(int id, String name, String noise, String species) {
        this.id = id;
        this.name = name;
        this.noise = noise;
        this.species = species;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

}
