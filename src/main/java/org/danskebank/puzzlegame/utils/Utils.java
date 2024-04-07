package org.danskebank.puzzlegame.utils;

import org.danskebank.puzzlegame.entities.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    private static List<Integer> gameSolved = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));

    private static List<Integer> listWhenDownAndRightMoveIsImposible = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));

    private static List<Integer> listWhenUpAndLeftMoveIsImposible = new ArrayList(Arrays.asList(0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1));

    private static List<Integer> listWhenAllMoveArePosible = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 0, 7, 8, 9, 10, 11, 12, 13, 14, 15, 6));

    private static List<Integer> indexesWhenLeftMoveIsImpossible = new ArrayList(Arrays.asList(0, 4, 8, 12));

    private static List<Integer> indexesWhenRightMoveIsImpossible = new ArrayList(Arrays.asList(3, 7, 11, 15));

    private static List<Integer> indexesWhenUpMoveIsImpossible = new ArrayList(Arrays.asList(0, 1, 2, 3));

    private static List<Integer> indexesWhenDownMoveIsImpossible = new ArrayList(Arrays.asList(12, 13, 14, 15));
    private static List<Integer> indexesWhenAllMoveArePossible = new ArrayList(Arrays.asList(5, 6, 9, 10));

    public static List<Integer> getGameSolved() {
        return gameSolved;
    }

    public static List<Integer> getListWhenDownAndRightMoveIsImposible() {
        return listWhenDownAndRightMoveIsImposible;
    }

    public static List<Integer> getListWhenUpAndLeftMoveIsImposible() {
        return listWhenUpAndLeftMoveIsImposible;
    }

    public static List<Integer> getListWhenAllMoveArePosible() {
        return listWhenAllMoveArePosible;
    }

    public static boolean isRightMovePossible(Integer index){
        if(!indexesWhenRightMoveIsImpossible.contains(index)){
            return true;
        } else {
            return false;
        }
    }
    public static boolean isLeftMovePossible(Integer index){
        if(!indexesWhenLeftMoveIsImpossible.contains(index)){
            return true;
        } else {
            return false;
        }
    }
    public static boolean isUpMovePossible(Integer index){
        if(!indexesWhenUpMoveIsImpossible.contains(index)){
            return true;
        } else {
            return false;
        }
    }
    public static boolean isDownMovePossible(Integer index){
        if(!indexesWhenDownMoveIsImpossible.contains(index)){
            return true;
        } else {
            return false;
        }
    }

    public static void setGameStatus(List<Integer> game, Player player){
        if(game.equals(gameSolved)){
            player.setGameSolved(true);
        }
    }

}
