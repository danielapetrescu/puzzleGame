package org.danskebank.puzzlegame;

import org.danskebank.puzzlegame.entities.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerResponseTest {

    @Test
    @DisplayName("Test should pass when getting name works correctly")
    public void test_getName() {
        String playerName = "John";
        Player playerResponse = new Player(playerName, new ArrayList<>());

        assertEquals(playerName, playerResponse.getName());
    }

}
