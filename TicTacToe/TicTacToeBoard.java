

import Marks.*;

public class TicTacToeBoard {
    
    private Mark[][] boxes;
    public final int length = 3;
    public final int width = 3;

    public TicTacToeBoard() {
        boxes = new Mark[3][3];
        for (int row = 0; row < boxes.length; row++) {
            for (int col = 0; col < boxes[row].length; col++) {
                boxes[row][col] = new NoMark();
            }
        }
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
}