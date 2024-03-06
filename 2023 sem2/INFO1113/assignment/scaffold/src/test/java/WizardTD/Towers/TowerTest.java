package WizardTD.Towers;

import WizardTD.*;
import WizardTD.Monsters.*;
import WizardTD.Tiles.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class TowerTest {
    private App test_p;
    private Tower testTower1;

    private Tower testTower2;

    private List<Monster> monsters;

    private path tile1;
    private path tile2;

    private List<Tile> road ;

    @BeforeEach
    public void setUp() {
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);

        road = new ArrayList<>();
        tile1 = new path(0,0, test_p,0,0,false);
        tile2 = new path(32,0, test_p,0,0,false);
        road.add(tile1);
        road.add(tile2);

        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower2 = new Tower(32, 32, 1, 1, 50, test_p);
        monsters = new ArrayList<>();
        monsters.add(new Monster(0.5, 120, 100,1,test_p,road));// 其他参数根据 Monster 类的构造方法来填写

    }

    @Test
    public void testInitialAttributes() {
        assertEquals(1,testTower1.getFiringSpeed());
        assertEquals(50,testTower1.getDamage());
    }

    @Test
    public void testFindNearestMonster() {
        Monster nearest = testTower1.findNearestMonster(monsters);
        assertEquals(6, nearest.getX());
        assertEquals(6, nearest.getY());

        Monster nullMonster = testTower2.findNearestMonster(monsters);
        assertNull(nullMonster);
    }


    @Test
    public void testUpgrade() {
        testTower1.upgrade("test");
        assertEquals(100, testTower1.getRange());

        testTower1.upgrade("range");

        assertEquals(132, testTower1.getRange());
        testTower1.upgrade("speed");
        assertEquals(1.5, testTower1.getFiringSpeed());
        testTower1.upgrade("damage");
        assertEquals(75, testTower1.getDamage());
    }

    @Test
    public void testUpgradeCount() {
        assertEquals(0, testTower1.getR());
        assertEquals(0, testTower1.getD());
        assertEquals(0, testTower1.getS());

        testTower1.upgrade("range");
        assertEquals(1, testTower1.getR());

        testTower1.upgrade("damage");
        assertEquals(1, testTower1.getD());

        testTower1.upgrade("speed");
        assertEquals(1, testTower1.getS());
    }

    @Test
    public void testUpdateStatusAfterUpgrade() {
        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        assertEquals("src/main/resources/WizardTD/tower1.png", testTower1.getImagePath());

        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        assertEquals("src/main/resources/WizardTD/tower2.png", testTower1.getImagePath());
    }


    @Test
    public void testRender1() {

        // 1. Test for only R < 2

        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("damage");
        testTower1.upgrade("speed");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        int[] results = testTower1.render();
        assertEquals(1, results[0]);
        assertEquals(0, results[1]);
        assertEquals(1, results[2]);

        // 2. Test for only D < 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("damage");
        testTower1.upgrade("range");
        testTower1.upgrade("range");
        testTower1.upgrade("speed");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(1, results[0]);
        assertEquals(1, results[1]);
        assertEquals(0, results[2]);

        // 3. Test for only S < 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("speed");
        testTower1.upgrade("range");
        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("damage");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(0, results[0]);
        assertEquals(1, results[1]);
        assertEquals(1, results[2]);

        // 4. Test for R and D < 2, but S >= 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("speed");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(1, results[0]);
        assertEquals(0, results[1]);
        assertEquals(0, results[2]);

        // 5. Test for R and S < 2, but D >= 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("range");
        testTower1.upgrade("speed");
        testTower1.upgrade("damage");
        testTower1.upgrade("damage");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(0, results[0]);
        assertEquals(0, results[1]);
        assertEquals(1, results[2]);

        // 6. Test for D and S < 2, but R >= 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("damage");
        testTower1.upgrade("speed");
        testTower1.upgrade("range");
        testTower1.upgrade("range");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(0, results[0]);
        assertEquals(1, results[1]);
        assertEquals(0, results[2]);

        // 7. Test for R, D, and S < 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(0, results[0]);
        assertEquals(0, results[1]);
        assertEquals(0, results[2]);

        // Test for S < 1, R and D >= 1
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(0, results[0]);
        assertEquals(1, results[1]);
        assertEquals(1, results[2]);

        // Test for R < 1, S and D >= 1
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("speed");
        testTower1.upgrade("damage");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(1, results[0]);
        assertEquals(0, results[1]);
        assertEquals(1, results[2]);

        // Test for D < 1, R and S >= 1
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("range");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(1, results[0]);
        assertEquals(1, results[1]);
        assertEquals(0, results[2]);

        // Test for S < 2, R and D >= 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("range");
        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("damage");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(0, results[0]);
        assertEquals(2, results[1]);
        assertEquals(2, results[2]);

        // Test for R, D, S >= 2
        testTower1 = new Tower(32, 32, 100, 1, 50, test_p);
        testTower1.upgrade("range");
        testTower1.upgrade("range");
        testTower1.upgrade("damage");
        testTower1.upgrade("damage");
        testTower1.upgrade("speed");
        testTower1.upgrade("speed");
        testTower1.updateStatus();
        results = testTower1.render();
        assertEquals(0, results[0]);
        assertEquals(0, results[1]);
        assertEquals(0, results[2]);
    }




}
