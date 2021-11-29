package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class RedEnemy extends Enemy{
    /**
     * Subclass of player and enemy  and blocks
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param sprite sprite/image of an object
     */
    public RedEnemy(int x, int y, PImage sprite) {
        super(x,y,sprite);
        this.count= this.move  =0;
        this.isXAxis = this.isYAxis = false;
    }
    @Override
    public void moveXaxis(int y, int x,Block[][] grid, int index){
        if (grid[y][x+index]==null || grid[y][x+index] instanceof Player){
            moveX(x,y,index);   //move when meet other player
        }
        else if (grid[y][x+index]!=null){
            randomX(grid,x,y); //choose red enemy path
            this.isXAxis= false;
            this.move =0;
        }
        else{
            this.isXAxis= false; 
            this.move = 0; 
        }
               
    
    }
    @Override
    public void moveYaxis(int y, int x,Block[][] grid, int index){
     
        if (grid[y+index][x]==null || grid[y+index][x] instanceof Player){
            moveY(x,y,index); //move enemy when empty of meet other player
        }
        else if (grid[y+index][x]!=null){
            randomY(grid,x,y); //when encountered blocks choose other path
            this.isYAxis=false;
            this.move = 0;
        }
        else{
            this.isYAxis= false;
            this.move = 0;    
        }
    }
    /**
     * Deciding the random x-axis path of red enemy 
     * @param grid  2d array of the may
     * @param x   x-coordinate of the object to modify
     * @param y   y-coordinate of the object to modify
     */
    private void randomX(Block[][] grid, int x, int y){
        if (grid[y+1][x] == null){
            this.down = true;
            this.up = this.left = this.right = false;
        }else if (grid[y-1][x] == null){
            this.up = true;
            this.down = this.left =this.right = false;
        }else if(grid[y][x+1] ==null){
            this.right = true;
            this.left =this.up =this.down =false;
        }else if( grid[y][x-1] == null){
            this.left = true;
            this.right = this.up=this.down = false;
        }
    }
    /**
     * Deciding the random y-axis path of red enemy 
     * @param grid 2d array of the may
     * @param x    x-coordinate of the object to modify
     * @param y    y-coordinate of the object to modify
     */

    private void randomY(Block [][] grid, int x, int y){
        if(grid[y][x+1] ==null){
            this.right = true;
            this.left =this.up =this.down =false;
        }else if( grid[y][x-1] == null){
            this.left = true;
            this.right = this.up=this.down = false;
        }else if (grid[y+1][x] == null){
            this.down = true;
            this.up = this.left = this.right = false;
        }else if (grid[y-1][x] == null){
            this.up = true;
            this.down = this.left =this.right = false;
        }
    }
    @Override
    // load enemy sprites
    public void setDirections(String frame, PApplet app){
        String [] url = {"src/main/resources/red_enemy/red_right1.png",
        "src/main/resources/red_enemy/red_left1.png",
        "src/main/resources/red_enemy/red_up1.png",
        "src/main/resources/red_enemy/red_down1.png"};

        for(int i =0 ; i < 4;i++){
            url[i] = url[i].replace("1", frame);
            this.sprites[i] = app.loadImage(url[i]); 
        }
       
    }

}