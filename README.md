# Bookadaisical


## Description

The scope of this project is to create an online platform where people can trade/exchange their books between them.

***

## Dependencies

- Java 17
- Angular
- Postgresql

***

## Getting started

### Database
```
cd database/
psql -U <username> -a -f ./create-database.pgsql #this will create the databse and also a user
psql -U bookadaisicalBackend -d bookadaisicalDatabase -a -f ./create-tables.pgsql 
```

### Backend
```
cd backend/
./mvnm spring-boot:build
```

### Frontend
```
cd frontend/
npm install
ng build
```

***

## Run the project on localhost
### Backend
```
cd backend/
./mvnm spring-boot:run
```

### Frontend
```
cd frontend/
ng serve
```

***

## Authors
* Diana Gliga
* David Dudas
* David Papava

***

## Project status

Work In Progress. No stable releases yet.
