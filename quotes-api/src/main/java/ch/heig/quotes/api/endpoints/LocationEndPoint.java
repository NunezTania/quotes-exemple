package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.AnimalEntity;
import ch.heig.quotes.api.entities.LocationEntity;
import ch.heig.quotes.api.entities.RaceEntity;
import ch.heig.quotes.api.exceptions.LocationAlreadyExists;
import ch.heig.quotes.api.exceptions.LocationNotFoundException;
import ch.heig.quotes.api.repositories.LocationRepository;
import ch.heig.quotes.api.services.LocationService;
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
    private LocationService locationService;

    @Override
    public ResponseEntity<List<Location>> getLocations() {
        return locationService.getLocations();
    }

    @Override
    public ResponseEntity<Void> addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @Override
    public ResponseEntity<Location> getLocation(Integer id) {
        return locationService.getLocation(id);
    }

    @Override
    public ResponseEntity<List<Race>> getLocationRaces(Integer id) {
        return locationService.getLocationRaces(id);
    }

    @Override
    public ResponseEntity<List<Animal>> getLocationAnimals(Integer id) {
        return locationService.getLocationAnimals(id);
    }

    @Override
    public ResponseEntity<Void> addLocationRace(Integer id, @RequestBody Integer race_id) {
        return locationService.addLocationRace(id, race_id);
    }
}
