package ch.heig.quotes.api.services;

import ch.heig.quotes.api.entities.RaceEntity;
import ch.heig.quotes.api.exceptions.RaceNotFoundException;
import ch.heig.quotes.api.repositories.RaceRepository;
import org.openapitools.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    public ResponseEntity<List<Race>> getRaces() {
        List<RaceEntity> raceEntities= raceRepository.findAll();
        List<Race> races = new ArrayList<>();
        for (RaceEntity raceEntity : raceEntities) {
            Race race = new Race();
            race.setId(raceEntity.getId());
            race.setName(raceEntity.getName());
            race.setDescription(raceEntity.getDescription());
            races.add(race);
        }
        return new ResponseEntity<>(races, HttpStatus.OK);
    }

    public ResponseEntity<Race> getRace(Integer id) {
        Optional<RaceEntity> opt = raceRepository.findById(id);
        if (opt.isPresent()) {
            RaceEntity raceEntity = opt.get();
            Race race = new Race();
            race.setId(raceEntity.getId());
            race.setName(raceEntity.getName());
            race.setDescription(raceEntity.getDescription());
            return new ResponseEntity<Race>(race, HttpStatus.OK);
        } else {
            throw new RaceNotFoundException(id);
        }
    }
}
