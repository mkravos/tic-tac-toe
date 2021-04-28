/*
    PlayTTT.java

    Code to play Tic Tac Toe.
*/

public class PlayTTT {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Keyboard kbd = new Keyboard();
        boolean continuePlaying = true;
        String playagain = "";
        int move = 0;
        
        while(continuePlaying) {
            game.reset();
            game.displayInstructions();
            System.out.println("\n" + game.toString());
            while(game.getStatus()==TicTacToe.Status.ONGOING) {
                move = kbd.getInt("Enter move for player " + game.nextToMove() + ": ", 1, 9);
                game.move(move);
                System.out.println(game.toString());
            }
            
            if(game.getStatus()==TicTacToe.Status.PLAYER_1_WIN) {
                System.out.println("Player X has won.");
            } else if(game.getStatus()==TicTacToe.Status.PLAYER_2_WIN) {
                System.out.println("Player O has won.");
            } else if(game.getStatus()==TicTacToe.Status.DRAW) {
                System.out.println("The game is a draw.");
            }

            playagain = "" + kbd.getChar("Play again (Y/N)? ", "YyNn");
            if(playagain.equalsIgnoreCase("Y")) {
                continuePlaying = true;
            } else if(playagain.equalsIgnoreCase("N")) {
                continuePlaying = false;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}