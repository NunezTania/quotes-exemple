
package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.AnimalEntity;
import ch.heig.quotes.api.repositories.AnimalRepository;
import org.openapitools.api.QuotesApi;
import ch.heig.quotes.api.exceptions.QuoteNotFoundException;
import org.openapitools.model.Quote;
import ch.heig.quotes.api.entities.QuoteEntity;
import ch.heig.quotes.api.repositories.QuoteRepository;
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


public class AnimalsEndPoint {
    @Autowired
    private AnimalRepository animalRepository;

    /*
    @Override
    public ResponseEntity<List<Animal>> getAnimals() {
        List<AnimalEntity> animalEntities= animalRepository.findAll();
        List<Animal> animals  = new ArrayList<>();
        for (AnimalEntity animalEntity : animalEntities) {
            Animal animal = new Animal();
            animal.setId(animalEntity.getId());
            animal.setSpecies(animalEntity.getSpecies());
            animal.setAge(animalEntity.getAge());
            animals.add(animal);
        }
        return new ResponseEntity<List<Animal>>(animals,HttpStatus.OK);
    }

     */

}
