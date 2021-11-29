package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;


public class EnemyTest {
    private App app;
    private Enemy enemy;
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
        this.grid[5][5]= this.enemy;
    }
    /**
     * When not able to move x axis
     */
    @Test
    public void moveFalseXTest(){
        
        this.enemy.moveX(5, 5,0);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[5][5] instanceof YellowEnemy);
    }
    /**
     * When not able to move y axis
     */
    @Test
    public void moveFalseYTest(){
        this.enemy.moveY(5, 5,0);
        set60seconds(this.enemy,5,5);
        assertTrue(this.grid[5][5] instanceof YellowEnemy);
    }

    private void set60seconds(Enemy enemy, int x,int y){
        for (int i =0 ; i < 60;i++){//move every 60 frames
            enemy.tick(y,x,this.grid);
        }
    }
}