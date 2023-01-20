package ch.heig.quotes.api.exceptions;

public class RaceNotFoundException extends RuntimeException {
    public RaceNotFoundException(Integer id) {
        super("Race " + id + " non trouvée");
    }
}
