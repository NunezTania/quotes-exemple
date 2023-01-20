package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {
    AnimalEntity findById(int id);
    List<AnimalEntity> findBySpeciesLike(String pattern);
}

