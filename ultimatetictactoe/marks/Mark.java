package ultimatetictactoe.marks;

import java.awt.Image;

public abstract class Mark {
    private Image image;
    protected int rowIndex;
    protected int colIndex;

    public Image getImage() {
        return image;
    }

    public void setImage(Image newImage) {
        image = newImage;
    }

    public boolean equals(Object otherMark) {
        otherMark = (Mark) otherMark;
        if (otherMark.toString().equals(this.toString())) {
            return true;
        }
        return false;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public abstract String toString();
}