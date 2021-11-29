package demolition;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;

public class Bomb extends Block{
    /*to count number the total of sprites based on the frame counter*/
    private int getFrame;
    /**indicate the time to explode*/
    private int stopExplode;
    /*Indicate number of frames to render each sprites*/
    public static final int FRAME_BOMB =15;
    /**Collection of sprites to render */
    protected PImage[] spriteCollection ;
    /**url for left sprites */
    private String[] urlLeft = {       
        "src/main/resources/explosion/horizontal.png",
        "src/main/resources/explosion/end_left.png"
    };
    /**url for right sprites */
    private String[] urlRight ={       
        "src/main/resources/explosion/horizontal.png",
        "src/main/resources/explosion/end_right.png"
    };
    /**url for top sprites */
    private String[] urlTop ={       
        "src/main/resources/explosion/vertical.png",
        "src/main/resources/explosion/end_top.png"
        
    };
    /**url for bottom sprites */
    private String[] urlBot = {
        "src/main/resources/explosion/vertical.png",
        "src/main/resources/explosion/end_bottom.png"};
    /**
     * Subclass of blocks
     * @param x  x-coordinate of an object
     * @param y  y-coordinate of an object
     * @param sprite sprite/image of an object
     */
    public Bomb(int x, int y, PImage sprite) {
        super(x,y,sprite);
        this.spriteCollection = new PImage [8];
    }
    /**
     * check if bomb is time to explode , then load image
     * @param app processing library
     * @param grid map of objects
     * @param background objects of map in the background 
     * @return true : if it is time to explode and no when it is not
     */
    public boolean explode(PApplet app,Block[][] grid,ArrayList<Block> background){

        if(this.getFrame == 8){//0.25 seconds for 8 sprite
            int x = this.x/32;
            int y = (this.y-64)/32;
            if(grid[y][x] instanceof BombGuy){
                Map.reset = true;
            }
            grid[y][x] = new Bomb(this.x,this.y-64,app.loadImage("src/main/resources/explosion/centre.png"));
            loadVertical(app,x,y,grid,-1);
            loadVertical(app,x,y,grid,+1);
            loadHorizontal(app,x,y,grid,-1);
            loadHorizontal(app,x,y,grid,1);

            return true;
        }else{
            return false;
        }
    }
    /**
     * Increment time frame to set time when it is about to explode or remain
     * @param x, x-coordinate of object
     * @param y, y -coordinate of object
     * @param grid, 2d array of map to modify object
     */
    public void tick(int x, int y,Block[][] grid){

        this.stopExplode++;
        if(this.stopExplode>=30){
            grid[y][x]=null;
            //this.stopExplode =0;
     
        }        
    }
    /**
     * render sprites of bomb remain 2 seconds for 8 sprites before it explodes
     * @param app processing image 
     */
    public void render(PApplet app){
        setSprites(app);
        if(this.getFrame == 8 ){
            this.getFrame = 0;
        }
        setSprite(this.spriteCollection[getFrame]);
        this.getFrame++;
    }
    /**
     * Import sprites to the collections according to its time frame
     * @param app, processing library
     */
    private void setSprites(PApplet app){
        String url = "src/main/resources/bomb/bomb.png";
        for(int i =0 ; i < 8 ; i++){
            String newUrl = url.replace("bomb.png","bomb"+ (i+1)+".png");
            this.spriteCollection[i] = app.loadImage(newUrl); 
        }
       
    }
     /**
     * Load horizonatal sprites , eliminate bombguy and enemy
     * @param app processing image library 
     * @param x x-coordinate of object
     * @param y y -coordinate of object
     * @param grid 2d array of map to modify object
     * @param index index decalring if it goes left or right
     */
    private void loadHorizontal(PApplet app,int x , int y,Block[][] grid,int index){
        int pos = 0;
        String [] url =null;
        if(index == -1)
            url = urlLeft;
        else
            url = urlRight;
        for(int i =0 ; i < 2 ;i ++){
            pos+=index;
            if(grid[y][x+pos] ==null|| grid[y][x+pos] instanceof Player || grid[y][x+pos] instanceof Broken){
                int xIndex = pos*32;
                if(grid[y][x+pos] instanceof Broken){
                    grid[y][x+pos] = new Bomb(this.x+xIndex,this.y-64,app.loadImage(url[i]));
                    break;
                }
                if(grid[y][x+pos] instanceof BombGuy){
                    Map.reset =true;
                }
                grid[y][x+pos] = new Bomb(this.x+xIndex,this.y-64,app.loadImage(url[i]));
            }else if(grid[y][x+pos]!=null){
                break;
            }
        }

    }
    /**
         * Load vertical sprites , eliminate bombguy and enemy
         * @param app processing image library 
         * @param x x-coordinate of object
         * @param y y -coordinate of object
         * @param grid 2d array of map to modify object
         * @param index index decalring if it goes up or down
     */
    private void loadVertical(PApplet app,int x , int y,Block[][] grid,int index){
        int pos = 0;
        String [] url =null;
        if(index == -1)
            url = urlTop;
        else
            url = urlBot;
        for(int i =0 ; i < 2 ;i ++){
            pos+=index;
           
            if(grid[y+pos][x] ==null|| grid[y+pos][x] instanceof Player || grid[y+pos][x] instanceof Broken ){
                int yIndex = pos*32;
                if(grid[y+pos][x] instanceof Broken){
                    grid[y+pos][x] = new Bomb(this.x,this.y-64+yIndex,app.loadImage(url[i]));
                    break;
                }
                if(grid[y+pos][x] instanceof BombGuy){
                    Map.reset =true;
                }
                grid[y+pos][x] = new Bomb(this.x,this.y-64+yIndex,app.loadImage(url[i]));
            }else if(grid[y+pos][x]!=null){
                break;
            }
        }

    }

}