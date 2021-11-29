package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;


public class PlayerTest {
    private App app;
    private Player player;
     /**
     * Setup app and other classes to test the functionality of player
     */
    @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.noLoop();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);

        this.player = new BombGuy(32,32,this.app.loadImage("src/main/resources/player/player1.png"));
        
    }
     /**
     * testign rendering or changing the sprite of player and enemy every frames 
     */
    @Test
    public void testRender(){
        for (int i = 0 ; i<100;i++){
            Database db = new Database();
            this.player.tick(1,1, db.getGrid());
            this.player.renderDirection(app);
            assertFalse(player.getSprite()==this.app.loadImage("src/main/resources/player/player1.png"));
        }
    
    }

}