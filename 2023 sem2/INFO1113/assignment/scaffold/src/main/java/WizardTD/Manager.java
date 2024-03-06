package WizardTD;

import WizardTD.Monsters.*;
import WizardTD.Towers.*;
import WizardTD.Tiles.*;
import processing.core.PApplet;
import java.util.*;

public class Manager{
    public PApplet p;
    public List<Monster> Monsters;
    public List<Tower> Towers;
    public Wizard_house wizardHouse;
    public GameBoard gameBoard;
    public List<Fireball> fireballs;
    private final ConfigLoader configLoader;

    private int currentMonsterIndex = 0;
    int current_wave = 0;
    int Monster_quantity;

    //for infinite mode
    double QUANTITY_INCREMENT_RATE,HP_INCREMENT_RATE,VOID_MONSTER_PROBABILITY,Inf_HP,INITIAL_SPEED,SPEED_RANDOM_RANGE,
            MANA_GAIN_ON_KILL      ;
    int SPAWN_TIME_EACH_WAVE,WAVE_PAUSE_TIME ;

    int timeMultiplier;
    public String mode;


    public Manager(PApplet p, String configpath) {
        this.p = p;
        this.Monsters = new ArrayList<>();
        this.Towers = new ArrayList<>();
        this.fireballs = new ArrayList<>();
        this.configLoader = new ConfigLoader(configpath, this.p);

        this.QUANTITY_INCREMENT_RATE = configLoader.getInf_Quantity_incremental_rate_from_1();
        this.HP_INCREMENT_RATE = configLoader.getInf_HP_incremental_rate_from_1();
        this.VOID_MONSTER_PROBABILITY = configLoader.getInf_VoidMonster_probability();
        this.Inf_HP = configLoader.getInf_hp_initial();
        this.INITIAL_SPEED = configLoader.getInf_speed_initial();
        this.SPEED_RANDOM_RANGE = configLoader.getInf_speed_random_range();
        this.MANA_GAIN_ON_KILL = configLoader.getInf_mana_gain_on_kill_initial();
        this.SPAWN_TIME_EACH_WAVE = (int) configLoader.getInf_spawn_time_each_wave();
        this.WAVE_PAUSE_TIME = (int) configLoader.getInf_wave_pause_time();
        this.Monster_quantity = 5;
        this.mode = configLoader.getMode();
        this.timeMultiplier  = 1;
    }

    public void CreateGameBoard(){
        this.gameBoard = new GameBoard(p,32,configLoader.getLayout());
        gameBoard.loadMap();
        gameBoard.findPaths();
    }

    public void CreateWizardHouse(){
        this.wizardHouse = new Wizard_house(gameBoard.getWizardHouse_position_X(), gameBoard.getWizardHouse_position_Y(), configLoader ,p);
    }

    public void DrawMap(){//include WizardHouse draw
        gameBoard.drawmap();
        wizardHouse.draw_house();
    }

    //show tower range and cost
    public void showTower_range(int x, int y,boolean[] buttonselected){
        for(Tower tower : Towers){
            if(x > tower.getX() && x < tower.getX() + App.CELLSIZE && y > tower.getY() && y < tower.getY() + App.CELLSIZE){
                p.clip(0, 40, 640, p.height - 40);
                p.stroke(255, 255, 0); // 设置笔触颜色为黄色
                p.strokeWeight(3); // 设置线条的宽度为5像素
                p.noFill(); // 确保圆是空心的
                p.ellipse((float) App.CELLSIZE / 2 + tower.getX(), (float) App.CELLSIZE / 2 + tower.getY(), tower.getRange() * 2, tower.getRange() * 2);
                p.noStroke();
                p.noFill();
                p.clip(0, 0, p.width, p.height);

                int rectX = 650;
                int rectY = 480;
                int count = 1;
                int total = 0;

                p.textSize(12);
                p.fill(255);
                p.rect(rectX, rectY, 100, 40);
                p.fill(0);
                p.text("Upgrade  Cost", rectX + 45, rectY + 20);

                if(buttonselected[3]) {
                    p.fill(200);
                    p.rect(rectX, rectY + 40 * count  , 100, 40);
                    p.fill(0);
                    p.text("Range: "+(20 + 10 *tower.getR()), rectX + 45, rectY + 40 * count + 20);
                    total += 20 + 10 *tower.getR();
                    count ++;
                }

                if(buttonselected[4]) {
                    p.fill(255);
                    p.rect(rectX, rectY + 40 * count, 100, 40);
                    p.fill(0);
                    p.text("Speed: "+(20 + 10 *tower.getS()), rectX + 45, rectY + 40 * count + 20);
                    total += 20 + 10 *tower.getS();
                    count ++;
                }

                if(buttonselected[5]) {
                    p.fill(255);
                    p.rect(rectX, rectY + 40 * count, 100, 40);
                    p.fill(0);
                    p.text("Damage: "+(20 + 10 *tower.getD()), rectX + 45, rectY + 40 * count + 20);
                    total += 20 + 10 *tower.getD();
                    count ++;
                }

                p.fill(200);
                p.rect(rectX, rectY + 40 * count, 100, 40);
                p.fill(0);
                p.text("Total: "+total, rectX + 45, rectY + 40 * count + 20);
            }
        }
    }


    //handle all logic (Monster move,Tower fire)
    public void loops() {
        setTimeMultiplier(this.timeMultiplier);
        List<Fireball> fireballsToRemove = new ArrayList<>();
        List<Monster> monstersToRemove = new ArrayList<>();
        List<Fireball> inactiveFireballs = new ArrayList<>();
        if (Objects.equals(mode, "Normal")){
            spawnMonsters_Normal();
        }else if(Objects.equals(mode, "Infinite")){
            SpawnMonsters_Inf();
        }

        // Handle Monsters
        for (Monster monster : Monsters) {
            if (monster.isDead() && monster.hasDeathAnimationFinished()) { // Check if all Animations finished
                monstersToRemove.add(monster);
                continue;
            }
            //arrive the wizard house
            if (monster.hasReachedEndOfPath()) {
                monster.resetPosition();
                this.wizardHouse.lose_mana(monster.getCurrent_hp());
            } else {//move
                monster.move();
                monster.display_bar();
                monster.display_image();
            }
        }

        // Handle Towers
        for (Tower tower : Towers) {
            if (tower.canFire()) {
                Monster targetMonster = tower.findNearestMonster(Monsters);
                if (targetMonster != null) {
                    Fireball fireball = tower.fireAtMonster(targetMonster);
                    fireballs.add(fireball);
                }
            }
        }

        // Handle Fireballs
        for (Fireball fireball : fireballs) {
            fireball.move();
            for (Monster monster : Monsters) {
                if (monster.isDead()) continue; // Skip dead monsters

                if (fireball.checkCollision(monster)) {
                    monster.damage(fireball.getDamage());

                    // Check monster's health immediately after damaging it
                    System.out.println(monster.getCurrent_hp());
                    System.out.println(!monster.isDead());
                    if (monster.getCurrent_hp() <= 0 && !monster.isDead()) {
                        monster.death();
                        wizardHouse.add_Mana(monster.getMana_gained_on_kill());
                    }
                    fireballsToRemove.add(fireball);
                    break;
                }
            }


            if (! fireball.isActive()) {
                inactiveFireballs.add(fireball);
            }
            fireball.display();
        }

        if (Objects.equals(mode, "Normal")) {
            this.wizardHouse.add_mana_default();
        }
        // Removing dead monsters and used fireballs
        Monsters.removeAll(monstersToRemove);
        fireballs.removeAll(fireballsToRemove);
        fireballs.removeAll(inactiveFireballs);
    }



    // Create a tower
    public void createTowerAtPosition(int X, int Y) {
        Tile currentTile = gameBoard.getTile(X, Y); // to get the tile in this position

        if(currentTile == null){return;}

        if ((! currentTile.isHave_tower()) && currentTile.isCan_place_tower()) {//No tower built before and this tile is a grass
            if (wizardHouse.getMana() >= configLoader.getTowerCost()){
                Towers.add(new Tower(currentTile.getX(), currentTile.getY() , configLoader.getInitialTowerRange(),
                        configLoader.getInitialTowerFiringSpeed(), configLoader.getInitialTowerDamage(), p));
                wizardHouse.lose_mana(configLoader.getTowerCost());
                currentTile.setHave_tower(true);
            }
        }
    }


    public void upgrade_range(int x , int y){
        for (Tower tower : Towers) {
            if(x > tower.getX() && x < tower.getX() + 32 && y > tower.getY() && y < tower.getY() + 32){
                System.out.println(wizardHouse.getMana());
                if(wizardHouse.getMana() > 20 + 10 *tower.getR() ){//TO check whether it has enough mana or not
                    tower.upgrade("range");
                    wizardHouse.lose_mana(20 + 10 *tower.getR() - 10);
                }
            }
        }

    }

    public void upgrade_speed(int x , int y){
        for (Tower tower : Towers) {
            if(x > tower.getX() && x < tower.getX() + 32 && y > tower.getY() && y < tower.getY() + 32){
                if(wizardHouse.getMana() > 20 + 10 * tower.getS()){
                    tower.upgrade("speed" );
                    wizardHouse.lose_mana(20 + 10 * tower.getS() - 10);
                }

            }
        }
    }

    public void upgrade_damage(int x , int y){
        for (Tower tower : Towers) {
            if(x > tower.getX() && x < tower.getX() + 32 && y > tower.getY() && y < tower.getY() + 32){
                System.out.println(wizardHouse.getMana());
                System.out.println(20 + 10 * tower.getD());
                if(wizardHouse.getMana() > 20 + 10 * tower.getD()){
                    tower.upgrade("damage");
                    wizardHouse.lose_mana(20 + 10 * tower.getD() - 10);
                }
            }
        }
    }

    public void rend_Towers(){
        for (Tower tower : Towers) {
            tower.render();
        }
    }

    //set TimeMultiplier for all Towers,fireballs,Monsters
    public void setTimeMultiplier(int multiplier) {
        this.timeMultiplier = multiplier;
        for (Tower tower : Towers) {
            tower.setTimeMultiplier(multiplier);
        }
        for (Fireball fireball : fireballs) {
            fireball.setTimeMultiplier(multiplier);
        }
        for (Monster monster : Monsters) {
            monster.setTimeMultiplier(multiplier);
        }
        wizardHouse.setTimeMultiplier(multiplier);
    }

    public void castManaPoolSpell() {
        wizardHouse.castManaPoolSpell();
    }
    // Variables to keep track of frames since the last monster spawn and last wave start.
    private long framesSinceLastMonsterSpawn = 0;
    private long framesSinceLastWave = 0;

    // Method to spawn monsters in Normal mode.
    public void spawnMonsters_Normal() {
        // Get the current frame rate.
        float framerate = App.FPS;

        // Check if the current wave number is less than the total number of configured waves.
        if (current_wave < configLoader.getWaves().size()) {
            // Fetch the configuration for the current wave.
            ConfigLoader.Wave currentWave = configLoader.getWaves().get(current_wave);

            // Calculate the number of frames to wait before starting the new wave.
            long framesForPreWavePause = (long) ((currentWave.getPreWavePause() * framerate) / timeMultiplier);

            // If enough frames have passed since the last wave, start spawning monsters for the current wave.
            if (framesSinceLastWave >= framesForPreWavePause) {
                // Calculate the total number of monsters in the current wave.
                long totalMonsters = 0;
                for (ConfigLoader.Monster monster : currentWave.getMonsters()) {
                    totalMonsters += monster.getQuantity();
                }

                // Calculate the number of frames between monster spawns.
                long framesBetweenSpawns = (long) ((currentWave.getDuration() * framerate) / timeMultiplier) / totalMonsters;

                // If enough frames have passed since the last monster spawn, spawn a new monster.
                if (framesSinceLastMonsterSpawn >= framesBetweenSpawns) {
                    int spawnedMonsters = 0;

                    // Loop through the monsters in the current wave configuration.
                    for (int i = 0; i < currentWave.getMonsters().size() && spawnedMonsters <= currentMonsterIndex; i++) {
                        ConfigLoader.Monster monsterConfig = currentWave.getMonsters().get(i);

                        // Loop through the quantity of each monster type to spawn.
                        for (int j = 0; j < monsterConfig.getQuantity() && spawnedMonsters <= currentMonsterIndex; j++) {
                            if (spawnedMonsters == currentMonsterIndex) {
                                // Spawn the monster based on its type.
                                switch (monsterConfig.getType()) {
                                    case "gremlin": {
                                        Random random = new Random();
                                        int randomNumber = random.nextInt(gameBoard.getPaths().size());
                                        Monsters.add(new Gremlin(monsterConfig.getArmour(),
                                                monsterConfig.getManaGainedOnKill(), monsterConfig.getHp(),
                                                monsterConfig.getSpeed(), p, gameBoard.getPaths().get(randomNumber)));
                                        break;
                                    }
                                    case "worm": {
                                        Random random = new Random();
                                        int randomNumber = random.nextInt(gameBoard.getPaths().size());
                                        Monsters.add(new Worm(monsterConfig.getArmour(),
                                                monsterConfig.getManaGainedOnKill(), monsterConfig.getHp(),
                                                monsterConfig.getSpeed(), p, gameBoard.getPaths().get(randomNumber)));
                                        break;
                                    }
                                    case "beetle": {
                                        Random random = new Random();
                                        int randomNumber = random.nextInt(gameBoard.getPaths().size());
                                        Monsters.add(new Beetle(monsterConfig.getArmour(),
                                                monsterConfig.getManaGainedOnKill(), monsterConfig.getHp(),
                                                monsterConfig.getSpeed(), p, gameBoard.getPaths().get(randomNumber)));
                                        break;
                                    }
                                }

                                // Reset the frames counter since the last monster spawn.
                                framesSinceLastMonsterSpawn = 0;
                            }
                            spawnedMonsters++;
                        }
                    }

                    // Move to the next monster index.
                    currentMonsterIndex++;

                    // If all monsters for the current wave have been spawned, reset and move to the next wave.
                    if (currentMonsterIndex >= totalMonsters) {
                        currentMonsterIndex = 0;
                        current_wave++;
                        framesSinceLastWave = 0;
                    }
                } else {
                    framesSinceLastMonsterSpawn++;
                }
            } else {
                framesSinceLastWave++;
            }
        }
    }

    //Spawn monster for Infinite mode
    public void SpawnMonsters_Inf() {
        float framerate = App.FPS;

        // Calculate the number of frames for wave pause and monster spawn time
        long framesForWavePause = (long) ((WAVE_PAUSE_TIME * framerate) / timeMultiplier);
        long framesBetweenMonsterSpawns = (long) (((SPAWN_TIME_EACH_WAVE / Monster_quantity) * framerate) / timeMultiplier);

        // Check if it's time to start a new wave
        if (framesSinceLastWave >= framesForWavePause) {
            // Reset the frame counter for the wave
            framesSinceLastWave = 0;

            // Update the HP and Monster quantity for the new wave
            Random random = new Random();
            Inf_HP = Inf_HP * (1 + random.nextInt((int) (HP_INCREMENT_RATE * 10))) / 10.0;
            Monster_quantity = (int) (Monster_quantity * QUANTITY_INCREMENT_RATE);

            // Move on to the next wave
            current_wave++;
            currentMonsterIndex = 0;
        }

        // Spawn monsters within the wave
        if (framesSinceLastMonsterSpawn >= framesBetweenMonsterSpawns && currentMonsterIndex < Monster_quantity) {
            Random random = new Random();
            double hp = Inf_HP + random.nextInt(21) - 10; // -10 to 10
            double speed = random.nextDouble() * SPEED_RANDOM_RANGE + INITIAL_SPEED;
            double mana_gain = MANA_GAIN_ON_KILL;

            int Voidx = random.nextInt(10);
            int pathRandom = random.nextInt(gameBoard.getPaths().size());

            if (Voidx >= VOID_MONSTER_PROBABILITY * 10) {
                Monsters.add(new Gremlin(1.0, (int) mana_gain, hp, speed, p, gameBoard.getPaths().get(pathRandom)));
            } else {
                List<Tile> path = gameBoard.getPaths().get(pathRandom);
                int minLength = path.size() / 2 + 1; // +1 to ensure the length is more than half
                int maxLength = path.size();
                int randomLength = random.nextInt(maxLength - minLength + 1) + minLength;
                List<Tile> subPath = path.subList(path.size() - randomLength, path.size());

                Monsters.add(new Worm(1.0, (int) mana_gain, hp, speed, p, subPath));
            }

            currentMonsterIndex++;
            framesSinceLastMonsterSpawn = 0;
        } else {
            framesSinceLastMonsterSpawn++;
        }

        framesSinceLastWave++;
    }


    public String displayWaveInfo(int x, int y) {
        int remainingSeconds = getRemainingSeconds();

        if (Objects.equals(mode, "Normal")) {
            p.fill(255);
            p.textSize(14);
            p.text("Wave " + (current_wave + 1) + " in: " + remainingSeconds + " seconds", x, y);
        } else if (Objects.equals(mode, "Infinite")) {
            p.fill(255);
            p.textSize(14);
            p.text("Wave " + (current_wave + 2) + " in: " + remainingSeconds + " seconds", x, y);
        }

        if (Objects.equals(mode, "Normal")) {
            return "Wave " + (current_wave + 1) + " in: " + remainingSeconds + " seconds";
        } else if (Objects.equals(mode, "Infinite")) {
            return "Wave " + (current_wave + 2) + " in: " + remainingSeconds + " seconds";
        }
        return "";
    }

    private int getRemainingSeconds() {
        float actualFramerate = App.FPS * timeMultiplier;
        long remainingFrames = 0;

        if (Objects.equals(mode, "Normal")) {
            if (current_wave < configLoader.getWaves().size()) {
                ConfigLoader.Wave currentWave = configLoader.getWaves().get(current_wave);
                long framesForPreWavePause = (long) ((currentWave.getPreWavePause() * App.FPS) / timeMultiplier);
                remainingFrames = framesForPreWavePause - framesSinceLastWave;
            }
        } else if (Objects.equals(mode, "Infinite")) {
            long framesForWavePause = (long) ((WAVE_PAUSE_TIME * App.FPS) / timeMultiplier);
            remainingFrames = framesForWavePause - framesSinceLastWave;
        }

        if (remainingFrames < 0) remainingFrames = 0;

        int remainingSeconds = (int) (remainingFrames / actualFramerate);
        return remainingSeconds;
    }


    public boolean hasPlayerWon() {
        if(Objects.equals(mode, "Normal")){
            return current_wave >= configLoader.getWaves().size() && Monsters.isEmpty();
        }else {
            return false;
        }
    }

    public void displayVictoryMessage() {
        p.fill(0, 255, 0);
        p.textSize(48);
        String victoryMessage = "You win!";
        float textWidth = p.textWidth(victoryMessage);
        float x = (p.width - textWidth) / 2;
        float y = p.height / 2;
        p.text(victoryMessage, x, y);
    }
    public double getCurrentSpellCost(){
        return wizardHouse.getCurrentSpellCost();
    }

    public void renderMonster(){
        for(Monster monster:Monsters){
            monster.display_image();
            monster.display_bar();
        }
    }
    public void displayMana(){
        wizardHouse.displayManaPool();
    }

    public void displayLose_message(){
        p.textSize(32);
        p.fill(255, 0, 0);
        p.textAlign(PApplet.CENTER, PApplet.CENTER);
        p.text("YOU LOST!", p.width / 2, p.height / 2);
        p.noLoop();
    }
    public boolean hasLost(){
        return wizardHouse.If_lost();
    }

    public void add_mana_for_test(double x){wizardHouse.add_Mana(x);}
    public void lose_mana_for_test(double x){wizardHouse.lose_mana(x);}


    //these methods are for testing
    public void set_current_wave(int x){this.current_wave = x;}

    public void setMode(String x){this.mode = x;}

    public void set_framesSinceLastWave(long frames) {
        this.framesSinceLastWave = frames;
    }
    public void set_framesSinceLastMonsterSpawn(long frames) {
        this.framesSinceLastMonsterSpawn = frames;
    }
}

