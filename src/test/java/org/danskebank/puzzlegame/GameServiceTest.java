package org.danskebank.puzzlegame;

import org.danskebank.puzzlegame.dto.PlayerResponse;
import org.danskebank.puzzlegame.entities.Move;
import org.danskebank.puzzlegame.entities.Player;
import org.danskebank.puzzlegame.service.GameService;
import org.danskebank.puzzlegame.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameServiceTest {
    private GameService gameService = new GameService();
    private String playerName = "John";

    @Test
    @DisplayName("Test should pass when setting map works correctly")
    public void test_getGames() {
        Player player = new Player(playerName, new ArrayList<>());
        Map<String, Player> playerMap = new HashMap<>();
        playerMap.put(playerName, player);
        gameService.setGamesMap(playerMap);

        assertTrue(gameService.getGamesMap().equals(playerMap));
    }

    @Test
    @DisplayName("Test should pass when starting a new game returns a valid game")
    public void test_startGame() {
        PlayerResponse player = startGame();
        boolean validList = testIfListIsValid(player.getGame());

        assertTrue(validList);
    }

    @Test
    @DisplayName("Should throw an exception when a move is ask but the player is unknown")
    public void test_startGame_whenPlayerIsUnknown() {
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                () -> gameService.moveTile(playerName, Move.LEFT));

        assertTrue(exception.getMessage().contains("Player is not known"));
    }

    @Test
    @DisplayName("Should pass when a left move returns a valid game")
    public void test_moveLeft() {
        setPlayerForGameService(Utils.getListWhenAllMoveArePosible());
        PlayerResponse newPlayer = gameService.moveTile(playerName, Move.LEFT);
        boolean validList = testIfListIsValid(newPlayer.getGame());

        assertTrue(validList);
    }

    @Test
    @DisplayName("Should throw an exception when a left move is not possible")
    public void test_invalidLeftMove(){
        setPlayerForGameService(Utils.getListWhenUpAndLeftMoveIsImposible());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> gameService.moveTile(playerName, Move.LEFT));

        assertTrue(exception.getMessage().contains("The left move is not possible"));
    }

    @Test
    @DisplayName("Should pass when a right move returns a valid game")
    public void test_moveRight() {
        setPlayerForGameService(Utils.getListWhenAllMoveArePosible());

        PlayerResponse newPlayer = gameService.moveTile(playerName, Move.RIGHT);
        boolean validList = testIfListIsValid(newPlayer.getGame());

        assertTrue(validList);
    }
    @Test
    @DisplayName("Should throw an exception when a right move is not possible")
    public void test_invalidRightMove(){
        setPlayerForGameService(Utils.getListWhenDownAndRightMoveIsImposible());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> gameService.moveTile(playerName, Move.RIGHT));

        assertTrue(exception.getMessage().contains("The right move is not possible"));
    }
    @Test
    @DisplayName("Should pass when a up move returns a valid game")
    public void test_moveUp() {
        setPlayerForGameService(Utils.getListWhenAllMoveArePosible());

        PlayerResponse newPlayer = gameService.moveTile(playerName, Move.UP);
        boolean validList = testIfListIsValid(newPlayer.getGame());

        assertTrue(validList);
    }
    @Test
    @DisplayName("Should throw an exception when a up move is not possible")
    public void test_invalidUpMove(){
        setPlayerForGameService(Utils.getListWhenUpAndLeftMoveIsImposible());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> gameService.moveTile(playerName, Move.UP));

        assertTrue(exception.getMessage().contains("The up move is not possible"));
    }
    @Test
    @DisplayName("Should pass when a down move returns a valid game")
    public void test_moveDown() {
        setPlayerForGameService(Utils.getListWhenAllMoveArePosible());

        PlayerResponse newPlayer = gameService.moveTile(playerName, Move.DOWN);
        boolean validList = testIfListIsValid(newPlayer.getGame());

        assertTrue(validList);
    }
    @Test
    @DisplayName("Should throw an exception when a down move is not possible")
    public void test_invalidDownMove(){
        setPlayerForGameService(Utils.getListWhenDownAndRightMoveIsImposible());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> gameService.moveTile(playerName, Move.DOWN));

        assertTrue(exception.getMessage().contains("The down move is not possible"));
    }
    @Test
    @DisplayName("Should pass when a up move returns a finished game")
    public void test_moveUAndGameOver() {
        setPlayerForGameService(Utils.getGameSolved());

        PlayerResponse newPlayer = gameService.moveTile(playerName, Move.UP);

        assertTrue(newPlayer.isGameSolved());
    }
    private void setPlayerForGameService(List<Integer> game){
        Player player = new Player(playerName, game);
        Map<String, Player> playerMap = new HashMap<>();
        playerMap.put(playerName, player);
        gameService.setGamesMap(playerMap);
    }

    private boolean testIfListIsValid(List<Integer> game) {
        Set<Integer> setNr = new HashSet<>();
        for(Integer nr : game){
            if(nr<0 || nr>15){
                return false;
            }
            setNr.add(nr);
        }
        return setNr.size() == game.size();
    }

    private PlayerResponse startGame(){
        return gameService.startGame(playerName);
    }
}
