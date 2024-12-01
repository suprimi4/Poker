package org.example.main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerHandTest {

    @Test
    void testIsRoyalFlash() {
        Board board = new Board("10HJH","2H5H","QHKHAH","KS","AS");
        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isRoyalFlash(list));
    }

    @Test
    void testIsStraightFlush() {
        Board board = new Board("8H9H","2H5H","10H10S9S","JH","QH");
        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isStraightFlush(list));
    }

    @Test
    void testIsFourOfAKind() {
        Board board = new Board("KHKS","2H5H","KC2C5C","KH","QH");
        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isFourOfAKind(list));
    }

    @Test
    void testIsFullHouse() {
        Board board = new Board("3C4H","2H5H","KCKHKS","QD","QH");
        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isFullHouse(list));
    }

    @Test
    void testIsFlush() {
        Board board = new Board("3C4H","2H5H","KCKHKS","QD","QH");
        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isFullHouse(list));
    }

    @Test
    void testIsStraight() {
        Board board1 = new Board("ASKH","2H5H","QSJC10D","QD","QH");
        Board board = new Board("AD2H","2H5H","4C5S6D","3D","QH");
        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isStraight(list));
    }



    @Test
    void testIsThreeOfAKind() {
        Board board = new Board("KHKS","2H5H","KC2C5C","QC","JH");

        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isThreeOfAKind(list));

    }

    @Test
    void testIsTwoPair() {
        Board board = new Board("KHKS","2H5H","10C10C5C","QC","JH");

        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isTwoPair(list));
    }

    @Test
    void testIsPair() {
        Board board = new Board("KHKS","2H5H","10C9C5C","QC","JH");

        List<Card> list = new ArrayList<>();
        list.addAll(board.getPlayerOne());
        list.addAll(board.getFlop());
        list.addAll(board.getTurn());
        list.addAll(board.getRiver());
        PlayerHand playerHand = new PlayerHand(list);
        System.out.println(playerHand.getHandCombination());
        assertTrue(playerHand.isPair(list));
    }
}