package src.marks;

import java.awt.Image;

public class XMark extends Mark{
    private Image image;

    public XMark(int row, int col) {
        rowIndex = row;
        colIndex = col;
        // set the image
        // super.setImage();
    }

    public Image getImage() {
        return super.getImage();
    }

    public String toString() {
        return "X";
    }

}
