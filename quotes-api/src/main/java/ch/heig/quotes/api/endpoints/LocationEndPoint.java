package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.AnimalEntity;
import ch.heig.quotes.api.entities.LocationEntity;
import ch.heig.quotes.api.entities.RaceEntity;
import ch.heig.quotes.api.exceptions.LocationAlreadyExists;
import ch.heig.quotes.api.exceptions.LocationNotFoundException;
import ch.heig.quotes.api.repositories.LocationRepository;
import org.openapitools.api.LocationsApi;
import org.openapitools.model.Animal;
import org.openapitools.model.Location;
import org.openapitools.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class LocationEndPoint implements LocationsApi {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public ResponseEntity<List<Location>> getLocations() {
        List<LocationEntity> locationEntities = locationRepository.findAll();
        List<Location> locations = new ArrayList<>();
        for (LocationEntity locationEntity : locationEntities) {
            Location location = new Location();
            location.setId(locationEntity.getId());
            location.setName(locationEntity.getName());
            locations.add(location);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addLocation(@RequestBody Location location) {
        if (locationRepository.existsById(location.getId())) {
            throw new LocationAlreadyExists(location.getId());
        }
        locationRepository.insertLocation(
                location.getId(),
                location.getName());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(location.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Location> getLocation(Integer id) {
        Optional<LocationEntity> opt = locationRepository.findById(id);
        if (opt.isPresent()) {
            LocationEntity locationEntity = opt.get();
            Location location = new Location();
            location.setId(locationEntity.getId());
            location.setName(locationEntity.getName());
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            throw new LocationNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<List<Race>> getLocationRaces(Integer id) {
        Optional<LocationEntity> opt = locationRepository.findById(id);
        if (opt.isPresent()) {
            LocationEntity locationEntity = opt.get();
            Set<RaceEntity> raceEntities = locationEntity.getRaces();
            List<Race> races = new ArrayList<>();
            for (RaceEntity re : raceEntities) {
                Race race = new Race();
                race.setName(re.getName());
                race.setDescription(re.getDescription());
                races.add(race);
            }
            return new ResponseEntity<>(races, HttpStatus.OK);
        } else {
            throw new LocationNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<List<Animal>> getLocationAnimals(Integer id) {
        Optional<LocationEntity> opt = locationRepository.findById(id);
        if (opt.isPresent()) {
            LocationEntity locationEntity = opt.get();
            Set<RaceEntity> raceEntities = locationEntity.getRaces();
            List<Animal> animals = new ArrayList<>();
            for (RaceEntity re : raceEntities) {
                Set<AnimalEntity> animalEntities = re.getAnimals();
                for (AnimalEntity ae : animalEntities) {
                    Animal animal = new Animal();
                    animal.setId(ae.getId());
                    animal.setName(ae.getName());
                    animal.setNoise(ae.getNoise());
                    animal.setSpecies(ae.getSpecies());
                    animal.setRace(ae.getRace().getId());
                    animals.add(animal);
                }
            }
            return new ResponseEntity<>(animals, HttpStatus.OK);
        } else {
            throw new LocationNotFoundException(id);
        }
    }
}
