package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, Integer> {
    RaceEntity findById(int id);

    List<RaceEntity> findByNameLike(String pattern);
}
