package Marks;

import java.awt.Image;

public abstract class Mark {
    private Image image;

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

    public abstract String toString();
}