# Assecor Assessment Test (DE)

## REST Schnittstelle

$ mvn spring-boot:run

$ curl --location --request POST 'localhost:8080/api/csv/upload' --form 'file=@<path-to-csv-file>/sample-input.csv'

Zugriff auf die In-Memory Datenbanken

localhost:8080/h2-console


**GET** api/v1/person/persons
```json
[{
"id" : 1,
"name" : "Hans",
"lastname": "Müller",
"zipcode" : "67742",
"city" : "Lauterecken",
"color" : "blau"
},{
"id" : 2,
...
}]
```

**GET** api/v1/person/persons/{Id}

```json
{
"id" : 1,
"name" : "Hans",
"lastname": "Müller",
"zipcode" : "67742",
"city" : "Lauterecken",
"color" : "blau"
}
```

**GET** api/v1/person/persons/color/{Color}
```json
[{
"id" : 1,
"name" : "Hans",
"lastname": "Müller",
"zipcode" : "67742",
"city" : "Lauterecken",
"color" : "blau"
},{
"id" : 2,
...
}]
```