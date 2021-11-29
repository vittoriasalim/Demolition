package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;


public class BombGuyTest {
    private App app;
    private BombGuy bombGuy;
    private PImage sprite;
    private Database db; ;
    private Block [][] grid; 
    private Map map;

    @BeforeEach
    /**
     * Set up bombguy object and map , including database to check all 
     * to check functionality of bombguy work as intended
     */
    public void setUp(){        
        this.app = new App();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);      
        this.map = new Map();
        this.sprite= this.app.loadImage("src/main/resources/player/player1.png");
        this.bombGuy = new BombGuy(160,160,this.sprite);
        this.db = new Database();
        this.grid = this.db.getGrid();
    }
    /**
     * test if bombguy able to move along x axis when key is pressed
     */
    @Test
    public void moveXaxisTest(){
        this.grid[5][5] = this.bombGuy;
        this.bombGuy.moveXaxis(5,5,this.grid,1);
        this.bombGuy.tick(5,5,this.grid);
        assertNotNull(this.grid[5][6]);
        assertTrue(this.grid[5][6] instanceof BombGuy);
    }
    /**
     * test if bombguy able to move along y axis when key is pressed
     */
    @Test
    public void moveYaxisTest(){
        this.grid[5][5] = this.bombGuy;
        this.bombGuy.moveYaxis(5,5,this.grid,1);
        this.bombGuy.tick(5,5,this.grid);
        assertNotNull(this.grid[6][5]);
        assertTrue(this.grid[6][5]instanceof BombGuy);
    }
    /**
     * test if bombguy reaches goal and update to new level
     */
     @Test
    public void moveGoalXTest(){
        this.grid[5][5] = this.bombGuy;
        this.grid[5][6] = new Goal(160,192,app.loadImage("src/main/resources/goal/goal.png"));
        this.bombGuy.moveXaxis(5,5,this.grid,1);
        this.bombGuy.tick(5,5,this.grid);
 
        assertTrue(Database.win);
    }
    /**
     * move bombguy upwards
     */
    @Test
    public void moveUpYTest(){
        this.grid[5][5] = this.bombGuy;
        this.bombGuy.moveYaxis(5,5,this.grid,-1);
        this.bombGuy.tick(5,5,this.grid);
        assertNotNull(this.grid[4][5]);
        assertTrue(this.grid[4][5]instanceof BombGuy);
    }
    /** move bombguy to the left*/
    @Test
    public void moveLeftXTest(){
        this.grid[5][5] = this.bombGuy;
        this.bombGuy.moveXaxis(5,5,this.grid,-1);
        this.bombGuy.tick(5,5,this.grid);
        assertNotNull(this.grid[5][4]);
        assertTrue(this.grid[5][4] instanceof BombGuy);
    }
    /**
     * when bombguy meet enemy and level should be reset
     */
    @Test
    public void moveEnemyXTest(){
        this.grid[5][5] = this.bombGuy;
        this.grid[5][6] = new RedEnemy(160,192,app.loadImage("src/main/resources/red_enemy/red_down1.png"));
        this.bombGuy.moveXaxis(5,5,this.grid,1);
        this.bombGuy.tick(5,5,this.grid);
 
        assertTrue(Map.reset);
    }
    /**
     * bombguy meet enemy from y axis
     */
    @Test
    public void moveEnemyYTest(){
        this.grid[5][5] = this.bombGuy;
        this.grid[6][5] = new RedEnemy(192,160,app.loadImage("src/main/resources/red_enemy/red_down1.png"));
        this.bombGuy.moveYaxis(5,5,this.grid,1);
        this.bombGuy.tick(5,5,this.grid);
 
        assertTrue(Map.reset);
    }
    /**
     * Bombguy loading bomb to the background
     */
    @Test
    public void testLoadBomb(){
        this.bombGuy.loadBomb(this.app,5,5,db.getBg());
        assertFalse(db.getBg().isEmpty());
    }




}