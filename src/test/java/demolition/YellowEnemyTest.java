package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;


public class YellowEnemyTest {
    private App app;
    private YellowEnemy enemy;
    private PImage sprite;
    private Block [][] grid; 
    private Database db;

     /**
     * Setting up app class and other classes to test yellow enemy functionalities
     */
    @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.noLoop();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
    
        this.sprite= this.app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
        this.enemy = new YellowEnemy(160,160,this.sprite);
        this.db = new Database();
        this.grid = this.db.getGrid();
        this.grid[5][5] = this.enemy;
    }
    /**
     * move yellow enemy by x axis
     */
    @Test
    public void moveYellowRightXTest(){
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][6]);
        assertTrue(this.grid[5][6] instanceof YellowEnemy);
    }
    /**
     * move yellow enemy by y axis
     */
    @Test
    public void moveYellowDownYTest(){
       
        this.enemy.moveYaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[6][5]);
        assertTrue(this.grid[6][5]instanceof YellowEnemy);
    }
    /**
     * move enemy to the up
     */
    @Test
    public void moveYellowUpYTest(){
      
        this.enemy.moveYaxis(5,5,this.grid,-1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[4][5]);
        assertTrue(this.grid[4][5]instanceof YellowEnemy);
    }
    /**
     * move enemu to the left
     */
    @Test
    public void moveYellowLeftXTest(){
      
        this.enemy.moveXaxis(5,5,this.grid,-1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][4]);
        assertTrue(this.grid[5][4] instanceof YellowEnemy);
    }
    /**
     * when enemy meet bombguy 
     * will be rest to current level
     */
    @Test
    public void moveYellowBombGuyXTest(){
       
        this.grid[5][6] = new BombGuy(160,192,app.loadImage("src/main/resources/player/playerl.png"));
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(Map.reset);
    }
    /**
     * when enemy meet bombguy at the y axis
     */
    @Test
    public void moveYellowBombGuyYTest(){
        
        this.grid[6][5] = new BombGuy(192,160,app.loadImage("src/main/resources/player/playerl.png"));
        this.enemy.moveYaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(Map.reset);
    }
    /**
     * yellow enemy meet red enemy 
     */
    @Test
    public void yelloEnemyYTest(){
       
        this.grid[6][5] = new RedEnemy(192,160,app.loadImage("src/main/resources/red_enemy/red_downl.png"));
        this.enemy.moveYaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[6][5] instanceof YellowEnemy);
    }
    /**
     * yellow enemy meet red enemy at the x axis
     */
    @Test
    public void yellowEnemyXTest(){
       
        this.grid[5][6] = new RedEnemy(160,192,app.loadImage("src/main/resources/red_enemy/red_downl.png"));
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[5][6] instanceof YellowEnemy);
    }
    /**
     * yellow enemy encountered wall at the x axis
     */
 
    @Test
    public void moveYellowSolidRightTest(){
      
        this.grid[5][6] = new Solid(160,192,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[5][6] instanceof Solid);
        assertTrue(this.grid[6][5] instanceof YellowEnemy);
    }
    /**
     * encountered solid wall at y axis
     */
    @Test
    public void moveYellowSolidDownTest(){
       
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveYaxis(5,5,this.grid,1);
        this.enemy.collision(5,5,this.grid);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[6][5] instanceof Solid);
        assertTrue(this.grid[5][4] instanceof YellowEnemy);
    }
        /**
     * Mix testcases of solid wall
     * 
     */
    @Test
    public void moveYellowSolidLeftTest(){
       
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[5][4] = new Solid(160,128,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveXaxis(5,5,this.grid,-1);
        this.enemy.collision(5,5,this.grid);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][4]);
        assertTrue(this.grid[4][5] instanceof YellowEnemy);
    }
    // /**
    //  * encountered solid wall at the left
    //  */
    @Test
    public void goClockwiseXTest(){
        
        this.grid[5][6] = new Solid(160,192,app.loadImage("src/main/resources/wall/solid.png"));

        this.grid[4][5] = new Solid(128,160,app.loadImage("src/main/resources/wall/solid.png"));
    
        this.enemy.moveYaxis(5,5,this.grid,-1);
        this.enemy.moveXaxis(5,5,this.grid,1);
        this.enemy.collision(5,5,this.grid);
        
        set60seconds(this.enemy,5,5);
        
        assertTrue(this.grid[6][5] instanceof YellowEnemy);
    }
    @Test
    public void goClockwiseYTest(){
        
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[5][6] = new Solid(160,192,app.loadImage("src/main/resources/wall/solid.png"));
        //this.enemy.collision(5,5,this.grid);
        this.enemy.moveXaxis(5,5,this.grid,1);
        
        this.enemy.moveYaxis(5,5,this.grid,1);
        this.enemy.collision(5,5,this.grid);
        
        set60seconds(this.enemy,5,5);
       
        assertTrue(this.grid[5][4] instanceof YellowEnemy);
    }
    @Test
    public void goStraightTest(){
       
        this.enemy.collision(5,5,this.grid);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[6][5] instanceof YellowEnemy);
    }
    /** solid wall at the right */
    @Test
    public void moveYellowSolidUpTest(){
       
        this.grid[4][5] = new Solid(128,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[5][4] = new Solid(160,128,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.collision(5,5,this.grid);
        this.enemy.moveYaxis(5,5,this.grid,-1);
        this.enemy.collision(5,5,this.grid);
        set60seconds(this.enemy,5,5);
        this.enemy.collision(5,5,this.grid);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[5][6] instanceof YellowEnemy);
    }



    /**
     * move when it is 1 second ans 60 frames
     */
    private void set60seconds(YellowEnemy enemy, int x,int y){
        for (int i =0 ; i < 60;i++){//move every 60 frames
            enemy.tick(y,x,this.grid);
        }
    }
}