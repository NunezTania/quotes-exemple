package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
    LocationEntity findById(int id);
    List<LocationEntity> findByNameLike(String pattern);
}