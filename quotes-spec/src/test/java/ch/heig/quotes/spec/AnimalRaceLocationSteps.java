package ch.heig.quotes.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.AnimalsEndPointApi;
import org.openapitools.client.api.RacesEndPointApi;
import org.openapitools.client.api.LocationsEndPointApi;
import org.openapitools.client.model.Animal;
import org.openapitools.client.model.Race;
import org.openapitools.client.model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalRaceLocationSteps {

    private final AnimalsEndPointApi animalApi = new AnimalsEndPointApi();
    private final RacesEndPointApi raceApi = new RacesEndPointApi();
    private final LocationsEndPointApi locationApi = new LocationsEndPointApi();

    private int statusCode;

    private Animal animal;

    @Given("I have an animal payload with id {int} and race {int}")
    public void i_have_an_animal_payload(int arg1, int arg2) throws Throwable {
        animal = new Animal();
        animal.setId(arg1);
        animal.setName("Toto");
        animal.setNoise("Cucumber");
        animal.setSpecies("Concombre");
        animal.setRace(arg2);
    }

    @When("I GET to the \\/animals endpoint")
    public void i_get_to_the_animals_endpoint() throws Throwable {
        try {
            ApiResponse response = animalApi.getAnimalsWithHttpInfo();
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I GET to the \\/animal\\/{int} endpoint")
    public void i_get_to_the_animal_with_id_endpoint(int arg1) throws Throwable {
        try {
            ApiResponse response = animalApi.getAnimalWithHttpInfo(arg1);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I GET to the \\/animal\\/{int}\\/race endpoint")
    public void i_get_to_the_animal_with_id_race_endpoint(int arg1) throws Throwable {
        try {
            ApiResponse response = animalApi.getAnimalRaceWithHttpInfo(arg1);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I DELETE to the \\/animal\\/{int} endpoint")
    public void i_DELETE_the_animal_with_id_endpoint(int arg1) throws Throwable {
        try {
            ApiResponse response = animalApi.deleteAnimalWithHttpInfo(arg1);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I PUT it to the \\/animals endpoint")
    public void i_PUT_it_to_the_animal_id_endpoint() throws Throwable {
        try {
            ApiResponse response = animalApi.putAnimalWithHttpInfo(animal);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I PATCH it to the \\/animal\\/{int} endpoint")
    public void i_PATCH_it_to_the_animal_id_endpoint(int arg1) throws Throwable {
        try {
            ApiResponse response = animalApi.updateAnimalWithHttpInfo(arg1, animal);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I POST it to the \\/animals endpoint")
    public void i_POST_it_to_the_animals_endpoint() throws Throwable {
        try {
            ApiResponse response = animalApi.addAnimalWithHttpInfo(animal);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I GET to the \\/races endpoint")
    public void i_GET_to_the_races_endpoint() throws Throwable {
        try {
            ApiResponse response = raceApi.getRacesWithHttpInfo();
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I GET to the \\/race\\/{id} endpoint")
    public void i_GET_to_the_race_with_id_endpoint(int arg1) throws Throwable {
        try {
            ApiResponse response = raceApi.getRaceWithHttpInfo(arg1);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I GET to the \\/locations endpoint")
    public void i_GET_to_the_locations_endpoint() throws Throwable {
        try {
            ApiResponse response = locationApi.getLocationsWithHttpInfo();
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @When("I GET to the \\/location\\/{id} endpoint")
    public void i_GET_to_the_location_with_id_endpoint(int arg1) throws Throwable {
        try {
            ApiResponse response = locationApi.getLocationWithHttpInfo(arg1);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }
}
