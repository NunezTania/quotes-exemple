package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.LocationEntity;
import ch.heig.quotes.api.exceptions.LocationNotFoundException;
import ch.heig.quotes.api.repositories.LocationRepository;
import org.openapitools.api.LocationsApi;
import org.openapitools.model.Location;
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
        return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addLocation(@RequestBody Location location) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(location.getId());
        locationEntity.setName(location.getName());
        LocationEntity locationAdded = locationRepository.save(locationEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(locationAdded.getId())
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
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } else {
            throw new LocationNotFoundException(id);
        }
    }
}
