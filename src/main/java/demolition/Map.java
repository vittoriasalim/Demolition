package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;
import java.util.ArrayList;


public class Map {
   /*To reset game configuration */
    public static boolean reset ;
    /*Default map row*/
    public static final int ROW = 13;
    /**Default map column */
    public static final int COL =15;
    /**To store game objects in a 2d array */
    private Block [][] grid = new Block[ROW][COL];
    /**Instantiate setting objects or file configuration */
    private Setting set = new Setting();
    /**Store background object , such as empty tiles */
    private ArrayList<Block> background = new ArrayList<>();  
    /**Eecord number or total levels availlable */
    private int numLevel=0;
   /**total lives in a game */
    public int live = 0;   
    /**total time in a game */ 
    public int time = 0;

   /**Default constructor */
    public Map (){
       this.reset = false;
    }
    /**
     * Get total levels in a game
     * @return total number of level in a map/game
     */
    public int totalLvl(){
       return this.numLevel;
    }
    /**
     * Return grids object of the map in a 2d array
     * @return grid of map
     */
    public Block[][] getBlock(){
        return this.grid;
    }
    /**
     * Get background object in an array list
     * such as: bomb , empty tiles, time and live
     * @return map background
     */
    public ArrayList<Block> getBg(){
        return this.background;
    }
    /**
     * Load the image of the game from json files to 2d array 
     * @param app  processing image to draw and process images
     * @param lvl  lvl to set the game at
     * @param configFile the configuration file to read at
     */
    public void setGame(PApplet app, int lvl,String configFile){
        
        set.readJSON(app,configFile);
        this.numLevel = set.numLevel();
        this.live = set.getLives();
        this.time = set.getTime(lvl);
        String filename = set.getPath(lvl);
        ArrayList<String[]> graph = set.getGrid(filename);

        for (int i =0 ; i < graph.size(); i++){
           for(int j =0 ;j < graph.get(i).length;j++){
                if ( graph.get(i)[j].equals("P")){ 
                   this.background.add(new Empty(j*32,i*32,app.loadImage("src/main/resources/empty/empty.png")));
                   this.grid[i][j] = new BombGuy(j*32,i*32,app.loadImage("src/main/resources/player/player1.png"));           
                } else if ( graph.get(i)[j].equals("Y")){ 
                   this.background.add(new Empty(j*32,i*32,app.loadImage("src/main/resources/empty/empty.png")));
                   this.grid[i][j] = new YellowEnemy(j*32,i*32,app.loadImage("src/main/resources/red_enemy/red_down1.png"));           
                }else if ( graph.get(i)[j].equals("R")){ 
                   this.background.add(new Empty(j*32,i*32,app.loadImage("src/main/resources/empty/empty.png")));
                   this.grid[i][j] = new RedEnemy(j*32,i*32,app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"));           
                } else if ( graph.get(i)[j].equals("W")){
                   this.grid[i][j] = new Solid(j*32,i*32,app.loadImage("src/main/resources/wall/solid.png"));
                } else if ( graph.get(i)[j].equals(" ")){
                   this.background.add(new Empty(j*32,i*32,app.loadImage("src/main/resources/empty/empty.png")));
                } else if ( graph.get(i)[j].equals("B")){
                   this.grid[i][j] = new Broken(j*32,i*32,app.loadImage("src/main/resources/broken/broken.png"));
                } else if ( graph.get(i)[j].equals("G")){
                   this.grid[i][j] = new Goal(j*32,i*32,app.loadImage("src/main/resources/goal/goal.png"));
                }else {
                   this.background.add(new Empty(j*32,i*32,app.loadImage("src/main/resources/empty/empty.png")));
                }
           }

           PFont font = app.createFont("src/main/resources/PressStart2P-Regular.ttf",25);
           app.textFont(font);
           this.background.add(new Icon(128, 20 , app.loadImage("src/main/resources/icons/player.png")));
           this.background.add(new Icon(256, 20, app.loadImage("src/main/resources/icons/clock.png")));
        }
        
    }   



}
