package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WormTest {
    private Worm worm;
    private Tile dummyTile;
    private PApplet test_p;

    @BeforeEach
    public void setUp() {
        List<Tile> dummyPath = new ArrayList<>();
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"},test_p);
        test_p.setup();
        test_p.delay(1000);
        dummyTile = new Tile(0, 0, test_p , false) { };  // 创建一个匿名子类的Tile实例
        dummyPath.add(dummyTile);

        worm = new Worm(10, 5, 100, 2, test_p, dummyPath);
    }


    @Test
    public void testDeathNormal() {
        worm.death();
        assertTrue(worm.isDead());
        assertEquals(0, worm.getDeathFrameCount());
        assertEquals(0, worm.getCurrentStatus());
    }

    @Test
    public void testDeath() {
        worm.death();
        worm.death();
        assertTrue(worm.isDead());
        assertEquals(0, worm.getDeathFrameCount());
        assertEquals(0, worm.getCurrentStatus());
    }

    @Test
    public void testRadius() {
        assertEquals(10.0, worm.getRadius(), 0.001);
    }


    @Test
    public void testDisolayImage(){
        worm.display_image();
        assertEquals(0, worm.getDeathFrameCount());
        assertEquals(0, worm.getCurrentStatus());
    }
}

