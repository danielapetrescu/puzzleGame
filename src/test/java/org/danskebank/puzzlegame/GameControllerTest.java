package org.danskebank.puzzlegame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.danskebank.puzzlegame.controller.GameController;
import org.danskebank.puzzlegame.dto.PlayerResponse;
import org.danskebank.puzzlegame.entities.Move;
import org.danskebank.puzzlegame.service.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;

    List<Integer> list = new ArrayList<>();

    private PlayerResponse setupPlayer1Response(){
        for (int i = 0; i <= 15; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.setName("player1");
        playerResponse.setGame(list);
        return playerResponse;
    }
    @Test
    @DisplayName("Test should pass if starting a game returns a valid game")
    public void givenPlayer_whenStartGame_thenReturnJsonArray()
            throws Exception {

        PlayerResponse playerResponse = setupPlayer1Response();

        Mockito.when(gameService.startGame("player1")).thenReturn(playerResponse);

        MvcResult result = mvc.perform(post("/startGame?name=player1")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        PlayerResponse returnedPlayerResponse = objectMapper.readValue(content, new TypeReference<PlayerResponse>() {});

        boolean validList = testIfListIsValid(returnedPlayerResponse.getGame());
        assertTrue(validList);
    }

    @Test
    @DisplayName("Test should pass if left move returns a valid game")
    public void givenPlayer_whenMoveLeft_thenReturnJsonArray()
            throws Exception {
        testMove(Move.LEFT);
    }

    @Test
    @DisplayName("Test should pass if right move returns a valid game")
    public void givenPlayer_whenMoveRight_thenReturnJsonArray()
            throws Exception {
        testMove(Move.RIGHT);
    }

    @Test
    @DisplayName("Test should pass if up move returns a valid game")
    public void givenPlayer_whenMoveUp_thenReturnJsonArray()
            throws Exception {
        testMove(Move.UP);
    }

    @Test
    @DisplayName("Test should pass if down move returns a valid game")
    public void givenPlayer_whenMoveDown_thenReturnJsonArray()
            throws Exception {
        testMove(Move.DOWN);
    }

    private void testMove(Move direction) throws Exception {

        PlayerResponse playerResponse = setupPlayer1Response();
        Mockito.when(gameService.moveTile("player1", direction)).thenReturn(playerResponse);

        MvcResult result = mvc.perform(get("/" + direction.name().toLowerCase() +"?playerName=player1")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        PlayerResponse returnedPlayerResponse = objectMapper.readValue(content, new TypeReference<PlayerResponse>() {});

        boolean validList = testIfListIsValid(returnedPlayerResponse.getGame());
        assertTrue(validList);
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
}

