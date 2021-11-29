package demolition;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;

public class BombGuy extends Player{
    /**
     * Declaring attributes of the class
     */
    private int xVal , yVal;
    /**
     * subclass of player, blocks
     * @param x      x-coordinate of an object
     * @param y      y-coordinate of an object
     * @param sprite sprite/image of an object
     */

    public BombGuy(int x, int y, PImage sprite) {
        super(x,y,sprite);
        this.xVal = this.yVal = 0;    
    }

    @Override
    public void moveXaxis(int y, int x,Block[][] grid, int index){
        if(grid[y][x+index] instanceof Goal){
            Database.lvl +=1; //move to next level when reaches the goal
            Database.win = true;
        }
        if (grid[y][x+index]==null || grid[y][x+index] instanceof Enemy){
            if (index ==-1){ //move to the next tile when key is pressed
                this.left = true;
                this.right = this.down = this.up = false;
            }else if (index==1){ //set the direction
                this.right = true;
                this.down = this.left = this.up = false;
            }
            this.index= index;
            this.xVal=32*index;
            this.isXAxis=true;
   
        }else{
            this.xVal = 0;
        }     
  
    }
    /**
     * Load bomb in bomb guy position at the background
     * @param y, y-coordinate of an object 
     * @param x, x-coordinate of an object
     * @param background, list of map background
     * @param app, processing image
     */
    public void loadBomb(PApplet app,int y, int x, ArrayList<Block> background){
        background.add(new Bomb(x*32,y*32,app.loadImage("src/main/resources/bomb/bomb.png")));
    }
    
    @Override
    public void moveYaxis(int y, int x, Block[][] grid,int index){
        if(grid[y+index][x] instanceof Goal){
            Database.lvl+=1; //move next level
            Database.win = true;
        }

        if (grid[y+index][x]==null || grid[y+index][x] instanceof Enemy){
            this.yVal=32*index;
            if (index ==1){ //set direction
                this.down = true;
                this.right = this.left = this.up = false;
            }else if (index == -1){
                this.up = true;
                this.right = this.left = this.down = false;
            }
            this.index= index;
            this.isYAxis=true;
        }else{
            this.yVal =0;
        }
    }
    
    @Override
    public void tick(int y , int x , Block[][] grid) {
        this.frameCount++;
        if (this.isXAxis){
            this.x +=this.xVal;  //move bombguy to desired position
            if(grid[y][x+this.index] instanceof Enemy){
                Map.reset = true; //reset current level when encountered enemy
                grid[y][x+this.index] = this;
            }else{
                grid[y][x+this.index] = this;
                grid[y][x]=null;
            }         
        }else if (this.isYAxis){
            this.y += this.yVal; 
            if(grid[y+this.index][x] instanceof Enemy){
                Map.reset = true; //reset current level when encountered enemy
                grid[y+this.index][x] = this;
            } else{
                grid[y+this.index][x] = grid[y][x];
                grid[y][x]=null;

            }       
        }  
        this.isXAxis = this.isYAxis=false;
    }
    @Override
    public void setDirections(String frame, PApplet app){
        String [] url = {"src/main/resources/player/player_right1.png",
        "src/main/resources/player/player_left1.png",
        "src/main/resources/player/player_up1.png", //change sprites according to direction
        "src/main/resources/player/player1.png"};
        for(int i =0 ; i < 4;i++){
            url[i] = url[i].replace("1", frame);
            this.sprites[i] = app.loadImage(url[i]); 
        }
        
       
    }

}