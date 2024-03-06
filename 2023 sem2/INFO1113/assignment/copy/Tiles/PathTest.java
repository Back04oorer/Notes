package WizardTD.Tiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processing.core.PApplet;
import processing.core.PImage;
import WizardTD.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PathTest {

    private PApplet test_p;
    private path testPath;

    @BeforeEach
    public void setUp() {
        List<Tile> dummyPath = new ArrayList<>();
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);
    }



    @Test
    public void testPathCreationWithDifferentTypes() {
        int x = 0;
        int y = 0;
        boolean can_place_tower = true;

        // 测试type = 0
        testPath = new path(x, y, test_p, 0, 90, can_place_tower);
        assertNotNull(testPath, "Path object with type 0 should not be null after creation.");

        // 测试type = 1
        testPath = new path(x, y, test_p, 1, 90, can_place_tower);
        assertNotNull(testPath, "Path object with type 1 should not be null after creation.");

        // 测试type = 2
        testPath = new path(x, y, test_p, 2, 90, can_place_tower);
        assertNotNull(testPath, "Path object with type 2 should not be null after creation.");

        // 测试type = 3
        testPath = new path(x, y, test_p, 3, 90, can_place_tower);
        assertNotNull(testPath, "Path object with type 3 should not be null after creation.");

        // 测试type = 4
        testPath = new path(x, y, test_p, 4, 90, can_place_tower);
        assertNull(testPath.pathImage);
    }

}

