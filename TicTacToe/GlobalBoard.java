

public class globalBoard {

    private localBoard[][] localBoards;
    public final int length = 3;
    public final int width = 3;
    private String winner;

    public globalBoard() {
        localBoards = new localBoard[length][width];
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                localBoards[row][col] = new localBoard();
            }
        }
        winner = "Neither";
    }

    public boolean gameOver() {
        //check all rows
        for (int row = 0; row < length; row++) {
            if (!localBoards[row][0].getWinner().equals("Neither") &&
            localBoards[row][0].getWinner().equals(localBoards[row][1].getWinner()) &&
            localBoards[row][1].getWinner().equals(localBoards[row][2].getWinner())) {
                winner = localBoards[row][0].getWinner();
                return true;
            }
        }

        //check all cols
        for (int col = 0; col < width; col++) {
            if (!localBoards[0][col].getWinner().equals("Neither") &&
            localBoards[0][col].getWinner().equals(localBoards[1][col].getWinner()) &&
            localBoards[1][col].getWinner().equals(localBoards[2][col].getWinner())) {
                winner = localBoards[0][col].getWinner();
                return true;
            }
        }

        //check diagonals
        if (!localBoards[0][0].getWinner().equals("Neither") &&
        localBoards[0][0].getWinner().equals(localBoards[1][1].getWinner()) && 
        localBoards[1][1].getWinner().equals(localBoards[2][2].getWinner())) {
            winner = localBoards[0][0].getWinner();
            return true;
        }
        if (!localBoards[0][2].getWinner().equals("Neither") &&
        localBoards[0][2].getWinner().equals(localBoards[1][1].getWinner()) && 
        localBoards[1][1].getWinner().equals(localBoards[2][0].getWinner())) {
            winner = localBoards[0][2].getWinner();
            return true;
        }

        //board is full but no three-in-a-row (tie)
        if (boardIsFull()) {
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

    public void display() {
    
        for (localBoard[] row : localBoards) {

            System.out.println("_____________  _____________  _____________");
   
            for (int smallRow = 0; smallRow < 3; smallRow++) {
 
                for (localBoard board : row) {
 
                    for (int smallCol = 0; smallCol < 3; smallCol++) {
                        System.out.print("|");
                        System.out.print(" " + board.getBox(smallRow, smallCol) + " ");
                    }
                    System.out.print("|  ");
                }
                System.out.println();
                System.out.println("-------------  -------------  -------------");

            }
            System.out.println();
        }
    }

    public String getWinner() {
        return winner;
    }
    
    public localBoard getLocalBoard(int rowInd, int colInd) {
        return localBoards[rowInd][colInd];
    }
}