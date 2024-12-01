package org.example.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BestDealer implements Dealer {
    Deck deck;

    public BestDealer() {
        this.deck = new Deck();
    }

    @Override
    public Board dealCardsToPlayers() {
        List<Card> playerOneCards = new ArrayList<>();
        playerOneCards.add(deck.nextCards());
        playerOneCards.add(deck.nextCards());

        List<Card> playerTwoCards = new ArrayList<>();
        playerTwoCards.add(deck.nextCards());
        playerTwoCards.add(deck.nextCards());
        return new Board(playerOneCards, playerTwoCards, null, null, null);
    }

    @Override
    public Board dealFlop(Board board) {
        List<Card> flop = new ArrayList<>();
        flop.add(deck.nextCards());
        flop.add(deck.nextCards());
        flop.add(deck.nextCards());


        return new Board(board.getPlayerOne(), board.getPlayerTwo(), flop, null, null);
    }

    @Override
    public Board dealTurn(Board board) {
        List<Card> turn = new ArrayList<>();
        turn.add(deck.nextCards());
        return new Board(board.getPlayerOne(), board.getPlayerTwo(), board.getFlop(), turn, null);
    }

    @Override
    public Board dealRiver(Board board) {
        List<Card> river = new ArrayList<>();
        river.add(deck.nextCards());
        return new Board(board.getPlayerOne(), board.getPlayerTwo(), board.getFlop(), board.getTurn(), river);
    }

    @Override
    public PokerResult decideWinner(Board board) throws InvalidPokerBoardException {
        validateBoard(board);

        List<Card> playerOneCombintaion = new ArrayList<>(board.getPlayerOne());

        playerOneCombintaion.addAll(board.getFlop());
        playerOneCombintaion.addAll(board.getTurn());
        playerOneCombintaion.addAll(board.getRiver());

        List<Card> playerTwoCombination = new ArrayList<>(board.getPlayerTwo());

        playerTwoCombination.addAll(board.getFlop());
        playerTwoCombination.addAll(board.getTurn());
        playerTwoCombination.addAll(board.getRiver());

        PlayerHand playerOneSearch = new PlayerHand(playerOneCombintaion);
        PlayerHand playerTwoSearch = new PlayerHand(playerTwoCombination);

        int playerOneRank = playerOneSearch.getPokerCombination().getRank();
        int playerTwoRank = playerTwoSearch.getPokerCombination().getRank();

        if (playerOneRank > playerTwoRank) {
            return PokerResult.PLAYER_ONE_WIN;
        } else if (playerOneRank < playerTwoRank) {
            return PokerResult.PLAYER_TWO_WIN;
        } else {
            return compareHands(board, playerOneSearch, playerTwoSearch);
        }
    }

    private PokerResult compareHands(Board board, PlayerHand playerOneSearch, PlayerHand playerTwoSearch) {
        List<Card> pOneHandCombination = playerOneSearch.getHandCombination();
        List<Card> pTwoHandCombination = playerTwoSearch.getHandCombination();
        pOneHandCombination = playerOneSearch.sortCards(pOneHandCombination);
        pTwoHandCombination = playerTwoSearch.sortCards(pTwoHandCombination);

        switch (playerOneSearch.getPokerCombination()) {
            case ROYAL_FLUSH -> {
                return PokerResult.DRAW;
            }
            case STRAIGHT_FLUSH, FLUSH, STRAIGHT -> {
                return compareFlushOrStraight(pOneHandCombination, pTwoHandCombination);
            }
            case FULL_HOUSE -> {
                return compareFullHouse(playerOneSearch, playerTwoSearch);
            }
            default -> {
                return compareHighCard(board, playerOneSearch, playerTwoSearch);
            }

        }


    }

    private PokerResult compareFlushOrStraight(List<Card> pOneHandCombination, List<Card> pTwoHandCombination) {

        for (int i = pOneHandCombination.size() - 1; i >= 0; i--) {
            int rankOne = pOneHandCombination.get(i).getRank().getValue();
            int rankTwo = pTwoHandCombination.get(i).getRank().getValue();

            if (rankOne > rankTwo) {
                return PokerResult.PLAYER_ONE_WIN;
            }

            if (rankOne < rankTwo) {
                return PokerResult.PLAYER_TWO_WIN;
            }
        }

        return PokerResult.DRAW;
    }


    private PokerResult compareFullHouse(PlayerHand playerOneSearch, PlayerHand playerTwoSearch) {
        int playerOneThreeValue = playerOneSearch.getThreeValue();
        int playerTwoThreeValue = playerTwoSearch.getThreeValue();

        int pOnePairValue = playerOneSearch.getPairValue();
        int pTwoPairValue = playerTwoSearch.getPairValue();

        if (playerOneThreeValue > playerTwoThreeValue) {
            return PokerResult.PLAYER_ONE_WIN;
        }
        if (playerOneThreeValue < playerTwoThreeValue) {
            return PokerResult.PLAYER_TWO_WIN;
        }

        if (pOnePairValue > pTwoPairValue) {
            return PokerResult.PLAYER_ONE_WIN;
        }

        if (pOnePairValue < pTwoPairValue) {
            return PokerResult.PLAYER_TWO_WIN;
        }

        return PokerResult.DRAW;

    }

    private PokerResult compareHighCard(Board board, PlayerHand playerOneSearch, PlayerHand playerTwoSearch) {

        List<Card> playerOneHand = new ArrayList<>(board.getPlayerOne());
        List<Card> playerTwoHand = new ArrayList<>(board.getPlayerTwo());

        List<Card> boardCards = new ArrayList<>();
        boardCards.addAll(board.getFlop());
        boardCards.addAll(board.getTurn());
        boardCards.addAll(board.getRiver());


        playerOneHand.removeAll(playerOneSearch.getHandCombination());
        playerTwoHand.removeAll(playerTwoSearch.getHandCombination());

        List<Card> remainingBoardCards = new ArrayList<>(boardCards);
        remainingBoardCards.removeAll(playerOneSearch.getHandCombination());
        remainingBoardCards.removeAll(playerTwoSearch.getHandCombination());

        int maxBoardCard = remainingBoardCards.stream()
                .mapToInt(card -> card.getRank().getValue())
                .max()
                .orElse(0);

        int maxPlayerOne = playerOneHand.stream()
                .mapToInt(card -> card.getRank().getValue())
                .max()
                .orElse(0);

        int maxPlayerTwo = playerTwoHand.stream()
                .mapToInt(card -> card.getRank().getValue())
                .max()
                .orElse(0);


        if (maxBoardCard > maxPlayerOne && maxBoardCard > maxPlayerTwo) {
            return PokerResult.DRAW;
        }


        if (maxPlayerOne > maxPlayerTwo) {
            return PokerResult.PLAYER_ONE_WIN;
        }
        if (maxPlayerOne < maxPlayerTwo) {
            return PokerResult.PLAYER_TWO_WIN;
        }


        playerOneHand.sort((card1, card2) -> Integer.compare(card2.getRank().getValue(), card1.getRank().getValue()));
        playerTwoHand.sort((card1, card2) -> Integer.compare(card2.getRank().getValue(), card1.getRank().getValue()));

        for (int i = 0; i < Math.min(playerOneHand.size(), playerTwoHand.size()); i++) {
            int rankPlayerOne = playerOneHand.get(i).getRank().getValue();
            int rankPlayerTwo = playerTwoHand.get(i).getRank().getValue();

            if (rankPlayerOne > rankPlayerTwo) {
                return PokerResult.PLAYER_ONE_WIN;
            }
            if (rankPlayerOne < rankPlayerTwo) {
                return PokerResult.PLAYER_TWO_WIN;
            }
        }


        return PokerResult.DRAW;
    }


    private void validateBoard(Board board) throws InvalidPokerBoardException {
        if (board.getPlayerOne().size() != 2 || board.getPlayerTwo().size() != 2) {
            throw new InvalidPokerBoardException("Игроки должны иметь по 2 карты в руке.");
        }

        if (board.getFlop().size() != 3) {
            throw new InvalidPokerBoardException("Флоп должен содержать 3 карты.");
        }
        if (board.getTurn() == null) {
            throw new InvalidPokerBoardException("Терн должен быть на столе");
        }
        if (board.getRiver() == null) {
            throw new InvalidPokerBoardException("Ривер должен быть на столе");
        }

        List<Card> allCards = new ArrayList<>();
        allCards.addAll(board.getPlayerOne());
        allCards.addAll(board.getPlayerTwo());
        allCards.addAll(board.getFlop());
        allCards.addAll(board.getTurn());
        allCards.addAll(board.getRiver());

        Set<Card> uniqueCards = new HashSet<>(allCards);
        if (uniqueCards.size() != allCards.size()) {
            throw new InvalidPokerBoardException("Найдены дупликаты карт на столе.");
        }
    }
}
