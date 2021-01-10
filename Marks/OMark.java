package Marks;

import java.awt.Image;

public class OMark extends Mark{
    private Image image;

    public OMark(int row, int col) {
        rowIndex = row;
        colIndex = col;
        // set the image
        // super.setImage();
    }

    public Image getImage() {
        return super.getImage();
    }

    public String toString() {
        return "O";
    }
}