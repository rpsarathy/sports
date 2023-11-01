# sports

Coding Assignment: Sports Player
Management System

Scope:
You are tasked with creating a production-ready solution to manage sports players and their
associated sports. This involves database schema design, SQL queries, and API endpoints. Follow best
practices, implement proper error handling, and include test cases.

A) Database Schema Design and SQL

(A-1) Database Tables Structure

Create the following database tables:

Table: players
email (not null)
level (1 ~ 10 / not null)
age (not null)
gender (male or female)

Table: sports
name (not null) (e.g. tennis, soccer, basketball)

Table Relations:
A player can be associated with multiple sports.
A sport can be associated with multiple players.

(A-1) ORM Query for Player Retrieval
Write an ORM query to retrieve players with a given gender, level, and age (consider these as "AND"
conditions).
Add a comment with the equivalent raw SQL query below the ORM code.

(A-2) ORM Query for Sports Retrieval
Write an ORM query to retrieve sports with multiple (≥ 2) associated players.

(A-3) ORM Query for Sports with No Players
Write an ORM query to retrieve sports with no associated players.

B) API Endpoints

(B-1) API Endpoint: Get Sports with Associated Players
Create an API endpoint to return sports with given names (1 or more names) in JSON format.
Associated players should also be returned.
Handle failure cases gracefully.

(B-2) API Endpoint: Get Players with No Sports
Create an API endpoint to return players with no associated sports.

(B-3) API Endpoint: Update Player Sports
Create an API endpoint to update a player's associated sports.

(B-4) API Endpoint: Delete Sports and Associated Data
Create an API endpoint to delete sports and their associated data.

(B-5) API Endpoint: Paginated Player List with Sports Filter
Implement an API endpoint to provide a paginated player list with sports filtering.
Pagination should be by 10 players per page.

The requirement to run the application :

Java 17

Maven

IDE

SpringBoot

H2 DB (MODE=MYSQL)

Postman for to test




