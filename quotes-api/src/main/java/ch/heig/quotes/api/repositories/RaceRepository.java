package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, Integer> {
    RaceEntity findById(int id);

    @Modifying
    @Transactional
    @Query(value = "insert into races (id, name, description) " +
            "values (:id, :name, :description)",
            nativeQuery = true)
    void insertRace(@Param("id") Integer id,
                      @Param("name") String name,
                      @Param("description") String description);

    @Query(value="select count(*) from races", nativeQuery = true)
    long count();
}
