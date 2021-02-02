package ultimatetictactoe.boards;

public class GlobalBoard {

    private LocalBoard[][] localBoards;
    public final int length = 3;
    public final int width = 3;
    private String winner;

    public GlobalBoard() {
        localBoards = new LocalBoard[length][width];
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                localBoards[row][col] = new LocalBoard();
            }
        }
        winner = "Neither";

    }

    public void updateLocalBoards() {
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                localBoards[row][col].updateBoard();
            }
        }
    }
    public boolean gameOver() {
        //check all rows
        for (int row = 0; row < length; row++) {
            if (!localBoards[row][0].getWinner().equals(" ") &&
            localBoards[row][0].getWinner().equals(localBoards[row][1].getWinner()) &&
            localBoards[row][1].getWinner().equals(localBoards[row][2].getWinner())) {
                winner = localBoards[row][0].getWinner();
                return true;
            }
        }

        //check all cols
        for (int col = 0; col < width; col++) {
            if (!localBoards[0][col].getWinner().equals(" ") &&
            localBoards[0][col].getWinner().equals(localBoards[1][col].getWinner()) &&
            localBoards[1][col].getWinner().equals(localBoards[2][col].getWinner())) {
                winner = localBoards[0][col].getWinner();
                return true;
            }
        }

        //check diagonals
        if (!localBoards[0][0].getWinner().equals(" ") &&
        localBoards[0][0].getWinner().equals(localBoards[1][1].getWinner()) && 
        localBoards[1][1].getWinner().equals(localBoards[2][2].getWinner())) {
            winner = localBoards[0][0].getWinner();
            return true;
        }
        if (!localBoards[0][2].getWinner().equals(" ") &&
        localBoards[0][2].getWinner().equals(localBoards[1][1].getWinner()) && 
        localBoards[1][1].getWinner().equals(localBoards[2][0].getWinner())) {
            winner = localBoards[0][2].getWinner();
            return true;
        }

        //board is full but no three-in-a-row (tie)
        if (tie() || boardIsFull()) {
            winner = "Neither";
            return true;
        }
        return false;
    }

    private boolean boardIsFull() {
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                if (!localBoards[row][col].boardIsFull()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean tie() {
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                if (!localBoards[row][col].getBoardFinished()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display() {
        int globalRow = 0;
        for (LocalBoard[] row : localBoards) {

            System.out.println("_____________  _____________  _____________");
            
            for (int localRow = 0; localRow < 3; localRow++) {
 
                for (LocalBoard board : row) {
 
                    for (int localCol = 0; localCol < 3; localCol++) {
                        System.out.print("|");
                        System.out.print(" " + board.getBox(localRow, localCol) + " ");
                    }
                    System.out.print("|  ");
                }
                
                System.out.println();
                System.out.println("-------------  -------------  -------------");

            }
            System.out.println("      " + localBoards[globalRow][0].getIsCurrentBoard() + "              " + localBoards[globalRow][1].getIsCurrentBoard() + "              " + localBoards[globalRow][2].getIsCurrentBoard());
            globalRow++;
        }
        System.out.println("Global board representation:");
        System.out.println("_____________");
        System.out.println("| "+ localBoards[0][0].getWinner() +" | "+ localBoards[0][1].getWinner() +" | "+ localBoards[0][2].getWinner() +" |");
        System.out.println("-------------");
        System.out.println("| "+ localBoards[1][0].getWinner() +" | "+ localBoards[1][1].getWinner() +" | "+ localBoards[1][2].getWinner() +" |");
        System.out.println("-------------");
        System.out.println("| "+ localBoards[2][0].getWinner() +" | "+ localBoards[2][1].getWinner() +" | "+ localBoards[2][2].getWinner() +" |");
        System.out.println("-------------");
    }

    public String getWinner() {
        return winner;
    }
    
    public LocalBoard getLocalBoard(int rowInd, int colInd) {
        return localBoards[rowInd][colInd];
    }
}