package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import processing.core.PApplet;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;


public class AppTest {
    private App app;
    private Database db;
    /**
     * Setting up app class before executing
     */
   @BeforeEach
    public void setUp(){
        this.app = new App();
        this.app.setConfig("src/test/resources/config.json");
        PApplet.runSketch(new String[] {"App"}, app);
        this.app.noLoop();
        this.db= new Database();
    }
    /**
     * Test key pressed 
     */

    @Test
    public void keyTest(){
        app.keyCode = 37;
        app.keyPressed();     
        assertTrue(app.keyCode==37);
        app.keyCode = 39;
        app.keyPressed();     
        assertTrue(app.keyCode==39);
        app.keyCode = 38;
        app.keyPressed();     
        assertTrue(app.keyCode==38);
        app.keyCode = 40;
        app.keyPressed();     
        assertTrue(app.keyCode==40);
        app.keyCode = 32;
        app.keyPressed();     
        assertTrue(app.keyCode==32);
    }
    /**
     * Check if image is loaded in database array and map
     */
    @Test
    public void testLoadImage(){
        assertNotNull(this.db.getGrid());
        assertNotNull(this.db.getBg());
    }


}
