package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class YellowEnemy extends Enemy{

    /**
     * Subclass of Enemey, Player, Blocks
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param sprite sprite/image of an object
     */
    public YellowEnemy(int x, int y, PImage sprite) {
        super(x,y,sprite);
        this.count= this.move  =0;
        this.isXAxis =this.isYAxis = false;
    }
    @Override
    public void moveXaxis(int y, int x,Block[][] grid, int index){
        if (grid[y][x+index]==null || grid[y][x+index] instanceof Player){
            moveX(x,y,index);//move x axes
        }
        else if (grid[y][x+index]!=null){
            if(this.right){ //direct enemy to its path clockwise if being blocked
                this.down= true;
                this.right = this.left =this.up = false;
            }
            if(this.left){
                this.up = true;
                this.down = this.left=this.right = false;
            }
            this.isXAxis= false;
            collision(y,x,grid); 
        }
        else{
            this.isXAxis= false;
            this.move = 0; 
        }
    }
    @Override
    public void moveYaxis(int y, int x,Block[][] grid, int index){
        if (grid[y+index][x]==null || grid[y+index][x] instanceof Player){
            moveY(x,y,index);//move only when it find empty tiles
        }else if (grid[y+index][x]!=null ){
            if(this.down){//finding direction , not moving
                this.left = true;
                this.right =this.up=this.down = false;
            }else if (this.up){
                this.right = true;
                this.left = this.up = this.down = false;
            }else{
                this.left = true;
                this.right =this.up=this.down = false;
            }
            this.isYAxis= false;
        }else{
            this.isYAxis= false;
            this.move = 0;    
        }
    }
    //set direction of enemy sprites
    @Override 
    public void setDirections(String frame, PApplet app){
        String [] url = {"src/main/resources/yellow_enemy/yellow_right1.png",
        "src/main/resources/yellow_enemy/yellow_left1.png",
        "src/main/resources/yellow_enemy/yellow_up1.png",
        "src/main/resources/yellow_enemy/yellow_down1.png"};
        for(int i =0 ; i < 4;i++){
            url[i] = url[i].replace("1", frame);
            this.sprites[i] = app.loadImage(url[i]); 
        }
       
    }


}