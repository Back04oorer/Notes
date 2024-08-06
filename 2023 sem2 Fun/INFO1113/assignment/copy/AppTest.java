package WizardTD;

import WizardTD.Monsters.Monster;
import WizardTD.Tiles.Tile;
import WizardTD.Tiles.path;
import WizardTD.Towers.Fireball;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class AppTest {
    private App test_p;


    @BeforeEach
    public void setUp() {
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);

    }

    @Test
    public void Test_key_press(){
        assertTrue(test_p.isLooping());

        test_p.key = 'R';
        test_p.keyPressed();
        assertTrue(test_p.isLooping());

        test_p.noLoop();
        assertFalse(test_p.isLooping());

        test_p.key = 'c';
        test_p.keyPressed();
        assertFalse(test_p.isLooping());

        test_p.key = 'r';
        test_p.keyPressed();
        assertTrue(test_p.isLooping());


    }

    @Test
    public void test_reverse_pause(){
        test_p.reversePaused();
        assertTrue(test_p.isPaused);
        test_p.reversePaused();
        assertFalse(test_p.isPaused);
    }

    @Test
    public void test_key_release(){
        test_p.key= 'm';
        test_p.keyPressed();
        test_p.keyReleased();
        assertNotEquals(200,test_p.manager.wizardHouse.getMana());
    }
    @Test
    public void test_draw(){
        test_p.manager.lose_mana_for_test(2000000);
        test_p.draw();
        assertFalse(test_p.isLooping());

    }

    @Test
    public void test_drawButtons1(){
        test_p.key = 'f';
        test_p.keyPressed();
        test_p.drawButtons();
        test_p.manager.createTowerAtPosition(33,40);
        test_p.manager.setTimeMultiplier(2);
        assertEquals(2.0,test_p.manager.Towers.get(0).timeMultiplier);
    }





}