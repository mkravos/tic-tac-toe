public class Testcode {
    private void test() {
            int i, j;
            boolean errorConstructor, errorReset, errorGetPlayer1, errorGetPlayer2;
            boolean errorNextToMove, errorGetStatus, errorCheckForWin, errorMove;
            
            // set overall error indicator to false
            boolean error = false;
            
            // test constructor
            errorConstructor = false;
            TicTacToe obj = new TicTacToe();
            if (obj.player[0] != 'X') { System.out.println("Player 1 not set to X in constructor"); errorConstructor = true; }
            if (obj.player[1] != 'O') { System.out.println("Player 2 not set to O in constructor"); errorConstructor = true; }
            if (obj.numMoves != 0) { System.out.println("numMoves not set to 0 in constructor"); errorConstructor = true; }
            for (i=1; i<=9; i++) {
                if (board[(i-1)/3][(i-1)%3] != ' ') {
                    System.out.println("Constructor left position " + i + " non-blank");
                    errorConstructor = true;
                }
            }
            if (obj.status != Status.ONGOING) { 
                System.out.println("Constructor did not set status to ONGOING"); errorConstructor = true; }
            if (!errorConstructor) System.out.println("Constructor passed tests");
            else error = true;
            
            // test reset()
            errorReset = false;
            obj.reset();
            if (obj.player[0] != 'X') { System.out.println("Player 1 not set to X in reset()"); errorReset = true; }
            if (obj.player[1] != 'O') { System.out.println("Player 2 not set to O in reset()"); errorReset = true; }
            if (obj.numMoves != 0) { System.out.println("numMoves not set to 0 in reset()"); errorReset = true; }
            for (i=1; i<=9; i++) {
                if (board[(i-1)/3][(i-1)%3] != ' ') {
                    System.out.println("reset() left position " + i + " non-blank");
                    errorReset = true;
                }
            }
            if (obj.status != Status.ONGOING) { System.out.println("reset() did not set status to ONGOING"); errorReset = true; }
            if (!errorReset) System.out.println("reset() passed tests");
            else error = true;
            
            // test getPlayer1()
            errorGetPlayer1 = false;
            obj.player[0] = '^';
            if (obj.getPlayer1() != '^') { System.out.println("getPlayer1() is not working properly"); errorGetPlayer1 = true; }
            obj.player[0] = 'X';
            if (!errorGetPlayer1) System.out.println("getPlayer1() passed test");
            else error = true;
            
            // test getPlayer2()
            errorGetPlayer2 = false;
            obj.player[1] = '?';
            if (obj.getPlayer2() != '?') { System.out.println("getPlayer2() is not working properly"); errorGetPlayer2 = true; }
            obj.player[1] = 'O';
            if (!errorGetPlayer2) System.out.println("getPlayer2() passed test");
            else error = true;
            
            // test nextToMove()
            errorNextToMove = false;
            obj.player[0] = 'A';
            obj.player[1] = 'B';
            for (obj.numMoves=0; obj.numMoves<=9; obj.numMoves++) {
                if (obj.nextToMove() != (new char[]{'A','B'})[obj.numMoves%2]) errorNextToMove = true;
            }
            if (errorNextToMove) {
                System.out.println("nextToMove() failed tests");
                error = true;
            } else {
                System.out.println("nextToMove() passed tests");
            }
            
            // test getStatus()
            errorGetStatus = false;
            Status[] stats = { Status.ONGOING, Status.DRAW, Status.PLAYER_1_WIN, Status.PLAYER_2_WIN };
            for (i=0; i<4; i++) {
                obj.status = stats[i];
                if (obj.getStatus() != stats[i]) errorGetStatus = true;
            }
            if (errorGetStatus) {
                System.out.println("getStatus() failed tests");
                error = true;
            } else {
                System.out.println("getStatus() passed tests");
            }
            
            // test checkForWin(char)
            int[] pat = {0x1c0, 0x038, 0x007, 0x124, 0x092, 0x049, 0x111, 0x054};
            errorCheckForWin = false;
            boolean tempError = false;
            for (i=0; i<8; i++) {
                for (j=0; j<9; j++) {
                    obj.board[j/3][j%3] = (pat[i] & 1<<j) != 0 ? 'z' : ' ';
                }
                if (!obj.checkForWin('z')) { tempError = true; System.out.println(obj); }
            }
            if (tempError) {
                System.out.println("checkForWin(char) failed to detect a win");
                errorCheckForWin = true;
            }
            int pat2[] = {0x1b0, 0x145, 0x099, 0x0aa};
            tempError = false;
            for (i=0; i<4; i++) {
                for (j=0; j<9; j++) {
                    obj.board[j/3][j%3] = (pat2[i] & 1<<j) != 0 ? 'z' : ' ';
                }
                if (obj.checkForWin('z')) tempError = true;
            }
            if (tempError) {
                System.out.println("checkForWin(char) detected a false win");
                errorCheckForWin = true;
            }
            
            if (errorCheckForWin) {
                System.out.println("checkForWin(char) failed tests");
                error = true;
            } else {
                System.out.println("checkForWin(char) passed tests");
            }
            
            // test move(int)
            errorMove = false;
            setBoard(" ", 0, Status.ONGOING);
            if (obj.move(0)) {
                System.out.println("move(int) failed to detect invalid cell number 0");
                errorMove = true;
            }
            if (obj.move(10)) {
                System.out.println("move(int) failed to detect invalid cell number 10");
                errorMove = true;
            }
            obj.status = Status.PLAYER_1_WIN;
            if (obj.move(2)) {
                System.out.println("move(int) failed to detect status was not ONGOING");
                errorMove = true;
            }
            
            setBoard(" O ", 0, Status.ONGOING);
            if (obj.move(8)) {
                System.out.println("move(int) failed to detect occupied cell on board");
                errorMove = true;
            }
            
            setBoard(" ", 0, Status.ONGOING);
            obj.player[0] = 'A';
            obj.player[1] = 'B';
            String[] boardImage = {" ", "A ", "AB ", "ABA ", "ABAB ", "ABABA ",
                "ABABAB ", "ABABABA ", "ABABABB ", "ABABABBA ", "ABABABBAB" };
            for (i=1; i<=7; i++) {
                obj.setBoard(boardImage[i-1], i-1, Status.ONGOING);
                if (!obj.move(i)) {
                    System.out.println("move(int) failed to allow valid move");
                    errorMove = true;
                } else if (obj.numMoves != i) {
                    System.out.println("move(int) did not increment numMoves properly");
                    errorMove = true;
                }
                if (i < 7 && obj.getStatus() != Status.ONGOING) {
                    System.out.println("move(int) changed status incorrectly");
                    errorMove = true;
                }
                if (i == 7 && obj.getStatus() != Status.PLAYER_1_WIN) {
                    System.out.println("move(int) did not update status to win properly");
                    errorMove = true;
                }
            }
            obj.status = Status.ONGOING;
            obj.player[0] = 'B';
            obj.player[1] = 'A';
            obj.board[2][0] = 'B';
            for (i=8; i<=9; i++) {
                obj.setBoard(boardImage[i], i-1, Status.ONGOING);
                if (!obj.move(i)) {
                    System.out.println("move(int) failed to allow valid move");
                    errorMove = true;
                } else if (obj.numMoves != i) {
                    System.out.println("move(int) did not increment numMoves properly");
                    errorMove = true;
                }
                if (i < 9 && obj.getStatus() != Status.ONGOING) {
                    System.out.println("move(int) changed status incorrectly");
                    errorMove = true;
                }
                if (i == 9 && obj.getStatus() != Status.DRAW) {
                    System.out.println("move(int) did not update status to draw properly");
                    errorMove = true;
                }
            }
            if (errorMove) {
                System.out.println("move(int) failed tests");
                error = true;
            } else {
                System.out.println("move(int) passed tests");
            }
            System.out.println("Note: move(int) tests did not include test for PLAYER_2_WIN");
            // test toString()
            obj.setBoard("ABCDEFGHI", 9, Status.DRAW);
            System.out.println(obj);
            System.out.println("Your toString() method produced the results above.");
            System.out.println("It should look like this:");
            System.out.println(" A | B | C \n D | E | F \n G | H | I ");
            System.out.println("You have to decide if toString() passed this test.");
            
            // test displayInstructions()
            obj.displayInstructions();
            System.out.println("Your displayInstructions() method produced the reults above.");
            System.out.println("They should look like this:");
            System.out.println("This is the game of Tic Tac Toe.\n"
                + "X moves first. Each player chooses their\n"
                + "move by selecting the cell they want to place\n"
                + "their mark in. The cells are numbered as follows:\n"
                + " 1 2 3\n 4 5 6\n 7 8 9\n");
            System.out.println("You have to decide if displayInstructions() passed this test.");
            
            // display overall results
            if (error) System.out.println("TicTacToe class has failed testing");
            else System.out.println("TicTacToe class has passed testing");
    }
        
    private void setBoard(String b, int moves, Status stat) {
        for (int i=0; i<b.length(); i++) board[i/3][i%3] = b.charAt(i);
        for (int i=b.length(); i<9; i++) board[i/3][i%3] = ' ';
        numMoves = moves;
        status = stat;
    }
    public static void main(String[] args) {
        (new TicTacToe()).test();
    }
}