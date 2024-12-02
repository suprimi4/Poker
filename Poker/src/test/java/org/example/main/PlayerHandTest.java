package org.example.main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerHandTest {

    @Test
    void testIsRoyalFlash() {
        List<Card> list = getCombinationList("10HJH","2H5H","QHKHAH","KS","AS");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isRoyalFlash(list));
    }

    @Test
    void testIsStraightFlush() {
        List<Card> list = getCombinationList("8H9H","2H5H","10H10S9S","JH","QH");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isStraightFlush(list));
    }

    @Test
    void testIsFourOfAKind() {
        List<Card> list = getCombinationList("KHKS","2H5H","KC2C5C","KH","QH");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isFourOfAKind(list));
    }

    @Test
    void testIsFullHouse() {
        List<Card> list = getCombinationList("3C4H","2H5H","KCKHKS","QD","QH");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isFullHouse(list));
    }

    @Test
    void testIsFlush() {
        List<Card> list = getCombinationList("3S4S", "5C6H", "7S8S9S", "10S", "AS");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isFlush(list));
    }

    @Test
    void testIsStraight() {
        List<Card> list = getCombinationList("ASKH","2H5H","QSJC10D","QD","QH");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isStraight(list));
    }



    @Test
    void testIsThreeOfAKind() {
        List<Card> list = getCombinationList("KHKS","2H5H","KC2C5C","QC","JH");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isThreeOfAKind(list));

    }

    @Test
    void testIsTwoPair() {
        List<Card> list = getCombinationList("KHKS","2H5H","10C10C5C","QC","JH");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isTwoPair(list));
    }

    @Test
    void testIsPair() {
        List<Card> list = getCombinationList("KHKS","2H5H","10C9C5C","QC","JH");
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isPair(list));
    }

    private  List<Card> getCombinationList( String playerOne, String playerTwo, String flop, String turn, String river) {
        Board board = new Board(playerOne,playerTwo,flop,turn,river);
        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        return list;
    }
}