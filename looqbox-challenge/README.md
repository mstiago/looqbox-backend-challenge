# POKE API LOOQBOX

Looqbox  challenge with poke-api

## Running project

### Build project

To build the Dockerfile

```
docker build poke-api .
```

### Run the docker container
```
docker-compose -f up
```

## API Endpoints

To use the API send a get request to the endpoint below.
```
http://localhost:8080/pokemons?q={searchquery}
```

### Query Params
The parameter ***q*** is mandatory <br>
The ***orderBy*** parameter is not mandatory, the default value is ***length***.

```
http://localhost:8080/pokemons?q={searchquery}&orderBy=length
```
or
```
http://localhost:8080/pokemons?q={searchquery}&orderBy=alphabetical
```