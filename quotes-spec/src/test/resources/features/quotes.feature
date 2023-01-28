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