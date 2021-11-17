# Assecor Assessment Test (DE)

## REST Schnittstelle

$ mvn spring-boot:run

$ curl --location --request POST 'localhost:8080/api/csv/upload' --form 'file=@<path-to-csv-file>/sample-input.csv'

Zugriff auf die In-Memory Datenbanken

localhost:8080/h2-console


**GET** /api/csv/users
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

**GET** /api/csv/users/{id}

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

**GET** /api/csv/users/color/{color}
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