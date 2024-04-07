package org.danskebank.puzzlegame.service;

import org.danskebank.puzzlegame.dto.PlayerResponse;
import org.danskebank.puzzlegame.entities.Move;
import org.danskebank.puzzlegame.entities.Player;
import org.danskebank.puzzlegame.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GameService {
    private Map<String, Player> gamesMap = new HashMap();
    private List<Integer> game = new ArrayList();

    {
        for (int i = 0; i <= 15; i++) {
            game.add(i);
        }
    }
    private ModelMapper modelMapper = new ModelMapper();
    public Map<String, Player> getGamesMap() {
        return gamesMap;
    }

    public void setGamesMap(Map<String, Player> gamesMap) {
        this.gamesMap = gamesMap;
    }

    public PlayerResponse startGame(String playerName) {
        ArrayList<Integer> newGame = new ArrayList(game);
        Collections.shuffle(newGame);
        Player newPlayer = new Player(playerName, newGame);
        gamesMap.put(playerName, newPlayer);
        return modelMapper.map(newPlayer, PlayerResponse.class);
    }

    public PlayerResponse moveTile(String playerName, Move moveDirection) {
        Optional<Player> optPlayer = Optional.ofNullable(gamesMap.get(playerName));
        if(optPlayer.isPresent()){
            Player player = optPlayer.get();
            List<Integer> newGame = verifyAndMoveTile(player, moveDirection);
            Utils.setGameStatus(newGame, player);
            return modelMapper.map(player, PlayerResponse.class);
        }
        throw new UsernameNotFoundException("Player is not known");
    }

    private List<Integer> verifyAndMoveTile(Player player, Move moveDirection){
        List<Integer> newGame = player.getGame();
        int indexOfBlackTile = newGame.indexOf(0);
        switch(moveDirection){
            case Move.RIGHT:
                if(Utils.isRightMovePossible(indexOfBlackTile)) {
                    newGame = moveRight(player.getGame());
                    player.setGame(newGame);
                } else {
                    throw new IllegalArgumentException("The right move is not possible");
                }
                break;
            case Move.LEFT:
                if(Utils.isLeftMovePossible(indexOfBlackTile)) {
                    newGame = moveLeft(player.getGame());
                    player.setGame(newGame);
                } else {
                    throw new IllegalArgumentException("The left move is not possible");
                }
                break;
            case Move.UP:
                if(Utils.isUpMovePossible(indexOfBlackTile)) {
                    newGame = moveUp(player.getGame());
                    player.setGame(newGame);
                } else {
                    throw new IllegalArgumentException("The up move is not possible");
                }
                break;
            case Move.DOWN:
                if(Utils.isDownMovePossible(indexOfBlackTile)) {
                    newGame = moveDown(player.getGame());
                    player.setGame(newGame);
                } else {
                    throw new IllegalArgumentException("The down move is not possible");
                }
                break;
        }
        return newGame;
    }
    private List<Integer> moveDown(List<Integer> game) {
        return game;
    }

    private List<Integer> moveUp(List<Integer> game) {
        return game;
    }

    private List<Integer> moveLeft(List<Integer> game) {
        return game;
    }

    private List<Integer> moveRight(List<Integer> game) {
        return game;
    }
}
