# Bienvenue au zoo
## Logique métier
Nous avons ajouté aux quotes 3 entités supplémentaires : Des animaux, des races et
des localisations. L'objectif est de pouvoir ajouter, modifier et supprimer des animaux.
Les animaux possèdent un identifiant (clé primaire), un nom, un bruit, une espèce et une race.
Une race possède un identifiant (clé primaire), un nom et une description.
Une localisation a un identifiant (clé primaire) et un nom.
Ce que nous souhaitons faire avec ces animaux, c'est de pouvoir les ajouter, les modifier et les supprimer.
Mais aussi pouvoir mixer des animaux, passés dans le corps de la requête.

## Routes
Les routes que nous avons créées pour les animaux sont les suivantes :
- GET /animals : récupère tous les animaux
- GET /animals/{id} : récupère un animal par son identifiant
- GET /animals/{id}/race : récupère la race d'un animal
- POST /animals : ajoute un animal
- PUT /animals : modifie ou crée un animal
- PATCH /animals/{id} : modifie un animal
- DELETE /animals/{id} : supprime un animal
- POST /animals/mix : créer un animal résultant du mix de deux animaux

Les routes pour les races :
- GET /races : obtient toutes les races
- GET /races/{id} : obtient une race par son identifiant

Les routes pour les localisations :
- GET /locations : récupère toutes les localisations
- GET /locations/{id} : récupère une localisation par son identifiant
- GET /locations/{id}/races : récupère les races d'une localisation par son identifiant
- GET /locations/{id}/animals : récupère les animaux d'une localisation par son identifiant
- POST /locations : ajoute une localisation
- POST /locations/{id}/races : ajoute une race à la localisation par son identifiant

## Relations entre les entités
### One To Many et Many To One
Afin d'avoir ces deux types de relation, nous avons décidé d'ajouter une relation One To Many
bidirectionnelle entre un Animal et une Race. Un Animal a une seule race et une race peut
être assignées à plusieurs animaux. Il est donc possible de récupérer la race d'un animal et
tous les animaux d'une race.

### Many To Many
Nous avons également décidé d'ajouter une relation Many To Many entre une race et une localisation.
Une race peut être présente dans plusieurs localisations et une localisation peut contenir plusieurs races.
Cette relation implique l'ajout d'une table *locations_races* qui référence les relations existantes.

## Tests avec cucumber
Nous avons ajouté des scénarios pour tester les endpoints permettant d'effectuer des opérations
CRUD sur les animaux. Les endpoints permettant de lire les races et les localisations ont aussi été
testé. Ce que nous testons dans ces scenarios sont les status code de retour des appels à l'api.
Les scenarios sont dans le fichier [quotes.feature](quotes-spec/src/test/resources/features/quotes.feature) et
l'implémentation des méthodes utilisée par Cucumber sont dans le fichier
[AnimalRaceLocationSteps](quotes-spec/src/test/java/ch/heig/quotes/spec/AnimalRaceLocationSteps.java).

## Lien vers la vidéo
Désolée pour la bannière free trial de WMM... Mais c'est qu'à l'exportation que nous avons vu que la vidéo
nous coûterait 39.99$... Bon visonnage....
[![Vidéo](https://img.youtube.com/vi/0Z9Z9Z9Z9Z9/0.jpg)](*lien*)