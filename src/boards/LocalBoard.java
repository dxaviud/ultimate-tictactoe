package src.boards;

import ultimatetictactoe.marks.*;

public class LocalBoard {
    
    private Mark[][] boxes;
    public final int length = 3;
    public final int width = 3;
    private String winner;
    private boolean boardFinished;
    private String isCurrentBoard;

    public LocalBoard() {
        winner = " ";
        boardFinished = false;
        boxes = new Mark[length][width];
        for (int row = 0; row < boxes.length; row++) {
            for (int col = 0; col < boxes[row].length; col++) {
                boxes[row][col] = new NoMark(row, col);
            }
        }
        isCurrentBoard = " ";
    }

    public String getIsCurrentBoard() {
        return isCurrentBoard;
    }

    public void setIsCurrentBoard() {
        isCurrentBoard = "^";
    }

    public void setIsNotCurrentBoard() {
        isCurrentBoard = " ";
    }

    public boolean getBoardFinished() {
        return boardFinished;
    }
    
    public void updateBoard() {
        if (boardFinished) {
            return;
        }
        //check all rows
        for (int row = 0; row < length; row++) {
            if (!(boxes[row][0] instanceof NoMark) && 
            boxes[row][0].equals(boxes[row][1]) &&
            boxes[row][1].equals(boxes[row][2])) {
                winner = boxes[row][0].toString();
                boardFinished = true;
                return;
            }
        }

        //check all cols
        for (int col = 0; col < width; col++) {
            if (!(boxes[0][col] instanceof NoMark) && 
            boxes[0][col].equals(boxes[1][col]) &&
            boxes[1][col].equals(boxes[2][col])) {
                winner = boxes[0][col].toString();
                boardFinished = true;
                return;
            }
        }

        //check diagonals
        if (!(boxes[0][0] instanceof NoMark) &&
        boxes[0][0].equals(boxes[1][1]) && 
        boxes[1][1].equals(boxes[2][2])) {
            winner = boxes[0][0].toString();
            boardFinished = true;
            return;
        }
        if (!(boxes[0][2] instanceof NoMark) &&
        boxes[0][2].equals(boxes[1][1]) && 
        boxes[1][1].equals(boxes[2][0])) {
            winner = boxes[0][2].toString();
            boardFinished = true;
            return;
        }

        //board is full but no three-in-a-row (tie)
        if (boardIsFull()) {
            winner = " ";
            boardFinished = true;
            return;
        }
        boardFinished = false;
    }

    public boolean boardIsFull() {
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                if (boxes[row][col] instanceof NoMark) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display() {
        System.out.println("_______");
        for (int row = 0; row < boxes.length; row++) {
            System.out.print("|");
            for (int col = 0; col < boxes[row].length; col++) {
                System.out.print(boxes[row][col] + "|");
            }
            System.out.println();
            System.out.println("-------");
        }
    }

    public Mark getBox(int rowIndex, int colIndex) {
        return boxes[rowIndex][colIndex];
    }
    
    public void setBox(int rowIndex, int colIndex, Mark newMark) {
        boxes[rowIndex][colIndex] = newMark;
    }

    public String getWinner() {
        return winner;
    }
}
