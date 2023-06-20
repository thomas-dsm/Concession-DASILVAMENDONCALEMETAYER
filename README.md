# Concession-DASILVAMENDONCALEMETAYER

This project uses Quarkus, the Supersonic Subatomic Java Framework.

Realised by Thomas DA SILVA MENDONCA and Paul LEMETAYER
## RUNNING PROJECT

If needed to modify MONGO var in .env file with your mongodb url ! (default : `mongodb://localhost:27017)

You can import your data from `/docs/collections/*`into your mongodb with compass

### RUNNING DEV
```shell script
./mvnw compile quarkus:dev
```
(for MacOS)
```shell script
mvn compile quarkus:dev
```

### PACKAGE & RUNNING PROJECT
```shell script
./mvnw package
```
(for MacOS)
```shell script
mvn package
```

## USING API

### VOITURE

* GET `/voiture/getAll`
 Return 200 with list of voitures json

* GET `/voiture/get/{immat}`
  Return 200 with a voiture json

* POST `/voiture/add`
  Return 201 if voiture is created

example of body :
```
{
    "marqueId" : "6487087d23e131b8da0bd0d6",
    "immat": "TE-000-ST",
    "dateImmat": "2023-06-14T00:00:00.000+00:00",
    "prix": 40000,
    "type": [
        "sportive",
        "suv"
    ],
    "caracteristiques": {
        "puissance": 600,
        "poids": 1000,
        "longueur": 4,
        "largeur": 3,
        "carburant": "E-95"
    },
    "couleur": "000000"
}
```

* UPDATE `/voiture/update/{immat}`
  Return 204 if voiture is modified

example of body :
```
{
    "marqueId": "6487087d23e131b8da0bd0d4",
    "immat": "TE-111-ST",
    "dateImmat": "2023-06-14T00:00:00.000+00:00",
    "prix": 34312.43,
    "type": [
        "sportive",
        "pickup"
    ],
    "caracteristiques": {
        "puissance": 2000,
        "poids": 4000,
        "longueur": 5,
        "largeur": 3,
        "carburant": "E-98"
    },
    "couleur": "EFD345"
}
```

* DELETE `voiture/get/{immat}`
  Return 204


### Marque

* GET `/marque/getAll`
  Return 200 with list of marques json

* GET `/marque/get/{nom}`
  Return 200 with a marque json

* POST `/marque/add`
  Return 201 if marque is created

example of body :
```
{
    "nom": "Test",
    "anneeCreation": 2023,
    "pays": "FRANCE"
}
```

* UPDATE `/marque/update/{nom}`
  Return 204 if marque is modified

example of body :
```
{
    "nom": "Test1",
    "anneeCreation": 2023,
    "pays": "SPAIN"
}
```

* DELETE `marque/delete/{nom}`
  Return 204

### Entretien

* GET `/entretien/get`
  Return 200 with list of entretien json

example of body :
```
{
  "immat": "AA-000-AA"
}
```

* POST `/entretien/add/{immat}`
  Return 201 if entretien is created

example of body :
```
{
    "date": "2022-08-12T14:50:16.000+00:00",
    "description": "vidange", 
    "garage": "Feu Vert"
}
```
