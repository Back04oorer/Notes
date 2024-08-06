package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GremlinTest {
    private Gremlin gremlin;
    private Tile dummyTile;
    private App test_p;

    @BeforeEach
    public void setUp() {
        List<Tile> dummyPath = new ArrayList<>();
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"},test_p);
        test_p.setup();
        test_p.delay(1000);

        dummyTile = new Tile(0, 0, test_p, false) {
        };  // 创建一个匿名子类的Tile实例
        dummyPath.add(dummyTile);

        gremlin = new Gremlin(10.0, 5, 100.0, 2.0, test_p, dummyPath);
    }

    @Test
    public void testDeath() {
        gremlin.death();
        gremlin.death();
        assertTrue(gremlin.isDead());
        assertEquals(0, gremlin.getDeathFrameCount());
        assertEquals(0, gremlin.getCurrentStatus());
    }



    @Test
    public void testDisplayImage1() {
        gremlin.death();
        gremlin.display_image();
        assertEquals(1, gremlin.getDeathFrameCount());
        assertEquals(0, gremlin.getCurrentStatus());
    }

    @Test
    public void testDisplayImage2() {
        gremlin.display_image();
        assertEquals(0, gremlin.getDeathFrameCount());
        assertEquals(0, gremlin.getCurrentStatus());
    }

    @Test
    public void testDisplayImage3() {
        gremlin.death();
        for (int i = 0; i < 16; i++) {
            gremlin.display_image();
        }
        assertEquals(16, gremlin.getDeathFrameCount());
        assertEquals(4, gremlin.getCurrentStatus());

        gremlin.display_image();
        assertEquals(16, gremlin.getDeathFrameCount());
        assertEquals(4, gremlin.getCurrentStatus());

    }


}