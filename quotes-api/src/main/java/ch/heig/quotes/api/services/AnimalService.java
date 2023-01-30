package ch.heig.quotes.api.services;
import ch.heig.quotes.api.entities.AnimalEntity;
import ch.heig.quotes.api.entities.RaceEntity;
import ch.heig.quotes.api.exceptions.AnimalAlreadyExistsException;
import ch.heig.quotes.api.exceptions.AnimalNotFoundException;
import ch.heig.quotes.api.exceptions.RaceNotFoundException;
import ch.heig.quotes.api.repositories.AnimalRepository;
import ch.heig.quotes.api.repositories.RaceRepository;
import org.openapitools.model.Animal;
import org.openapitools.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    RaceRepository raceRepository;

    public ResponseEntity<Void> addAnimal(Animal animal) {
        if (raceRepository.findById(animal.getRace()).isEmpty())
            throw new RaceNotFoundException(animal.getRace());
        if (animalRepository.findById(animal.getId()).isPresent())
            throw new AnimalAlreadyExistsException(animal.getId());
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
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }

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
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    public ResponseEntity<Race> getAnimalRace(Integer id) {
        Optional<AnimalEntity> opt = animalRepository.findById(id);
        if (opt.isPresent()) {
            AnimalEntity animalEntity = opt.get();
            Race race = new Race();
            RaceEntity raceEntity = animalEntity.getRace();
            race.setId(raceEntity.getId());
            race.setName(raceEntity.getName());
            race.setDescription(raceEntity.getDescription());
            return new ResponseEntity<>(race, HttpStatus.OK);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }

    public ResponseEntity<Void> deleteAnimal(Integer id) {
        if (animalRepository.findById(id).isPresent()) {
            animalRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }

    public ResponseEntity<Void> updateAnimal(Integer id, Animal animal) {
        if (animalRepository.findById(animal.getId()).isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<AnimalEntity> opt = animalRepository.findById(id);
        if (opt.isPresent()) {
            animalRepository.updateAnimalById(
                    id,
                    animal.getName(),
                    animal.getNoise(),
                    animal.getSpecies(),
                    raceRepository.findById(animal.getRace())
                            .orElseThrow(() -> new RaceNotFoundException(animal.getRace())));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }

    public ResponseEntity<Void> putAnimal(Animal animal) {
        if (raceRepository.findById(animal.getRace()).isEmpty())
            throw new RaceNotFoundException(animal.getRace());
        Integer id = animal.getId();
        if (animalRepository.findById(id).isPresent()) {
            animalRepository.replaceAnimalById(
                    id,
                    animal.getName(),
                    animal.getNoise(),
                    animal.getSpecies(),
                    raceRepository.findById(animal.getRace())
                            .orElseThrow(() -> new RaceNotFoundException(animal.getRace())));
        } else {
            animalRepository.insertAnimal(
                    animal.getId(),
                    animal.getName(),
                    animal.getNoise(),
                    animal.getSpecies(),
                    animal.getRace());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(animal.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Void> mixAnimals(List<Animal> animals) {
        if (animals.size() != 2)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Animal animal1 = animals.get(0);
        Animal animal2 = animals.get(1);
        int id = Math.toIntExact(animalRepository.count() + 1);
        int race_id = Math.toIntExact(raceRepository.count() + 1);
        System.out.println("id: " + id + " race_id " + race_id);
        RaceEntity raceEntity = new RaceEntity();
        raceEntity.setId(race_id);
        raceEntity.setName(animal1.getSpecies() + " " + animal2.getSpecies());
        raceEntity.setDescription("A mixed race between " + animal1.getSpecies() + " and " + animal2.getSpecies());
        raceRepository.insertRace(
                race_id,
                animal1.getSpecies() + " " + animal2.getSpecies(),
                "A mixed race between " + animal1.getSpecies() + " and " + animal2.getSpecies());
        animalRepository.insertAnimal(
                id,
                "Abomination " + id,
                animal1.getNoise() + " " + animal2.getNoise(),
                animal1.getSpecies() + " " + animal2.getSpecies(),
                race_id
        );
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
