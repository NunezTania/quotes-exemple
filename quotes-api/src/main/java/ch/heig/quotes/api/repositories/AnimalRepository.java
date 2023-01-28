package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.AnimalEntity;
import ch.heig.quotes.api.entities.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {
    AnimalEntity findById(int id);
    List<AnimalEntity> findBySpeciesLike(String pattern);
    @Modifying
    @Transactional
    @Query(value = "insert into animals (id, name, noise, species, race_id) " +
                    "values (:id, :name, :noise, :species, :race_id)",
            nativeQuery = true)
    void insertAnimal(@Param("id") Integer id,
                      @Param("name") String name,
                      @Param("noise") String noise,
                      @Param("species") String species,
                      @Param("race_id") Integer race_id);

    @Modifying
    @Transactional
    @Query(value = "update Animal as a set" +
                    " a.name = :name," +
                    " a.noise = :noise," +
                    " a.species = :species," +
                    "a.race = :race" +
                    " where a.id = :id")
    void updateAnimalById(@Param("id") Integer id,
                          @Param("name") String name,
                          @Param("noise") String noise,
                          @Param("species") String species,
                          @Param("race") RaceEntity race);

    @Modifying
    @Transactional
    @Query(value = "update Animal as a set" +
            " a.name = :name," +
            " a.noise = :noise," +
            " a.species = :species," +
            "a.race = :race" +
            " where a.id = :id")
    void replaceAnimalById(@Param("id") Integer id,
                          @Param("name") String name,
                          @Param("noise") String noise,
                          @Param("species") String species,
                          @Param("race") RaceEntity race);

}

