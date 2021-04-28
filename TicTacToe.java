/*
    TicTacToe.java

    Implementation of TicTacToe class for PlayTTT.java.
*/

public class TicTacToe {
    public enum Status { ONGOING, DRAW, PLAYER_1_WIN, PLAYER_2_WIN };
    private char[][] board = new char[3][3];
    private char[] player = {'X', 'O'};
    private int numMoves = 0;
    private Status status;

    private boolean checkForWin(char c) {
        // horizontal win
        if(board[0][0] == c && board[0][1] == c && board[0][2] == c) {
            return true;
        }
        else if(board[1][0] == c && board[1][1] == c && board[1][2] == c) {
            return true;
        }
        else if(board[2][0] == c && board[2][1] == c && board[2][2] == c) {
            return true;
        }
        // vertical win
        else if(board[0][0] == c && board[1][0] == c && board[2][0] == c) {
            return true;
        }
        else if(board[0][1] == c && board[1][1] == c && board[2][1] == c) {
            return true;
        }
        else if(board[0][2] == c && board[1][2] == c && board[2][2] == c) {
            return true;
        } 
        // diagonal win
        else if(board[0][0] == c && board[1][1] == c && board[2][2] == c) {
            return true;
        } 
        else if(board[0][2] == c && board[1][1] == c && board[2][0] == c) {
            return true;
        } else {
            return false;
        }  
    }

    public TicTacToe() {
        reset();
    }

    public void reset() {
        player[0] = 'X';
        player[1] = 'O';
        numMoves = 0;
        status = status.ONGOING;
        for(int r=0; r<=board.length-1; r++) {
            for(int c=0; c<=board[r].length-1; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public Status getStatus() {
        return status;
    }
    public char getPlayer1() {
        return player[0];
    }
    public char getPlayer2() {
        return player[1];
    }

    public char nextToMove() {
        if(numMoves%2==0) {
            return getPlayer1();
        } else {
            return getPlayer2();
        }
    }

    public void displayInstructions() {
        /*
            This is the game of Tic Tac Toe.
            X moves first. Each player chooses their
            move by selecting the cell they want to place
            their mark in. The cells are numbered as follows:
              1  2  3
              4  5  6
              7  8  9
        */
        System.out.print("This is the game of Tic Tac Toe.\nX moves first. Each player chooses their\nmove by selecting the cell they want to place\ntheir mark in. The cells are numbered as follows:\n  1  2  3\n  4  5  6\n  7  8  9\n");
    }

    public String toString() {
        StringBuilder str = new StringBuilder("");
        for(int r=0; r<board.length; r++) {
            for(int c=0; c<board[r].length; c++) {
                str.append(" " + board[r][c] + " ");
                if(c<board[r].length-1) str.append("|");
            }
            str.append('\n');
            if(r<board.length-1) {
                str.append("-----------\n");
            }
        }
        return str.toString();
    }
    
    public boolean move(int cell) {
        char cellOnBoard = ' ';
        if(cell>=1 && cell<=9) {
            if(cell==1) cellOnBoard = board[0][0];
            if(cell==2) cellOnBoard = board[0][1];
            if(cell==3) cellOnBoard = board[0][2];
            if(cell==4) cellOnBoard = board[1][0];
            if(cell==5) cellOnBoard = board[1][1];
            if(cell==6) cellOnBoard = board[1][2];
            if(cell==7) cellOnBoard = board[2][0];
            if(cell==8) cellOnBoard = board[2][1];
            if(cell==9) cellOnBoard = board[2][2];

            if(status == status.ONGOING) {
                if(cellOnBoard==' ') {
                    if(cell==1) board[0][0] = nextToMove();
                    if(cell==2) board[0][1] = nextToMove();
                    if(cell==3) board[0][2] = nextToMove();
                    if(cell==4) board[1][0] = nextToMove();
                    if(cell==5) board[1][1] = nextToMove();
                    if(cell==6) board[1][2] = nextToMove();
                    if(cell==7) board[2][0] = nextToMove();
                    if(cell==8) board[2][1] = nextToMove();
                    if(cell==9) board[2][2] = nextToMove();
                    numMoves++;
                }
                if(checkForWin(getPlayer1())) status=status.PLAYER_1_WIN;
                if(checkForWin(getPlayer2())) status=status.PLAYER_2_WIN;
                if(!checkForWin(getPlayer1()) || !checkForWin(getPlayer2())) {
                    if(numMoves==9) {
                        status=status.DRAW;
                    }
                }
                return true;
            }
        }
        return false;
    }

}