package WizardTD.Towers;

import WizardTD.*;
import WizardTD.Monsters.*;
import WizardTD.Tiles.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;


public class FireballTest {

    private Fireball fireball;

    private Monster testMonster;

    private App test_p;


    @BeforeEach
    public void setUp() {
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);

        List<Tile> road = new ArrayList<>();
        path tile1 = new path(32, 0, test_p, 0, 0, false);
        path tile2 = new path(64, 0, test_p, 0, 0, false);
        road.add(tile1);
        road.add(tile2);

        testMonster = new Monster(1, 5, 100.0, 2.0, test_p, road);

        fireball = new Fireball(32,32,testMonster,10, test_p);

        fireball.getPosition();
    }

    @Test
    public void testGet_damage(){
        assertEquals(10,fireball.getDamage());
    }

    @Test
    public void testGet_TimeMultiplier(){
        assertEquals(1.0f,fireball.getTTimeMultiplier());
    }



    @Test
    public void testFireballMovement() {
        PVector initialPosition = new PVector(32, 32);
        fireball.move();
        assertNotEquals(initialPosition, fireball.getPosition(), "Fireball should move from its initial position.");
    }

    @Test
    public void testFireballMovementWhenMonsterDead() {
        // 让怪物受到大量伤害，使其血量变为0或更小
        testMonster.damage(200);

        PVector initialPosition = fireball.getPosition().copy();

        fireball.move();

        assertEquals(initialPosition, fireball.getPosition(), "Fireball should not move when the target monster is dead.");
        assertFalse(fireball.isActive(), "Fireball should become inactive when the target monster is dead.");
    }


}