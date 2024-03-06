package WizardTD;

import WizardTD.App;
import WizardTD.Manager;
import WizardTD.Tiles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WizardHouseTest {
    private App test_p;
    private Wizard_house wizardHouse;
    private ConfigLoader configLoader;
    @BeforeEach
    public void setUp() {
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);
        configLoader = new ConfigLoader("test_config1.json",test_p);
        wizardHouse = new Wizard_house(32,32,configLoader,test_p);
    }


    //Lose mana
    @Test
    public void test_lose_mana(){
        wizardHouse.lose_mana(10);
        assertEquals(90,wizardHouse.getMana());

        wizardHouse.lose_mana(200);
        assertEquals(0,wizardHouse.getMana());
    }

    //Add mana
    @Test
    public void test_add_mana(){
        wizardHouse.add_Mana(800);
        assertEquals(900,wizardHouse.getMana());

        //Cap
        wizardHouse.add_Mana(800);
        assertEquals(1000,wizardHouse.getMana());
    }

    @Test
    public void test_set_time_mutiplier(){
        wizardHouse.setTimeMultiplier(2);
        assertEquals(2,wizardHouse.timeMultiplier);
    }

    //Default add
    @Test
    public void test_add_mana_default(){
        wizardHouse.add_Mana(1000);
        wizardHouse.add_mana_default();
        assertEquals(1000,wizardHouse.getMana());
    }

    //Test for mana spell
    @Test
    public void test_castManaSpell(){
        //not enough mana
        wizardHouse.lose_mana(50);
        wizardHouse.castManaPoolSpell();
        assertEquals(0,wizardHouse.spell_cast_count);

        wizardHouse.add_Mana(1000);
        wizardHouse.castManaPoolSpell();
        assertEquals(1,wizardHouse.spell_cast_count);
    }


}