package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.exceptions.RaceNotFoundException;
import ch.heig.quotes.api.services.RaceService;
import org.openapitools.api.RacesApi;
import org.openapitools.model.Race;
import ch.heig.quotes.api.entities.RaceEntity;
import ch.heig.quotes.api.repositories.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RacesEndPoint implements RacesApi {

    @Autowired
    private RaceService raceService;

    @Override
    public ResponseEntity<List<Race>> getRaces() {
       return raceService.getRaces();
    }

    @Override
    public ResponseEntity<Race> getRace(Integer id) {
        return raceService.getRace(id);
    }
}
