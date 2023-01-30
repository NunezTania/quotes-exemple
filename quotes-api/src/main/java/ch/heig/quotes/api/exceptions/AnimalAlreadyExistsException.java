package ch.heig.quotes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnimalAlreadyExistsException extends RuntimeException {
    public AnimalAlreadyExistsException(Integer id) {
        super("Animal " + id + " déjà existant");
    }
}
