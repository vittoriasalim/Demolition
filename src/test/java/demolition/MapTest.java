package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;
import processing.core.PImage;


public class MapTest {
    private App app;
     /**
     * Setup app and other classes to test the functionality of database
     */
    @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.noLoop();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);        
    }
     /**
     * Testing getter method of the map 
     */
    @Test
    public void testLoadMapGetter(){
        Map map= new Map();
        map.setGame(this.app,0,"src/test/resources/config.json");
        assertTrue(map.totalLvl()!=0);
        assertNotNull(map.getBlock());
        assertNotNull(map.getBg());
    }

  


}