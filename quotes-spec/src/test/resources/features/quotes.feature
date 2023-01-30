Feature: Application quotes

Scenario: Register a new quote
  Given I have an quote payload
  When I POST it to the /quotes endpoint
  Then I receive a 201 status code

Scenario: Register a new animal with valid id and valid race
  Given I have an animal payload with id 11 and race 1
  When I POST it to the /animals endpoint
  Then I receive a 201 status

Scenario: Register a new animal with invalid id
  Given I have an animal payload with id 1 and race 1
  When I POST it to the /animals endpoint
  Then I receive a 400 status

Scenario: Register a new animal with invalid race
  Given I have an animal payload with id 12 and race 10
  When I POST it to the /animals endpoint
  Then I receive a 404 status

Scenario: Get all the animals
  When I GET to the /animals endpoint
  Then I receive a 200 status

Scenario: Get an animal with a valid id
  When I GET to the /animal/1 endpoint
  Then I receive a 200 status

Scenario: Get an animal with an invalid id
  When I GET to the /animal/99 endpoint
  Then I receive a 404 status

Scenario: Get an animal race with a valid id
  When I GET to the /animal/1/race endpoint
  Then I receive a 200 status

Scenario: Get an animal race with a invalid id
  When I GET to the /animal/99/race endpoint
  Then I receive a 404 status

Scenario: Update an existing animal
  Given I have an animal payload with id 1 and race 1
  When I PATCH it to the /animal/1 endpoint
  Then I receive a 202 status

Scenario: Update a non existing animal
  Given I have an animal payload with id 1 and race 1
  When I PATCH it to the /animal/99 endpoint
  Then I receive a 404 status

Scenario: Put an existing animal
  Given I have an animal payload with id 1 and race 3
  When I PUT it to the /animals endpoint
  Then I receive a 201 status

Scenario: Put a non existing animal
  Given I have an animal payload with id 13 and race 3
  When I PUT it to the /animals endpoint
  Then I receive a 201 status

Scenario: Put an animal with invalid race
  Given I have an animal payload with id 13 and race 99
  When I PUT it to the /animals endpoint
  Then I receive a 404 status

Scenario: Delete an existing animal
  When I DELETE to the /animal/1 endpoint
  Then I receive a 204 status

Scenario: Delete a non existing animal
  When I DELETE to the /animal/99 endpoint
  Then I receive a 404 status

Scenario: Get all races
  When I GET to the /races endpoint
  Then I receive a 200 status

Scenario: Get a race with valid it
  When I GET to the /race/1 endpoint
  Then I receive a 200 status

Scenario: Get a race with invalid it
  When I GET to the /race/99 endpoint
  Then I receive a 404 status

Scenario: Get all locations
  When I GET to the /locations endpoint
  Then I receive a 200 status

Scenario: Get a location with valid it
  When I GET to the /location/1 endpoint
  Then I receive a 200 status

Scenario: Get a location with invalid it
  When I GET to the /location/99 endpoint
  Then I receive a 404 status

Scenario: Get a location races with valid it
  When I GET to the /location/1/races endpoint
  Then I receive a 200 status

Scenario: Get a location races with invalid it
  When I GET to the /location/99/races endpoint
  Then I receive a 404 status

Scenario: Get a location animals with valid it
  When I GET to the /location/1/animals endpoint
  Then I receive a 200 status

Scenario: Get a location animals with invalid it
  When I GET to the /location/99/animals endpoint
  Then I receive a 404 status

Scenario: Add a new location with valid id
  Given I have a location payload with id 6
  When I POST it to the /locations endpoint
  Then I receive a 201 status

Scenario: Add a new location with valid id
  Given I have a location payload with id 1
  When I POST it to the /locations endpoint
  Then I receive a 400 status