# Concert & Venues List

#### Created By
Isabelle George

## Description
This is the code review for week 4 of the Epicodus Java class featuring a concert list, allowing the user to add a list of bands and venues to the database and list which venues have hosted which bands and which bands have played at certain venues. Utilizing the many-to-many attribute, bands may have played at many venues, and venues may have hosted many bands.

Enjoy!

###Technologies
- HTML/CSS
- Java
- JUnit/FluentLenium
- Spark/Velocity
- Postgres/SQL

#### Setup/Installation Requirements

** See required technologies above **
In the terminal:
```
$ git clone https://github.com/10igeorge/java-codereview4.git
$ postgres
$ psql
```
While in git directory, run in psql:
```
# CREATE DATABASE bands_venues;
```
Back in the terminal:
```
$ psql bands_venues < bands_venues.sql
$ gradle run
```
* open browser to localhost:4567/

##### License

Copyright (c) 2016 Isabelle George
