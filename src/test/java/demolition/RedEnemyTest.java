package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;


public class RedEnemyTest {
    private App app;
    private RedEnemy enemy;
    private PImage sprite;
    private Block [][] grid; 
    private Database db;
    /**
     * Setting up app class and other classes to test red enemy functionalities
     */
    @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.noLoop();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);

        
        this.sprite= this.app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
        this.enemy = new RedEnemy(160,160,this.sprite);
        this.db = new Database();
        this.grid = this.db.getGrid();
        this.grid[5][5] = this.enemy;
    }
    /**
     * Move Red enemy by x axis to the right
     */
    @Test
    public void moveXaxisTest(){
        
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][6]);
        assertTrue(this.grid[5][6] instanceof RedEnemy);
    }
    /**
     * Move enemy by y axis to the down
     */
    @Test
    public void moveYaxisTest(){
    
        this.enemy.moveYaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[6][5]);
        assertTrue(this.grid[6][5]instanceof RedEnemy);
    }
    /**
     * move enemy to the up
     */
  
    @Test
    public void moveUpYTest(){
      
        this.enemy.moveYaxis(5,5,this.grid,-1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[4][5]);
        assertTrue(this.grid[4][5]instanceof RedEnemy);
    }
    /** move  enemy tot he left*/
    @Test
    public void moveLeftXTest(){
       
        this.enemy.moveXaxis(5,5,this.grid,-1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][4]);
        assertTrue(this.grid[5][4] instanceof RedEnemy);
    }
    /**enemy meet bombguy at the x -axis */
    @Test
    public void moveBombGuyXTest(){
        this.grid[5][6] = new BombGuy(160,192,app.loadImage("src/main/resources/player/playerl.png"));
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(Map.reset);
    }
    /**
     * test enemy path
     */
    @Test
    public void moveRandomY(){
        Database db= new Database();
        this.grid[5][5] = this.enemy;
        this.grid[5][6] = new Solid(160,192,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
       
        this.enemy.moveYaxis(5,5,this.grid,1);
        this.enemy.collision(5,5,this.grid);
    
        set60seconds(this.enemy,5,5);

        assertTrue(this.grid[5][4] instanceof RedEnemy);

    }
    /**
     * test enemy path at x axis
     */
    @Test
    public void moveRandomX(){
        Database db= new Database();
        this.grid[5][5] = this.enemy;
        this.grid[5][6] = new Solid(160,192,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
       
        this.enemy.moveXaxis(5,5,this.grid,1);
        this.enemy.collision(5,5,this.grid);
    
        set60seconds(this.enemy,5,5);

        assertTrue(this.grid[4][5] instanceof RedEnemy);

    }
    /**
     * red enemy meet yellow enemy at y axis
     */
    @Test
    public void yellowEnemyYTest(){
        this.grid[5][5] = this.enemy;
        this.grid[6][5] = new YellowEnemy(192,160,app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"));
        this.enemy.moveYaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[6][5] instanceof RedEnemy);
    }
    /**
     * enemy meet yellow enemy x test
     */
    @Test
    public void yellowEnemyXTest(){
        this.grid[5][5] = this.enemy;
        this.grid[5][6] = new YellowEnemy(160,192,app.loadImage("src/main/resources/yellow_enemy/yello_downl.png"));
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[5][6] instanceof RedEnemy);
    }
    /**
     * enemy meet solid wall at x test
     */
    @Test
    public void moveSolidXTest(){
        this.grid[5][5] = this.enemy;
        this.grid[5][6] = new Solid(160,192,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[5][4] = new Solid(160,128,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[5][6] instanceof Solid);
    }
    /**
     * enemy meet solid wall at y axis
     */
    @Test
    public void moveSolidYTest(){
        this.grid[5][5] = this.enemy;
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveYaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[6][5] instanceof Solid);
    }
    /**
     * enemy meet solid wall x axis
     */
    @Test
    public void moveLeftSolidXTest(){
        this.grid[5][5] = this.enemy;
        this.grid[5][4] = new Solid(160,128,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveXaxis(5,5,this.grid,-1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][4]);
        assertTrue(this.grid[5][4] instanceof Solid);
    }
    /**
     * enemy meet solid wall y-axis up
     */
    @Test
    public void moveSolidYUpTest(){
        this.grid[5][5] = this.enemy;
        this.grid[4][5] = new Solid(128,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveYaxis(5,5,this.grid,-1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[4][5]);
        assertTrue(this.grid[4][5] instanceof Solid);
    }
    @Test
    public void testXSolidAxis(){
        this.grid[5][5] = this.enemy;
        this.grid[4][5] = new Solid(128,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[5][4] = new Solid(160,128,app.loadImage("src/main/resources/wall/.png"));
        this.enemy.moveXaxis(5,5,this.grid,-1);
        
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][5]);
        assertNotNull(this.grid[5][5]);
        assertNotNull(this.grid[6][5]);


        
    }
    @Test
    public void testXRandomAxis(){
        this.grid[5][5] = this.enemy;
        this.grid[4][5] = new Solid(128,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[6][5] = new Solid(192,160,app.loadImage("src/main/resources/wall/solid.png"));
        this.grid[5][6] = new Solid(160,192,app.loadImage("src/main/resources/wall/solid.png"));
        this.enemy.moveXaxis(5,5,this.grid,1);
        set60seconds(this.enemy,5,5);
        assertNotNull(this.grid[5][5]);
        assertNotNull(this.grid[4][5]);
        assertNotNull(this.grid[6][5]);
        //assertTrue(this.grid[4][5] instanceof Solid);

        
    }
    
    private void set60seconds(RedEnemy enemy, int x,int y){
        for (int i =0 ; i < 60;i++){//move every 60 frames
            enemy.tick(y,x,this.grid);
        }
    }
}