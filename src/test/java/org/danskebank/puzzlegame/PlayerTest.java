package org.danskebank.puzzlegame;

import org.danskebank.puzzlegame.entities.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    @DisplayName("Test should pass when getting name works correctly")
    public void test_getName() {
        String playerName1 = "John";
        String playerName2 = "Dany";

        Player player= new Player(playerName1, new ArrayList<>());
        player.setName(playerName2);

        assertEquals(playerName2, player.getName());
    }
}
