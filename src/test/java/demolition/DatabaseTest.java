package demolition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import java.util.ArrayList;

public class DatabaseTest {
    private App app;
    private Database db;
    private Block[][] grid;
    /**
     * setup app and other classes to test the functionality of database
     */
    @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.noLoop();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);

        this.db = new Database();
        this.grid = db.getGrid();
     
    }
    /**
     * test drawing and loading blocks
     */
    @Test
    public void drawAndLoadBlocks(){
        Bomb bomb = new Bomb(160,160,this.app.loadImage("src/main/resources/bomb/bomb.png"));
        BombGuy bombGuy = new BombGuy(192,192,this.app.loadImage("src/main/resources/player/player1.png"));
        grid[6][6] = bombGuy;
        grid[5][5] =bomb;
        grid[1][1]= new Bomb(32,32,this.app.loadImage("src/main/resources/bomb/bomb.png"));
        ArrayList<Block> bg = db.getBg();
      
        bg.add(bomb);
        db.drawBackground(this.app);
        db.drawBlock(this.app);
        assertNotNull(grid[6][6]);
        assertFalse(bg.isEmpty());
    }
        /**
     * testing triggering key pressed to move bomb guy
     */

    @Test
    public void setBGuyDirectionLeft(){
        
        BombGuy bombGuy = new BombGuy(192,192,this.app.loadImage("src/main/resources/player/player1.png"));
        
        this.grid[6][6] = bombGuy;
        db.setDirection(37);
        db.drawBlock(this.app);
        assertTrue(this.grid[6][5] instanceof BombGuy);
    
    }
        /**
     * testing triggering key pressed to move bomb guy to the right
     */
    @Test
    public void setDirectionRight(){
        
        BombGuy bombGuy = new BombGuy(192,192,this.app.loadImage("src/main/resources/player/player1.png"));
        this.grid[6][6] = bombGuy;
        db.setDirection(39);
        db.drawBlock(this.app);
        assertTrue(this.grid[6][7] instanceof BombGuy);
    
    }
        /**
     * testing trigerring key pressed to move bomb guy to the up
     */
    @Test
    public void setBGuyDirectionUp(){
        
        BombGuy bombGuy = new BombGuy(192,192,this.app.loadImage("src/main/resources/player/player1.png"));
        
        this.grid[6][6] = bombGuy;
        db.setDirection(38);
        db.drawBlock(this.app);
        assertTrue(this.grid[5][6] instanceof BombGuy);
    
    }
        /**
     * testing triggering key pressed to move bomb guy to the down
     */
    @Test
    public void setBGuyDirectionDown(){
        
        BombGuy bombGuy = new BombGuy(192,192,this.app.loadImage("src/main/resources/player/player1.png"));
        this.grid[6][6] = bombGuy;
        db.setDirection(40);
        db.drawBlock(this.app);
        assertTrue(this.grid[7][6] instanceof BombGuy);
    
    }
        /**
     * set key pressed where bomb guy able to load bomb
     */
    @Test
    public void setBGuyBomb(){
        
        BombGuy bombGuy = new BombGuy(192,192,this.app.loadImage("src/main/resources/player/player1.png"));
        
        this.grid[6][6] = bombGuy;
        db.setDirection(32);
        
        db.drawBlock(this.app);
        assertNotNull(db.getBg());
    
    }
    @Test
    public void setLoadBomb(){
        BombGuy bombGuy = new BombGuy(192,192,this.app.loadImage("src/main/resources/player/player1.png"));
        this.grid[6][6] = bombGuy;
        db.setDirection(40);
        assertNotNull(db.getBg());


    }
    
}
