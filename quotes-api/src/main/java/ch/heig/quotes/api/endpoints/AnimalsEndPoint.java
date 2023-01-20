package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.AnimalEntity;
import ch.heig.quotes.api.repositories.AnimalRepository;
import org.openapitools.api.AnimalsApi;
import ch.heig.quotes.api.exceptions.QuoteNotFoundException;
import org.openapitools.model.Animal;
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
public class AnimalsEndPoint implements AnimalsApi {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public ResponseEntity<List<Animal>> getAnimals() {
        List<AnimalEntity> animalEntities= animalRepository.findAll();
        List<Animal> animals  = new ArrayList<>();
        for (AnimalEntity animalEntity : animalEntities) {
            Animal animal = new Animal();
            animal.setId(animalEntity.getId());
            animal.setSpecies(animalEntity.getSpecies());
            animal.setName(animalEntity.getName());
            animal.setNoise(animalEntity.getNoise());
            animals.add(animal);
        }
        return new ResponseEntity<List<Animal>>(animals,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addAnimal(@RequestBody Animal animal) {
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setId(animal.getId());
        animalEntity.setSpecies(animal.getSpecies());
        animalEntity.setName(animal.getName());
        animalEntity.setNoise(animal.getNoise());
        AnimalEntity animalAdded = animalRepository.save(animalEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(animalAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Animal> getAnimal(Integer id){
        Optional<AnimalEntity> opt = animalRepository.findById(id);
        if (opt.isPresent()) {
            AnimalEntity animalEntity = opt.get();
            Animal animal = new Animal();
            animal.setId(animalEntity.getId());
            animal.setSpecies(animalEntity.getSpecies());
            animal.setName(animalEntity.getName());
            animal.setNoise(animalEntity.getNoise());
            return new ResponseEntity<Animal>(animal, HttpStatus.OK);
        } else {
            throw new QuoteNotFoundException(id);
        }
    }
}
