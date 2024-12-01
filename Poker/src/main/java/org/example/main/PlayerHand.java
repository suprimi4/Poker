package org.example.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerHand {
    private final List<Card> handAndTable;

    private List<Card> handCombination;

    private int pairValue;
    private int threeValue;

    public PokerCombinations getPokerCombination() {
        return pokerCombination;
    }

    public List<Card> getHandAndTable() {
        return handAndTable;
    }

    private final PokerCombinations pokerCombination;


    public List<Card> getHandCombination() {
        return handCombination;
    }

    public int getPairValue() {
        return pairValue;
    }

    public int getThreeValue() {
        return threeValue;
    }

    public PlayerHand(List<Card> handAndTable) {
        List<Card> copyList = new ArrayList<>(handAndTable);
        this.handAndTable = copyList;
        this.pokerCombination = determineCombination();
    }

    private PokerCombinations determineCombination() {

        if (isRoyalFlash(handAndTable)) {
            return PokerCombinations.ROYAL_FLUSH;
        } else if (isStraightFlush(handAndTable)) {
            return PokerCombinations.STRAIGHT_FLUSH;
        } else if (isFourOfAKind(handAndTable)) {
            return PokerCombinations.FOUR_OF_A_KIND;
        } else if (isFullHouse(handAndTable)) {
            return PokerCombinations.FULL_HOUSE;
        } else if (isFlush(handAndTable)) {
            return PokerCombinations.FLUSH;
        } else if (isStraight(handAndTable)) {
            return PokerCombinations.STRAIGHT;
        } else if (isThreeOfAKind(handAndTable)) {
            return PokerCombinations.THREE_OF_A_KIND;
        } else if (isTwoPair(handAndTable)) {
            return PokerCombinations.TWO_PAIRS;
        } else if (isPair(handAndTable)) {
            return PokerCombinations.PAIR;
        } else {
            return PokerCombinations.HIGH_CARD;
        }
    }

    private boolean isRoyalFlash(List<Card> cardsToCheck) {
        handCombination = new ArrayList<>();
        List<Card> suitedCards = getSuitedCards(cardsToCheck);
        suitedCards = trimToFive(suitedCards);

        if (suitedCards.size() < 5) {
            return false;
        }
        Suit suit = suitedCards.get(0).getSuit();
        List<Card> royalRanks = List.of(new Card(Rank.TEN, suit),
                new Card(Rank.JACK, suit),
                new Card(Rank.QUEEN, suit),
                new Card(Rank.KING, suit),
                new Card(Rank.ACE, suit));

        if (suitedCards.containsAll(royalRanks)) {
            handCombination = new ArrayList<>(royalRanks);
            return true;
        } else {
            return false;
        }

    }

    private List<Card> trimToFive(List<Card> cards) {
        cards = sortCards(cards);
        for (int i = 0; i < cards.size() - 5; i++) {
            cards.remove(0);
        }
        return cards;
    }

    private List<Card> getSuitedCards(List<Card> allCards) {
        Map<String, List<Card>> suitMap = new HashMap<>();

        for (Card card : allCards) {
            String suit = card.getSuit().getSymbol();
            suitMap.putIfAbsent(suit, new ArrayList<>());
            suitMap.get(suit).add(card);
        }

        for (List<Card> suitedCards : suitMap.values()) {
            suitedCards = sortCards(suitedCards);

            if (suitedCards.size() >= 5) {
                return suitedCards;
            }

        }
        return new ArrayList<>();
    }


    private boolean isStraightFlush(List<Card> cardsToCheck) {

        List<Card> cards = getSuitedCards(cardsToCheck);
        if (cards.size() < 5) {
            return false;
        }

        cardsToCheck = sortCards(cards);
        return isStraight(cardsToCheck);
    }


    private boolean isFourOfAKind(List<Card> cardsToCheck) {
        handCombination = new ArrayList<>();
        Map<Integer, Integer> rankCount = getRankCount(cardsToCheck);

        for (Map.Entry<Integer, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 4) {
                handCombination = getCardsCombination(entry.getKey());
                return true;
            }
        }
        return false;
    }


    private boolean isFullHouse(List<Card> cardsToCheck) {
        handCombination = new ArrayList<>();
        Map<Integer, Integer> rankCount = getRankCount(cardsToCheck);

        boolean isPair = false;
        boolean isThreeOfAKind = false;

        for (Map.Entry<Integer, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 3) {
                isThreeOfAKind = true;
                threeValue = entry.getKey();

            }
            if (entry.getValue() == 2) {

                if (isPair && pairValue < entry.getKey()) {
                    pairValue = entry.getKey();
                }
                if (!isPair) {
                    pairValue = entry.getKey();
                }
                isPair = true;

            }
        }
        if (isPair) {
            handCombination.addAll(getCardsCombination(pairValue));
        }
        if (isThreeOfAKind) {
            handCombination.addAll(getCardsCombination(threeValue));
        }
        return isPair && isThreeOfAKind;
    }


    private boolean isFlush(List<Card> cardsToCheck) {
        handCombination = new ArrayList<>();
        handCombination = getSuitedCards(cardsToCheck);
        handCombination = sortCards(trimToFive(handCombination));

        return handCombination.size() == 5;
    }

    private boolean isStraight(List<Card> cardsToCheck) {

        if (isAceLowStraight(cardsToCheck)) {
            return true;
        } else {
            handCombination = new ArrayList<>();
        }

        List<Card> currentCombination = new ArrayList<>();

        cardsToCheck = sortCards(cardsToCheck);
        for (int i = 0; i < cardsToCheck.size() - 1; i++) {
            if ((cardsToCheck.get(i + 1).getRank().getValue() - cardsToCheck.get(i).getRank().getValue() == 1)) {
                currentCombination.add(cardsToCheck.get(i));
                if (cardsToCheck.size() - 2 == i) {
                    currentCombination.add(cardsToCheck.get(i + 1));
                    handCombination = new ArrayList<>(currentCombination);
                }
            } else {
                currentCombination.add(cardsToCheck.get(i));
                if (currentCombination.size() >= 5) {
                    handCombination = new ArrayList<>(currentCombination);
                }
                currentCombination = new ArrayList<>();
            }
        }

        if (handCombination.size() > 5) {
            trimToFive(handCombination);
        }

        return handCombination.size() == 5;
    }

    private boolean isAceLowStraight(List<Card> cardsToCheck) {
        List<Rank> aceLowStaightRanks = List.of(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE);
        handCombination = new ArrayList<>();
        for (Rank rank : aceLowStaightRanks) {
            for (Card card : cardsToCheck) {
                if (card.getRank() == rank) {
                    handCombination.add(card);
                    break;
                }
            }
        }


        return handCombination.size() == 5;
    }

    private boolean isThreeOfAKind(List<Card> cardsToCheck) {
        Map<Integer, Integer> rankCount = getRankCount(cardsToCheck);
        handCombination = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 3) {
                handCombination = getCardsCombination(entry.getKey());
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair(List<Card> cardsToCheck) {
        handCombination = new ArrayList<>();
        Map<Integer, Integer> rankCount = getRankCount(cardsToCheck);

        int pairCounter = 0;
        for (Map.Entry<Integer, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 2) {
                handCombination.addAll(getCardsCombination(entry.getKey()));
                pairCounter++;
            }
        }

        return pairCounter == 2;
    }

    private boolean isPair(List<Card> cardsToCheck) {

        handCombination = new ArrayList<>();
        Map<Integer, Integer> rankCount = getRankCount(cardsToCheck);
        for (Map.Entry<Integer, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 2) {
                handCombination.addAll(getCardsCombination(entry.getKey()));
                return true;
            }
        }
        return false;
    }


    public List<Card> getCardsCombination(int rankValue) {
        List<Card> cardsCombination = new ArrayList<>();
        for (Card card : handAndTable) {
            if (card.getRank().getValue() == rankValue) {
                cardsCombination.add(card);
            }
        }

        return cardsCombination;

    }

    private Map<Integer, Integer> getRankCount(List<Card> cardsToCheck) {
        Map<Integer, Integer> rankCount = new HashMap<>();
        for (Card card : cardsToCheck) {
            int rankValue = card.getRank().getValue();
            rankCount.put(rankValue, rankCount.getOrDefault(rankValue, 0) + 1);
        }
        return rankCount;
    }


    public List<Card> sortCards(List<Card> cards) {

        List<Card> copyOfHand = new ArrayList<>(cards);
        for (int i = 0; i < copyOfHand.size(); i++) {
            for (int j = 0; j < copyOfHand.size() - 1 - i; j++) {
                if (copyOfHand.get(j).getRank().getValue() > copyOfHand.get(j + 1).getRank().getValue()) {
                    Card temp = copyOfHand.get(j);
                    copyOfHand.set(j, copyOfHand.get(j + 1));
                    copyOfHand.set(j + 1, temp);
                }
            }
        }
        cards = new ArrayList<>(copyOfHand);

        return cards;
    }


}
