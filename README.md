# puzzleGame

Description


This repository contains a Spring Boot application written in Java 22 for playing 15 Puzzle Game. The application provides endpoints to start a game and move tiles.

Prerequisites

    JDK 17 installed
    Maven installed
    IDE (optional)

Setup

    1. Clone the repository: https://github.com/danielapetrescu/puzzleGame
    2. Navigate to the project directory: cd puzzleGame
    3. Build the project using Maven: mvn clean install
    4. Run the application: mvn spring-boot:run 
Or, if you have an IDE, you can run the GamersApplication class directly.

Usage

    Once the application is running, you can access it at http://localhost:4001.
    The following endpoints are available:
        1. "GET /puzzleGame/api/startGame": Start a new game (requires name of the player )
        2. "GET /puzzleGame/api/left": Move empty tile to left (requires name of the player )
        3. "GET /puzzleGame/api/right": Move empty tile to right (requires name of the player )
        4. "GET /puzzleGame/api/up": Move empty tile to up (requires name of the player )
        5. "GET /puzzleGame/api/down": Move empty tile to down (requires name of the player )

Configuration.com

    The application configuration can be found in the application.properties file, where you can modify properties such as server port, etc.

Dependencies

    Spring Boot Starter Web: for building web applications with Spring MVC
    Spring Security Core: for handling exception
    Junit Jupiter: for testing
    Model Mapper: for mapping the response
