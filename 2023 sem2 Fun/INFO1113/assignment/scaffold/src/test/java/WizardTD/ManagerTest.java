package WizardTD;

import WizardTD.Monsters.Gremlin;
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



public class ManagerTest {
    private App test_p;

    private Manager test_manager;

    private Manager Inf_test_manager;
    path tile1;
    path tile2;
    List<Tile> road ;

    @BeforeEach
    public void setUp() {
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);

        test_manager = new Manager(test_p,"test_config1.json");
        test_manager.CreateGameBoard();
        test_manager.CreateWizardHouse();

        Inf_test_manager = new Manager(test_p,"test_config2.json");
        Inf_test_manager.CreateGameBoard();
        Inf_test_manager.CreateWizardHouse();
    }


    //Test for Create Towers
    @Test
    public void testCreate_tower(){
        //Not valid situations
        //invalid position
        test_manager.createTowerAtPosition(0,40);
        assertEquals(0,test_manager.Towers.size());

        //invalid position
        test_manager.createTowerAtPosition(1000,1000);
        assertEquals(0,test_manager.Towers.size());

        //not enough mana
        test_manager.lose_mana_for_test(200);
        test_manager.createTowerAtPosition(32,40);
        assertEquals(0,test_manager.Towers.size());

        //positive
        test_manager.add_mana_for_test(200);
        test_manager.createTowerAtPosition(32,40);
        assertEquals(32,test_manager.Towers.get(0).getX());
        assertEquals(40,test_manager.Towers.get(0).getY());

        //Already have a tower
        test_manager.createTowerAtPosition(32,42);
        assertEquals(1,test_manager.Towers.size());
        assertEquals(32,test_manager.Towers.get(0).getX());
        assertEquals(40,test_manager.Towers.get(0).getY());
    }


    //Verify the win
    @Test
    public void testHasPlayerWon() {
        // 1. Normal mode, current_wave is less than total waves
        test_manager.setMode("Normal");
        test_manager.set_current_wave(1); // Assuming there are more than 1 wave
        assertFalse(test_manager.hasPlayerWon());

        // 1. Normal mode, current_wave is less than total waves
        test_manager.Monsters.clear();
        test_manager.set_current_wave(1000); // Assuming there are more than 1 wave
        assertTrue(test_manager.hasPlayerWon());


        // 2. Normal mode, current_wave = 0 and Monsters is empty
        test_manager.Monsters.clear();
        assertTrue(test_manager.hasPlayerWon());

        // 3. Not in Normal mode
        test_manager.setMode("Infinite");
        assertFalse(test_manager.hasPlayerWon());
    }


    //Upgrade speed
    @Test
    public void test_upgrade_speed(){

        test_manager.createTowerAtPosition(33,40);

        //invalid positions(negative)
        test_manager.upgrade_speed(34,41);
        test_manager.add_mana_for_test(200);
        test_manager.upgrade_speed(0,1000);
        test_manager.upgrade_speed(34,1000);
        test_manager.upgrade_speed(-10,41);
        test_manager.upgrade_speed(10,41);
        test_manager.upgrade_speed(34,0);

        assertEquals(0,test_manager.Towers.get(0).getS());

        //positive testcase
        test_manager.add_mana_for_test(500);
        test_manager.upgrade_speed(34,41);
        assertEquals(1,test_manager.Towers.get(0).getS());
    }

    //Upgrade Damage
    @Test
    public void test_upgrade_damage(){
        test_manager.createTowerAtPosition(33,40);

        //invalid positions(negative)
        test_manager.upgrade_damage(34,41);
        test_manager.add_mana_for_test(200);
        test_manager.upgrade_damage(0,1000);
        test_manager.upgrade_damage(34,1000);
        test_manager.upgrade_damage(-10,41);
        test_manager.upgrade_damage(10,41);
        test_manager.upgrade_damage(34,0);
        assertEquals(0,test_manager.Towers.get(0).getD());

        //positive testcase
        test_manager.add_mana_for_test(500);
        test_manager.upgrade_damage(34,41);
        assertEquals(1,test_manager.Towers.get(0).getD());
    }

    //Upgrade Range
    @Test
    public void test_upgrade_range(){
        test_manager.createTowerAtPosition(33,40);

        //invalid positions(negative)
        test_manager.upgrade_range(34,41);
        test_manager.add_mana_for_test(200);
        test_manager.upgrade_range(0,1000);
        test_manager.upgrade_range(34,1000);
        test_manager.upgrade_range(-10,41);
        test_manager.upgrade_range(10,41);
        test_manager.upgrade_range(34,0);

        assertEquals(0,test_manager.Towers.get(0).getR());

        //positive testcase
        test_manager.add_mana_for_test(500);
        test_manager.upgrade_range(34,41);
        assertEquals(1,test_manager.Towers.get(0).getR());
    }


    //Simulate real loops in Normal Situation
    @Test
    public void test_loop_Normal1(){

        int FRAMES_PER_SECOND = 60;
        int TOTAL_DURATION_SECONDS = 10;
        final int TOTAL_FRAMES = FRAMES_PER_SECOND * TOTAL_DURATION_SECONDS;
        final long FRAME_DURATION_MILLIS = 1000L / FRAMES_PER_SECOND;

        assertEquals(0,test_manager.Monsters.size());
        test_manager.add_mana_for_test(10000);
        test_manager.createTowerAtPosition(32 * 13,40 + 32 );

        road = new ArrayList<>();
        tile1 = new path(32 * 14,40 + 32, test_p,0,0,false);
        tile2 = new path(32 * 14,40 + 64, test_p,0,0,false);
        road.add(tile1);
        road.add(tile2);

        Monster monster = new Monster(0.5,10,100,1,test_p,road);
        test_manager.Monsters.add(monster);

        assertEquals(1,test_manager.Towers.size());

        //Simulate loop by frame
        for (int frame = 1; frame <= TOTAL_FRAMES; frame++) {
            test_manager.loops();
            try {
                Thread.sleep(FRAME_DURATION_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertEquals(4,test_manager.Monsters.size());
        assertEquals(0,test_manager.fireballs.size());

    }

    //Test for time multiplier
    @Test
    public void test_Set_time_multiplier(){
        test_manager.createTowerAtPosition(32 * 13,40 + 32 );

        road = new ArrayList<>();
        tile1 = new path(0,0, test_p,0,0,false);
        tile2 = new path(32,0, test_p,0,0,false);
        road.add(tile1);
        road.add(tile2);

        Monster monster = new Monster(0.5,10,100,1,test_p,road);
        Fireball fireball = new Fireball(32,32,monster,100,test_p);
        test_manager.fireballs.add(fireball);

        test_manager.setTimeMultiplier(2);

        assertEquals(2.0,test_manager.Towers.get(0).timeMultiplier);
        assertEquals(2.0,test_manager.fireballs.get(0).getTTimeMultiplier());

    }

    //Test for showing Tower
    @Test
    public void testShowTower(){
        test_manager.createTowerAtPosition(32,42);
        boolean[] testSelectedButton = new boolean[7];

        test_manager.showTower_range(0,0,testSelectedButton);
        test_manager.showTower_range(33,42,testSelectedButton);
        test_manager.showTower_range(0,42,testSelectedButton);
        test_manager.showTower_range(89,42,testSelectedButton);
        test_manager.showTower_range(33,100,testSelectedButton);
        test_manager.showTower_range(33,0,testSelectedButton);

        assertEquals(20,20 + test_manager.Towers.get(0).getS() * 10);

        test_manager.add_mana_for_test(100);
        test_manager.upgrade_speed(34,42);
        test_manager.Towers.get(0).updateStatus();

        testSelectedButton[3] = true;
        testSelectedButton[4] = true;
        testSelectedButton[5] = true;

        test_manager.showTower_range(0,0,testSelectedButton);
        test_manager.showTower_range(33,42,testSelectedButton);
        test_manager.showTower_range(0,42,testSelectedButton);
        test_manager.showTower_range(89,42,testSelectedButton);
        test_manager.showTower_range(33,100,testSelectedButton);
        test_manager.showTower_range(33,0,testSelectedButton);
        assertEquals(30,20 + test_manager.Towers.get(0).getS() * 10);

    }

    //Test displaying of Wave message in Normal/Infinite mode
    @Test
    public void testDisplayWaveInfoNormal() {
        test_manager.setMode("Normal");
        test_manager.set_current_wave(0);
        test_manager.set_framesSinceLastWave(10);

        String result = test_manager.displayWaveInfo(0, 0);
        assertTrue(result.startsWith("Wave 1 in:")); // make sure starts from wave 1 in Normal mode
        test_manager.set_current_wave(3);
        result = test_manager.displayWaveInfo(0, 0);
        assertTrue(result.startsWith("Wave 4 in:")); // make sure wave counts is right


        Inf_test_manager.setMode("Infinite");
        Inf_test_manager.set_current_wave(0);

        result = Inf_test_manager.displayWaveInfo(0, 0);
        assertTrue(result.startsWith("Wave 2 in:")); // in Infinite , displaying starts from wave 2

        Inf_test_manager.set_current_wave(3);
        result = Inf_test_manager.displayWaveInfo(0, 0);
        assertTrue(result.startsWith("Wave 5 in:"));
    }

    @Test
    public void testSetmode(){
        Inf_test_manager.setMode("Infinite");
        assertEquals("Infinite",Inf_test_manager.mode);
    }


    //Test monster spawning at the beginning of a wave(Infinite)
    @Test
    public void testSpawnMonsters_Inf_newWave() {
        long initialWavePauseFrames =  (((long) Inf_test_manager.WAVE_PAUSE_TIME * App.FPS) / Inf_test_manager.timeMultiplier);

        Inf_test_manager.set_framesSinceLastWave(initialWavePauseFrames);
        Inf_test_manager.SpawnMonsters_Inf();

        assertEquals(1, Inf_test_manager.current_wave); // Expecting the wave to be incremented
    }


    //Test monster spawning in a wave(Infinite)
    @Test
    public void testSpawnMonsters_Inf_monsterSpawnFrequency() {
        long initialSpawnFrames = (((long) (Inf_test_manager.SPAWN_TIME_EACH_WAVE / Inf_test_manager.Monster_quantity) * App.FPS) / Inf_test_manager.timeMultiplier);

        Inf_test_manager.set_framesSinceLastMonsterSpawn(initialSpawnFrames);
        Inf_test_manager.SpawnMonsters_Inf();
        assertEquals(1, Inf_test_manager.Monsters.size()); // Expecting one monster to be spawned
    }

    @Test
    public void testSpawnMonsters_Inf_monsterAttributes() {
        long initialSpawnFrames = (((long) (Inf_test_manager.SPAWN_TIME_EACH_WAVE / Inf_test_manager.Monster_quantity) * App.FPS) / Inf_test_manager.timeMultiplier);

        Inf_test_manager.set_framesSinceLastMonsterSpawn(initialSpawnFrames);
        Inf_test_manager.SpawnMonsters_Inf();

        Monster spawnedMonster = Inf_test_manager.Monsters.get(0);
        assertTrue(spawnedMonster.getCurrent_hp() >=Inf_test_manager.Inf_HP - 10 && spawnedMonster.getCurrent_hp() <= Inf_test_manager.Inf_HP + 10); // Checking HP range
    }

    //Simulate real situation for Infinite loop
    @Test
    public void testLoopInf(){
        Inf_test_manager.setMode("Infinite");

        int FRAMES_PER_SECOND = 60;
        int TOTAL_DURATION_SECONDS = 2;
        final int TOTAL_FRAMES = FRAMES_PER_SECOND * TOTAL_DURATION_SECONDS;
        final long FRAME_DURATION_MILLIS = 1000L / FRAMES_PER_SECOND;

        assertEquals(0,Inf_test_manager.Monsters.size());
        Inf_test_manager.add_mana_for_test(10000);
        Inf_test_manager.createTowerAtPosition(32 * 13,40 + 32 );

        road = new ArrayList<>();
        tile1 = new path(32 * 14,40 + 32, test_p,0,0,false);
        tile2 = new path(32 * 14,40 + 64, test_p,0,0,false);
        road.add(tile1);
        road.add(tile2);

        Monster monster = new Gremlin(0.5,10,100,1,test_p,road);
        Inf_test_manager.Monsters.add(monster);

        for (int frame = 1; frame <= TOTAL_FRAMES; frame++) {
            Inf_test_manager.loops();
            try {
                Thread.sleep(FRAME_DURATION_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Inf_test_manager.Monsters.get(0).damage(100000);
        Inf_test_manager.loops();
        Inf_test_manager.loops();
        Inf_test_manager.loops();
        Inf_test_manager.loops();
        Inf_test_manager.loops();

        assertEquals(2,Inf_test_manager.Monsters.size());
        assertEquals(0,Inf_test_manager.fireballs.size());

    }
}
