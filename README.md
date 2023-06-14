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
    "marqueId" : "647b689220b05566a410e5b1",
    "immat": "AA-000-AA",
    "dateImmat": "2012-03-26T23:20:16.000+00:00",
    "prix": 40000,
    "type": [
        "sportive"
    ],
    "caracteristiques": {
        "puissance": 1000,
        "poids": 2000,
        "longueur": 5,
        "largeur": 3,
        "carburant": "ethanol"
    },
    "couleur": "FFFFFF"
}
```

* UPDATE `/voiture/getAll`
  Return 204 if voiture is modified

example of body :
```
{
    "marqueId" : "647b689220b05566a410e5b1",
    "immat": "AA-000-AA",
    "dateImmat": "2012-03-26T23:20:16.000+00:00",
    "prix": 40000,
    "type": [
        "sportive"
    ],
    "caracteristiques": {
        "puissance": 1000,
        "poids": 2000,
        "longueur": 5,
        "largeur": 3,
        "carburant": "ethanol"
    },
    "couleur": "FFFFFF"
}
```

* DELETE `voiture/get/{immat}`
  Return 204 if voiture is deleted


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
  "nom": "Renauld",
  "anneeCreation": 1970,
  "pays": "FRANCE"
}
```

* UPDATE `/marque/getAll`
  Return 204 if marque is modified

example of body :
```
{
  "nom": "Renauld",
  "anneeCreation": 1970,
  "pays": "FRANCE"
}
```

* DELETE `marque/get/{nom}`
  Return 204 if marque is deleted

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
  "date": "2023-06-12T14:50:16.000+00:00",
  "description": "vidange", 
  "garage": "Garage de Toto"
}
```
