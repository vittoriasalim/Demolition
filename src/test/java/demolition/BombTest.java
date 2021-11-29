package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;


public class BombTest {
    private App app;
    private Bomb bomb;
    private PImage sprite;
    private Database db; ;
    private Block [][] grid; 
    /**
     * setup bomb object and other classes to test the functionality o
     */
    @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.noLoop();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
  
        this.sprite= this.app.loadImage("src/main/resources/bomb/bomb.png");
        this.bomb = new Bomb(160,160,this.sprite);//grid 5 , 5
        this.db = new Database();
        this.grid = this.db.getGrid();
    }


    @Test
    /**
     * Sprite changes every 8 frames and explodes
     */
    public void testExplodes(){
        grid[5][5]= this.bomb;
        setFrameExplode(this.bomb,this.grid);
        boolean result = this.bomb.explode(this.app,this.grid,this.db.getBg());
        assertTrue(result);
            
    }
    /**
     * test destroying horizontal broken wall
     */
    @Test
    public void destroyBrokenHorizontal(){
        grid[5][6]= new Broken(160,192,this.app.loadImage("src/main/resources/broken/broken.png"));
        grid[6][5]= new Broken(192,160,this.app.loadImage("src/main/resources/broken/broken.png"));
        setFrameExplode(this.bomb,this.grid);
        boolean result = this.bomb.explode(app,grid,db.getBg());
        assertTrue(result);
        assertFalse(grid[5][6] instanceof Broken);
        assertTrue(grid[5][6] instanceof Bomb);
        assertFalse(grid[6][5] instanceof Broken);
        assertTrue(grid[6][5] instanceof Bomb);
    }
     /**
     * test destroying vertical broken wall
     */
    @Test
    public void destroyBrokenVertical(){
        grid[6][5]= new Broken(192,160,this.app.loadImage("src/main/resources/broken/broken.png"));
        setFrameExplode(this.bomb,this.grid);
        boolean result = this.bomb.explode(app,grid,db.getBg());
        assertTrue(result);
        assertFalse(grid[6][5] instanceof Broken);
        assertTrue(grid[6][5] instanceof Bomb);
    }

    /**
     * test destroying horizontal bombguy
     */
    @Test
    public void destroyBombGuyHorizontal(){
        this.grid[5][6] = new BombGuy(160,192,this.app.loadImage("src/main/resources/broken/broken.png"));
        
        setFrameExplode(this.bomb,this.grid);
        boolean result = this.bomb.explode(app,grid,db.getBg());
        assertTrue(result);
        assertFalse(grid[5][6] instanceof BombGuy);
        assertTrue(grid[5][6] instanceof Bomb);

    }
         /**
     * do not destroy solid wall test
     */
    @Test
    public void preserveSolid(){
        grid[5][6] = new Solid(160,192,this.app.loadImage("src/main/resources/wall/solid.png"));
        setFrameExplode(this.bomb,this.grid);
        boolean result = this.bomb.explode(app,grid,db.getBg());
        assertNotNull(grid[5][6]);
        assertTrue(grid[5][6] instanceof Solid);
    }
         /**
     * make sure explosion finish and grid will be empty
     */
    @Test
    public void resetExplosion(){
        grid[5][6] = new Broken(160,192,this.app.loadImage("src/main/resources/broken/broken.png"));
        grid[5][7] = new Broken(160,224,this.app.loadImage("src/main/resources/broken/broken.png"));
        setFrameExplode(this.bomb,grid);
        boolean result = this.bomb.explode(app,grid,db.getBg());
        refreshExplode(6,5,this.bomb,this.grid);
        refreshExplode(7,5,this.bomb,this.grid);
        assertNull(grid[5][6]);
        assertNull(grid[5][7]);


    }
         /**
     * fail to explode when it has not reach 8 frames
     */
    @Test
    public void notExplode(){
        grid[5][6] = new Broken(160,192,this.app.loadImage("src/main/resources/broken/broken.png"));
        grid[5][7] = new Broken(160,224,this.app.loadImage("src/main/resources/broken/broken.png"));
    
        boolean result = this.bomb.explode(app,grid,db.getBg());
        assertFalse(result);
        assertNotNull(grid[5][6]);
        assertNotNull(grid[5][7]);


    }
         /**
     * private method to trigger 8 frames
     */
    private void setFrameExplode(Bomb bomb, Block[][] grid){
        for (int i =0 ; i<8;i++){
            bomb.render(this.app);
        }
    }
         /**
     * private method to trigger 30 frames 
     */
    private void refreshExplode(int x, int y,Bomb bomb, Block[][] grid){
        for (int i =0 ; i<30;i++){
            bomb.tick(x,y, grid);
            
        }
    }


}