package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public abstract class Block{
    /**x-coordinate of the blocks*/
    protected int x;
    /**y-coordinate of the blocks */
    protected int y;
    /*image of sprite object*/
    protected PImage sprite;

    /**
     * Instantiate super class constructor
     * @param x - x-coordinate of an object
     * @param y - y-coordinate of an object
     * @param sprite - sprite/image of an object
    */
    public Block(int x,int y,PImage sprite) {
        this.x = x;
        this.y = y+64;
        this.sprite = sprite;
    }
    /**
     * Set sprite to new sprite
     * @param sprite new sprite
     */
    public void setSprite(PImage sprite){
        this.sprite =sprite;
    }
    /**
     * Function to draw image
     * @param app  processing library to process images 
     */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }
    /**
     * Get the x-coordinate of image object
     * @return this.x 
     */
    public int getX(){
        return this.x;
    }
    /**
     * Get the y-coordinate of image object
     * @return this.y
     */
    public int getY(){
        return this.y;
    }
     /**
     * Get object sprites
     * @return PImage
     */
    public PImage getSprite(){
        return this.sprite;
    }

}
