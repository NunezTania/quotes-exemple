package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.AnimalEntity;
import ch.heig.quotes.api.entities.RaceEntity;
import ch.heig.quotes.api.exceptions.AnimalNotFoundException;
import ch.heig.quotes.api.exceptions.RaceNotFoundException;
import ch.heig.quotes.api.repositories.AnimalRepository;
import ch.heig.quotes.api.repositories.RaceRepository;
import org.openapitools.api.AnimalsApi;
import org.openapitools.model.Animal;
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

@RestController
public class AnimalsEndPoint implements AnimalsApi {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private RaceRepository raceRepository;

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
            animal.setRace(animalEntity.getRace().getId());
            animals.add(animal);
        }
        return new ResponseEntity<List<Animal>>(animals,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addAnimal(@RequestBody Animal animal) {
        animalRepository.insertAnimal(animal.getId(),
                animal.getName(),
                animal.getNoise(),
                animal.getSpecies(),
                animal.getRace());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(animal.getId())
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
            animal.setRace(animalEntity.getRace().getId());
            return new ResponseEntity<Animal>(animal, HttpStatus.OK);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<Race> getAnimalRace(Integer id) {
        Optional<AnimalEntity> opt = animalRepository.findById(id);
        if (opt.isPresent()) {
            AnimalEntity animalEntity = opt.get();
            Race race = new Race();
            RaceEntity raceEntity = animalEntity.getRace();
            race.setId(raceEntity.getId());
            race.setName(raceEntity.getName());
            race.setDescription(raceEntity.getDescription());
            return new ResponseEntity<Race>(race, HttpStatus.OK);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAnimal(Integer id) {
        if (animalRepository.findById(id).isPresent()) {
            animalRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }
    @Override
    public ResponseEntity<Void> updateAnimal(Integer id, @RequestBody Animal animal) {
        Optional<AnimalEntity> opt = animalRepository.findById(id);
        if (opt.isPresent()) {
            animalRepository.updateAnimalById(
                    id,
                    animal.getName(),
                    animal.getNoise(),
                    animal.getSpecies(),
                    raceRepository.findById(animal.getRace())
                            .orElseThrow(() -> new RaceNotFoundException(animal.getRace())));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(animal.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            throw new AnimalNotFoundException(id);
        }
    }
    @Override
    public ResponseEntity<Void> replaceAnimal(Integer id, @RequestBody Animal animal) {
        if (animalRepository.findById(id).isPresent()) {
            animalRepository.replaceAnimalById(
                    id,
                    animal.getId(),
                    animal.getName(),
                    animal.getNoise(),
                    animal.getSpecies(),
                    raceRepository.findById(animal.getRace())
                            .orElseThrow(() -> new RaceNotFoundException(animal.getRace())));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(animal.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            throw new AnimalNotFoundException(id);
        }
    }
}
