package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;


public abstract class Enemy extends Player{
    /**Number of step to increment,
     * while count is to count number of frame**/
    protected int move,count;
    /**
     * Subclass of player
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param sprite sprite/image of an object
     */
    public Enemy(int x, int y, PImage sprite) {
        super(x,y,sprite);
    }
    /**
     * Set directions of enemy automove according to its index 
     * if index ==-1 , then left
     * if index == 1 , then right
     * @param x , x-coordinate of an object
     * @param y, y-coordinate of an object
     * @param index, directions
     */
    public void moveX(int x, int y,int index){
        this.move = 32*index;
        if(index ==1){
            this.right = true;
            this.left = this.up = this.down = false;
        }else if (index ==-1){
            this.left = true;
            this.right = this.up = this.down = false;
        }
        this.isXAxis = true;
        this.index = index;
    }
    /**
     * Set directions of enemy automove according to the index 
     * if index ==-1 , then up
     * if index == 1 , then down
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param index  directions
     */
    public void moveY(int x, int y,int index){
        this.move = 32*index;
        if(index ==1){
            this.down = true;
            this.left=this.right=this.up=false;
        }else if (index == -1){

            this.up = true;
            this.right=this.down=this.left =false;
        }
        this.isYAxis=true;
        this.index = index;
    }
    /**
     * Redirect enemy if it collides to the appropriate directions
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param grid  map of 2d array
     */
    public void collision(int y , int x, Block[][] grid){
        if(this.right)
            moveXaxis(y,x,grid,1);
        else if (this.left)
            moveXaxis(y,x,grid,-1);
        else if(this.up)
            moveYaxis(y,x,grid,-1);
        else if (this.down)
            moveYaxis(y,x,grid,1);
        else if (!this.down && !this.left && !this.right && !this.up)//straight line
            moveYaxis(y,x,grid,1);  
    }
    /**
     * Update sprite and modify grid in x-axis
     * Delete previous position and update to new grid 
     * Swap when enemy collides
     * Reset game if met bombguy or bomb, etc
     * if index==-1 , then left
     * if index == 1 , then right
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param move amount to move 
     * @param index  directions
     * @param grid  map of 2d array
     */
    public void tickXAxis(int x, int y , int move,int index,Block[][] grid){
        this.x+=move;
        if(grid[y][x+index] instanceof BombGuy){
            grid[y][x+index]=this;  
            Map.reset = true;
        }   
        else if(!(grid[y][x+index] instanceof Enemy)){
            grid[y][x+index]= this;
            grid[y][x]=null;
        }
        else if (grid[y][x+index] instanceof Enemy){
            int yelX = x*32;
            if (grid[y][x+index].getX() == yelX ){
                Enemy enemy = (Enemy)grid[y][x+index];
                grid[y][x+index]=this;
                grid[y][x] = enemy;
            }
            
        }
    }
    /**
     * Update sprite and modify grid in y-axis
     * Delete previous position and update to new grid 
     * Swap when enemy collides
     * Reset game if met bombguy or bomb, etc
     * if index==-1 , then up
     * if index == 1 , then down
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param move set amount to move
     * @param index directions
     * @param grid   map of 2d array
     */
    public void tickYAxis(int x, int y , int move, int index ,Block[][] grid){
        this.y +=move;
        if(grid[y+index][x] instanceof BombGuy){
            grid[y+index][x]= this;
            Map.reset = true;
        }   
        else if (!(grid[y+index][x] instanceof Enemy)){
            grid[y+index][x]= this;
            grid[y][x]=null;
        }
        else if (grid[y+index][x] instanceof Enemy){
            int yelY = ((y*32)+64)-15;    
            if (grid[y+index][x].getY() == yelY ){
                Enemy enemy = (Enemy)grid[y+index][x];
                grid[y+index][x]=grid[y][x];
                grid[y][x] = enemy;
            }                                                     
        }
    }
    /**
     * Redirect object moves .
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param grid  map of 2d array
     */
    public void tick(int y , int x, Block[][] grid){
        this.frameCount++;
        this.count++;
        if(this.count%60==0){
            if(this.isXAxis){
                tickXAxis(x,y,this.move,this.index,grid);
            }
            else if (this.isYAxis){
                tickYAxis(x,y,this.move,this.index,grid);
            }
        }
    }
}
