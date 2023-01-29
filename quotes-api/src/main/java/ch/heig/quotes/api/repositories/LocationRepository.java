package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
    LocationEntity findById(int id);
    List<LocationEntity> findByNameLike(String pattern);

    @Modifying
    @Transactional
    @Query(value = "insert into locations (id, name) " +
            "values (:id, :name)",
            nativeQuery = true)
    void insertLocation(@Param("id") Integer id,
                      @Param("name") String name);
}