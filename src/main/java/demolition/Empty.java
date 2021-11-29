package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class Empty extends Block{
    /**
     * Subclass of blocks
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param sprite sprite/image of an object
     */
    public Empty(int x, int y, PImage sprite) {
        super(x,y,sprite);
    }
}
