package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.services.AnimalService;
import org.openapitools.api.AnimalsApi;
import org.openapitools.model.Animal;
import org.openapitools.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalsEndPoint implements AnimalsApi {

    @Autowired
    private AnimalService animalService;

    @Override
    public ResponseEntity<List<Animal>> getAnimals() {
        return animalService.getAnimals();
    }

    @Override
    public ResponseEntity<Void> addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }
    @Override
    public ResponseEntity<Animal> getAnimal(Integer id){
        return animalService.getAnimal(id);
    }

    @Override
    public ResponseEntity<Race> getAnimalRace(Integer id) {
        return animalService.getAnimalRace(id);
    }

    @Override
    public ResponseEntity<Void> deleteAnimal(Integer id) {
        return animalService.deleteAnimal(id);
    }
    @Override
    public ResponseEntity<Void> updateAnimal(Integer id, @RequestBody Animal animal) {
        return animalService.updateAnimal(id, animal);
    }
    @Override
    public ResponseEntity<Void> putAnimal(@RequestBody Animal animal) {
        return animalService.putAnimal(animal);
    }

    @Override
    public ResponseEntity<Void> mixAnimals(@RequestBody List<Animal> animals) {
        return animalService.mixAnimals(animals);
    }
}
