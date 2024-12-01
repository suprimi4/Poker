package org.example.main;

public class PokerGame {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            BestDealer dealer = new BestDealer();
            Board board = dealer.dealCardsToPlayers();
            board = dealer.dealFlop(board);
            board = dealer.dealTurn(board);
            board = dealer.dealRiver(board);
            PokerResult result = dealer.decideWinner(board);
            System.out.println("Игрок 1: " + board.getPlayerOne());
            System.out.println("Игрок 2: " + board.getPlayerTwo());
            System.out.println("Флоп: " + board.getFlop());
            System.out.println("Терн: " + board.getTurn());
            System.out.println("Ривер: " + board.getRiver());


            switch (result) {
                case PLAYER_ONE_WIN:
                    System.out.println("1 игрок победил");
                    break;
                case PLAYER_TWO_WIN:
                    System.out.println("2 игрок победил");
                    break;
                case DRAW:
                    System.out.println("Ничья");
                    break;
                default:
                    System.out.println("Ошибка");
                    break;


            }
        }
    }


}