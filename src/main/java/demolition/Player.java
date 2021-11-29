package demolition;

import processing.core.PImage;
import processing.core.PApplet;


public abstract class Player extends Block{
    /**CONSTANT frame to render every 0.2 seconds*/
    public final int FRAME = 12;
    /**directions of player */
    protected boolean right,left,up,down;
    /**keep track of frame and index */
    protected int frameCount,index;
    /**collections of player sprites */
    protected PImage[] sprites ;
    /**is player moving x axis or y axis */
    protected boolean isXAxis,isYAxis;
    /**
     * Player constructor, subclass of blocks
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param sprite  sprite/image of an object
     */
    public Player(int x, int y, PImage sprite) {
        super(x,y-15,sprite);
        this.right =this.left = this.up= this.down = false;
        this.frameCount = 0;
        this.sprites = new PImage[4];
        this.isXAxis=this.isYAxis=false;
        this.index =0;
    }
    /**
     * Method to set sprites according to its direction
     * @param frame frame direction out of 4 to replace 
     * @param app   processing images
     */
    public abstract void setDirections(String frame,PApplet app);
    /**
     * Method to move characters by x-axis according to its logic
     * @param y, y-coordinate of an object 
     * @param x, x-coordinate of an object
     * @param grid, 2d array of map objects
     * @param index, index to check if character should turn left or right
     */
    public abstract void moveXaxis(int y, int x, Block[][] grid,int index);
    /**
     * Method to move characters by y-axis according to its logic
     * @param y  y-coordinate of an object 
     * @param x  x-coordinate of an object
     * @param grid  2d array of map objects
     * @param index index to check if character should turn up or down
     */
    public abstract void moveYaxis(int y, int x, Block[][] grid,int index);
    /**
     * Render character sprites ever 0.2 seconds according to its direction
     * @param app  processing library images
     */
    public void renderDirection(PApplet app){
        if (this.frameCount >= FRAME*4 ){
            this.frameCount=0;
            setDirections("1",app);
        }else if (this.frameCount >= FRAME*3 )
            setDirections("4",app);
        else if (this.frameCount >= FRAME*2 )
            setDirections("3",app);
        else if (this.frameCount >= FRAME )
            setDirections("2",app);
        else{
            setDirections("1",app);
        }
        if(this.right == true)
            setSprite(this.sprites[0]);
        else if (this.left == true)
            setSprite(this.sprites[1]);
        else if (this.up == true)
            setSprite(this.sprites[2]);
        else
            setSprite(this.sprites[3]);
    }
    /**
     * Move player  and update its sprite
     * @param y  y-coordinate of an object 
     * @param x  x-coordinate of an object
     * @param grid  2d array of map objects
     */
    public abstract void tick(int y, int x,Block[][] grid);
  
  


}
