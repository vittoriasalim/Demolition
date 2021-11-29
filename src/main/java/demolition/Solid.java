package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class Solid extends Block{
    /**
     * subclass of blocks
     * @param x , x-coordinate of an object
     * @param y, y-coordinate of an object
     * @param sprite, sprite/image of an object
     */
    public Solid(int x, int y, PImage sprite) {
        super(x,y,sprite);
    }   
}
