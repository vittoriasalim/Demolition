package demolition;

 
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    /**Instantiate map objects to be used */
    private Map map = new Map();
    /**to identify bombguy direction */
    private int direction = 0;
    /*count frame every second */
    private int frameCounter = 0;
    /**store the map of the objects */
    private Block[][] grid ;
    /**all objects located in the background such as : grass, bomb */
    private ArrayList<Block> bg;
    /**keey track of enemy lives */
    private int live =0;
    /**keep track if bombguy has reaches end goal */
    public static boolean win = false;
    /**keep track of bombguy level */
    public static int lvl =0;
    public String configFile ;

    /**Default constructor */
    public Database(){
        this.grid = map.getBlock();
        this.bg =  map.getBg();
    }
    /* 
     * Helper method used to remove all blocks 
     */
    private void emptyGrid(){
        for (int i =0 ; i < Map.ROW ; i++){
            for(int j =0 ; j < Map.COL ;j++){
                this.grid[i][j]= null;
            }
        }

    }
    /**
     * Setting up blocks from json files
     * @param app processing library used to draw,animate images
     * @param lvl update blocks according to current level
     */
    public void updateBlock(PApplet app, int lvl){
        map.setGame(app,lvl,configFile);
        this.live = map.live;
    }

    /**
     * Draw backgroud images, such as : empty tiles , clock, live and bomb
     * Also to keep track if bomb is ready to explode
     * @param app processing library used to process images
     */
    public void drawBackground(PApplet app){
        Bomb tempBomb = null; 
        for (Block i : this.bg){
            if(i instanceof Bomb){
                Bomb b = (Bomb)i;
                // render every 0.25 seconds
                if (this.frameCounter%Bomb.FRAME_BOMB==0)
                    b.render(app);
                else if (b.explode(app,this.grid,bg))
                    tempBomb =b;
            }
            i.draw(app);
        }
        if(tempBomb!=null)
            this.bg.remove(tempBomb);        
    }
    /**
     * Update Game is used to update game according to game situation
     * if bombguy win, if enemy bomb, if bombguy bomb, if time has exceed or game over
     * @param app, processing library to process images
     */
    public void updateGame(PApplet app){
        if(this.lvl == map.totalLvl()){
            emptyGrid();          
            app.fill(0,0,0);
            app.text("YOU WIN !", 128,200);
            this.bg.removeAll(this.bg);
        }
        else if(this.live == 0 || map.time == 0|| Map.reset||this.win){
            emptyGrid();
            if(!this.win){
                app.fill(0,0,0);
                app.text("GAME OVER", 128,200);
                this.bg.removeAll(this.bg);
            }
            if(Map.reset||this.win){   
                this.win =false;
                if(Map.reset){
                    this.live -=1;
                    Map.reset = false;
                }
                map.setGame(app,this.lvl,configFile);
            }
        }else{
            app.fill(0,0,0);
            app.text(this.live, 170,50);
            app.text(map.time, 298,50);
            this.frameCounter++;
            if(this.frameCounter%60==0){
                map.time --;
            }
        }
    }
    /**
     * Helper method to render bomb and to clear up bomb when it has explode
     * @param app processign library
     * @param y y coordinate of the bomb
     * @param x x coordinate of the bomb
     * @param block the object of the bomb
     */
    private void updateBomb(PApplet app,int y, int x,Block block){
        if(!this.bg.contains(block))
            this.bg.add(new Empty(x*32,y*32,app.loadImage("src/main/resources/empty/empty.png")));
        Bomb b = (Bomb) block;
        b.tick(x,y, this.grid);
    }

    private void updatePlayer(int x, int y,PApplet app,Block player){
        Player play = (Player) player;
        play.renderDirection(app);
        if (player instanceof BombGuy)
            setKey(app,y, x, (BombGuy) player);
        else{
            Enemy enemy =(Enemy)play;
            enemy.collision(y,x,this.grid);
        }             
        play.tick(y,x,this.grid);  
    }
    
    /**
     * Draw method to draw all objects such as:
     * grid, walls ,enemies, bombguy and etc
     * @param app, processing library to process images
     */
    public void drawBlock(PApplet app){
        for (int i =0 ; i <Map.ROW;i++){
            for(int j =0; j < Map.COL;j++){
                if (this.grid[i][j] != null){
                    if(this.grid[i][j] instanceof Bomb)
                    {
                        updateBomb(app,i,j,this.grid[i][j]);                                                
                    }
                    if (this.grid[i][j] instanceof Player){
                        updatePlayer(j,i,app,this.grid[i][j]);
                    } 
                    if(this.grid[i][j]!=null)                   
                        this.grid[i][j].draw(app);  
                }
            }
        }    
    }
    /**
     * Set direction of the bombguy 
     * @param direction keycode or the direction
     */
    public void setDirection(int direction){
        this.direction = direction;
    }
    
    /**
     * Set key directions to move bombGuy according to its axes
     * @param app processing library to process images
     * @param y   coordinate of y axis
     * @param x   coordinate of x axis
     * @param bGuy  the object of bombguy to be moved
     */
    public void setKey(PApplet app,int y, int x , BombGuy bGuy){
        if(this.direction == App.RIGHT){
            bGuy.moveXaxis(y,x,this.grid,1);
        }else if (this.direction == App.DOWN){
            bGuy.moveYaxis(y,x,this.grid,1);        
        }else if (this.direction == App.LEFT){
            bGuy.moveXaxis(y,x,this.grid,-1);
        }else if (this.direction == App.UP ){
            bGuy.moveYaxis(y,x,this.grid,-1);
        }else if(this.direction == App.SPACE){
            bGuy.loadBomb(app,y,x,this.bg);
   
        }
        this.direction =0;
    }
    /**
     * Get key pressed direction or bombguy direction
     * @return direction of key
     */
    public int direction(){
        return this.direction;
    } 
    /**
     * Get the map objects
     * @return 2d array of game map
     */
    public Block[][] getGrid(){
        return this.grid;
    }
    /**
     * Get map background objects
     * @return background objects of map 
     */
    public ArrayList<Block> getBg(){
        return this.bg;
    }
    public void configFile(String newConfig){
        this.configFile = newConfig;
    }
}
