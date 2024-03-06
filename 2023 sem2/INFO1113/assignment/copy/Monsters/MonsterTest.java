package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Tiles.Tile;
import WizardTD.Tiles.path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {
    private Monster monster;

    private PApplet test_p;
    private path tile1;
    private path tile2;

    private List<Tile> road ;

    @BeforeEach
    public void setUp() {

        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"},test_p);
        test_p.setup();
        test_p.delay(1000);

        road = new ArrayList<>();
        tile1 = new path(0,0, test_p,0,0,false);
        tile2 = new path(32,0, test_p,0,0,false);
        road.add(tile1);
        road.add(tile2);


        monster = new Monster(0.5,10,100,1,test_p,road);
    }

    @Test
    public void testGetRadius(){
        assertEquals(10,monster.getRadius());
    }

    @Test
    public void testDisplayImage(){
        monster.display_image();
        assertEquals(0,monster.getCurrentStatus());
    }
    
    @Test
    public void testHasfinishAnime(){
        assertFalse(monster.hasDeathAnimationFinished());

        monster.isDead = false;
        monster.current_status = 1;
        assertFalse(monster.hasDeathAnimationFinished());

        monster.isDead = true;
        monster.current_status = 0;
        assertFalse(monster.hasDeathAnimationFinished());

        monster.isDead = true;
        monster.current_status = 1;
        assertTrue(monster.hasDeathAnimationFinished());

    }


}

