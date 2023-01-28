insert into quotes values (1,'Dr. Seuss', 'Don''t cry because it''s over, smile because it happened.');
insert into quotes values (2,'Oscar Wilde', 'Be yourself; everyone else is already taken.');
insert into quotes values (3,'Albert Einstein', 'Two things are infinite: the universe and human stupidity; and I''m not sure about the universe.');

insert into races values (1, 'Dog', 'A domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, non-retractable claws, and a barking, howling, or whining voice.');
insert into races values (2, 'Cat', 'The cat (Felis catus) is a domestic species of small carnivorous mammal. It is the only domesticated species in the family Felidae and is commonly referred to as the domestic cat or house cat to distinguish it from the wild members of the family.');
insert into races values (3, 'Cow', 'a fully grown female animal of a domesticated breed of ox, kept to produce milk or beef.
"a dairy cow"');
insert into races values (4, 'Horse', 'a large, solid-hoofed, herbivorous mammal, typically with a short mane and tail, found in a wide variety of breeds.');
insert into races values (5, 'Pig', 'a domesticated animal of the family Suidae');
insert into races values (6, 'Lion', 'a large, powerful, predatory cat of the genus Panthera, with a tawny coat marked with black rosettes. It is the second-largest living cat after the tiger.');
insert into races values (7, 'Zebra', 'a large African equine with a distinctive coat pattern of black and white stripes.');

insert into animals values (1, 'Dagobert','Woof','Dog', 1);
insert into animals values (2, 'Catty','Meow', 'Cat', 2);
insert into animals values (3, 'Bertrand', 'Meow', 'Cat', 2);
insert into animals values (4, 'Marguerite','Moo', 'Cow', 3);
insert into animals values (5, 'Gertrude', 'Moo', 'Cow', 3);
insert into animals values (6, 'Geraldine', 'Moo', 'Cow', 3);
insert into animals values (7, 'Bozic','Gruik Gruik', 'Pig', 5);
insert into animals values (8, 'Richard','Neigh', 'Horse', 4);
insert into animals values (9, 'Jean-Charles','Roooar', 'Lion', 6);
insert into animals values (10, 'Luc','Savana Neigh', 'Zebra', 7);

insert into locations values (1, 'In your house');
insert into locations values (2, 'In the street');
insert into locations values (3, 'In grassland');
insert into locations values (4, 'In the savana');
insert into locations values (5, 'In the farm');

insert into locations_races values (1, 1);
insert into locations_races values (1, 2);
insert into locations_races values (2, 1);
insert into locations_races values (2, 2);
insert into locations_races values (3, 3);
insert into locations_races values (3, 5);
insert into locations_races values (4, 3);
insert into locations_races values (4, 5);
insert into locations_races values (5, 5);
insert into locations_races values (6, 4);
insert into locations_races values (7, 4);
