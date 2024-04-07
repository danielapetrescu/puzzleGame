package org.danskebank.puzzlegame.controller;

import org.danskebank.puzzlegame.dto.PlayerResponse;
import org.danskebank.puzzlegame.entities.Move;
import org.danskebank.puzzlegame.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }
    @PostMapping("/startGame")
    public ResponseEntity<PlayerResponse> startGame(@RequestParam String name){
        PlayerResponse playerResponse = gameService.startGame(name);
        return new ResponseEntity<>(playerResponse, HttpStatus.OK);
    }

    @GetMapping("/right")
    public PlayerResponse moveRight(@RequestParam String playerName){
        return gameService.moveTile(playerName, Move.RIGHT);
    }

    @GetMapping("/left")
    public PlayerResponse moveLeft(@RequestParam String playerName){
        return gameService.moveTile(playerName, Move.LEFT);
    }

    @GetMapping("/up")
    public PlayerResponse moveUp(@RequestParam String playerName){
        return gameService.moveTile(playerName, Move.UP);
    }

    @GetMapping("/down")
    public PlayerResponse moveDown(@RequestParam String playerName){
        return gameService.moveTile(playerName, Move.DOWN);
    }
}

