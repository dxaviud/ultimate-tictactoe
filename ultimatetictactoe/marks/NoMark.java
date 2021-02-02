package ultimatetictactoe.marks;

import java.awt.Image;

public class NoMark extends Mark {
    private Image image;

    public NoMark(int row, int col) {
        rowIndex = row;
        colIndex = col;
        // set the image
        // super.setImage();
    }

    public Image getImage() {
        return super.getImage();
    }

    public String toString() {
        return " ";
    }
}