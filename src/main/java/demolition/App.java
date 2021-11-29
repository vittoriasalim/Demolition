package demolition;
import processing.core.PApplet;
import processing.core.PFont;
import java.awt.event.KeyEvent;

public class App extends PApplet{

    /**Dimension window of the app, width = 480*/
    public static final int WIDTH = 480;
    /**Dimension window of the app, height = 480*/
    public static final int HEIGHT = 480;
    /**Keypressed keycode left = 37*/
    public static final int LEFT = 37;
     /**Keypressed keycode right = 39*/
    public static final int RIGHT = 39;
     /**Keypressed keycode up = 38*/
    public static final int UP = 38;
     /**Keypressed keycode down = 40*/
    public static final int DOWN = 40; 
     /**Keypressed keycode space = 32*/ 
    public static final int SPACE = 32; 
     /**Frame rate 60 per seconds*/
    public static final int FPS = 60;
    public  String config="";
    
    private Database db;

    /**
     * App constructor
     */
    public App(){
        this.db = new Database(); //intantiate database
        
    }
    /**
     * Build windows of the app according to the size given
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    /**
     * Setting up frame rate by 60 per secs and and load image block according to level
     */
    public void setup() {
        frameRate(FPS);
        setConfig("config.json");
        this.db.configFile(this.config); 
        this.db.updateBlock(this, Database.lvl);
    }
    /**
     * Draw and update game config.
     * Consist of 2 draw methods one for background and for blocks.
     */
    public void draw() {
        background(239, 129 , 0);
        this.db.updateGame(this);
        this.db.drawBackground(this);
        this.db.drawBlock(this);
    }
    /**
     * Declaring key code. Set it according to the direction of the bombguy
     */
    public void keyPressed(){
        if (this.keyCode ==SPACE){
            this.db.setDirection(SPACE);

        }else if (this.keyCode == RIGHT){
            this.db.setDirection(RIGHT);

        }else if (this.keyCode == DOWN){
            this.db.setDirection(DOWN);
        
        }else if (this.keyCode == UP){
            this.db.setDirection(UP);
        
        }else if (this.keyCode == LEFT){
            this.db.setDirection(LEFT);
        }
    }
    /**
     * set configuration file according to file path
     * @param filepath the path to set config to
     */
    public void setConfig(String filepath){
        if(this.config == "")
            this.config = filepath;

    }
    /*
     * Main method to run the app
     *  */  
    public static void main(String[] args) {         
        PApplet.main("demolition.App");         
    }
}
