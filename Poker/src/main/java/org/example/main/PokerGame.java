package org.example.main;

public class PokerGame {

    public static void main(String[] args) {

            BestDealer dealer = new BestDealer();
            Board board;

            board = new Board("2C2H", "2C2H", "2C2H2C", "2C", "2H");

            PokerResult result = dealer.decideWinner(board);
            System.out.println("Игрок 1: " + board.getPlayerOne());
            System.out.println("Игрок 2: " + board.getPlayerTwo());
            System.out.println("Флоп: " + board.getFlop());
            System.out.println("Терн: " + board.getTurn());
            System.out.println("Ривер: " + board.getRiver());



            switch (result) {
                case PLAYER_ONE_WIN:
                    System.out.println("1 игрок победил");
                    System.out.println();
                    System.out.println();
                    break;
                case PLAYER_TWO_WIN:
                    System.out.println("2 игрок победил");
                    System.out.println();
                    System.out.println();
                    break;
                case DRAW:
                    System.out.println("Ничья");
                    System.out.println();
                    System.out.println();
                    break;
                default:
                    System.out.println("Ошибка");
                    System.out.println();
                    System.out.println();
                    break;
            }
        }


}