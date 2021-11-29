package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;



public class BlockTest {
    private App app;
    private Block blocks;
    /**
     * Setup map and block object
     */
    @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        this.blocks = new BombGuy(32,32,app.loadImage("src/main/resources/player/player1.png")); 
    }
    /**
     * check if getter method return expected value of x and y
     */
    @Test
    public void testGetter(){
        assertTrue(this.blocks.getX()==32);
        assertTrue(this.blocks.getY()==32+64-15);
    }
    /**
     * test if sprite can be changed to other sprite or to be rendered
     */
    @Test
    public void testSprite(){
        PImage sprite = this.app.loadImage("src/main/resources/player/player1.png");
        this.blocks.setSprite(sprite);
        assertTrue(sprite==this.blocks.getSprite());
    }
    

}
  