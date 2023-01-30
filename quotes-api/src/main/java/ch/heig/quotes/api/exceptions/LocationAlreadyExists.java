package ch.heig.quotes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocationAlreadyExists extends RuntimeException {
    public LocationAlreadyExists(Integer id) {
        super("Location " + id + " already exists");
    }
}
