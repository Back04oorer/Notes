package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BeetleTest {
    private Beetle beetle;
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

        beetle = new Beetle(10, 5, 100, 2, test_p, dummyPath);
    }


    @Test
    public void testDeath() {
        beetle.death();
        beetle.death();
        assertTrue(beetle.isDead());
        assertEquals(0, beetle.getDeathFrameCount());
        assertEquals(0, beetle.getCurrentStatus());
    }

    @Test
    public void testRadius() {
        assertEquals(10.0, beetle.getRadius(), 0.001);
    }

    @Test
    public void testDisolayImage(){
        beetle.display_image();
        assertEquals(0, beetle.getDeathFrameCount());
        assertEquals(0, beetle.getCurrentStatus());
    }
}

