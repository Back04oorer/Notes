package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

public class App extends PApplet {
    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;
    public static int WIDTH = CELLSIZE * BOARD_WIDTH + SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH * CELLSIZE + TOPBAR;
    public static final int FPS = 60;

    public static PImage grassImage,shrubImage,path0Image,path1Image,path2Image,path3Image,gremlin0Image,gremlin1Image,
            gremlin2Image,gremlin3Image, gremlin4Image,worm0Image,beetle0Image,WizardhouseImage;
    public boolean isPaused = false;
    public String configPath;
    Manager manager;
    private InputHandler inputHandler;

    public App() {
        this.configPath = "config.json";
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        frameRate(FPS);
        grassImage = loadImage("src/main/resources/WizardTD/grass.png");
        shrubImage = loadImage("src/main/resources/WizardTD/shrub.png");
        path0Image = loadImage("src/main/resources/WizardTD/path0.png");
        path1Image = loadImage("src/main/resources/WizardTD/path1.png");
        path2Image = loadImage("src/main/resources/WizardTD/path2.png");
        path3Image = loadImage("src/main/resources/WizardTD/path3.png");
        gremlin0Image = loadImage("src/main/resources/WizardTD/gremlin.png");
        gremlin1Image = loadImage("src/main/resources/WizardTD/gremlin1.png");
        gremlin2Image = loadImage("src/main/resources/WizardTD/gremlin2.png");
        gremlin3Image = loadImage("src/main/resources/WizardTD/gremlin3.png");
        gremlin4Image = loadImage("src/main/resources/WizardTD/gremlin4.png");
        worm0Image = loadImage("src/main/resources/WizardTD/worm.png");
        beetle0Image = loadImage("src/main/resources/WizardTD/beetle.png");
        WizardhouseImage = loadImage("src/main/resources/WizardTD/wizard_house.png");

        manager = new Manager(this, this.configPath);//Create a new manager class to control all logics
        manager.CreateGameBoard();
        manager.CreateWizardHouse();
        inputHandler = new InputHandler(this,manager);

    }


    @Override
    public void draw() {
        //check the status of buttons
        inputHandler.checkButtonHover();
        //draw map
        manager.DrawMap();

        //if paused,only render Monster
        if(! this.isPaused){
            manager.loops();
        }else {
            manager.renderMonster();
        }
        drawRectangles();//draw GUI background
        drawButtons();
        manager.displayMana();
        manager.rend_Towers();
        inputHandler.checkTower_range(inputHandler.buttonSelected);

        manager.displayWaveInfo(100, 10);

        if(manager.hasLost()){
            manager.displayLose_message();
        }

        if(manager.hasPlayerWon()){
            manager.displayVictoryMessage();
            noLoop();
        }
    }

    void drawButtons() {
        int sidebarStartX = 640;
        int buttonWidth = 50;
        int buttonHeight = 50;
        int gap = 10;
        String[] labels = {"FF", "P", "T", "U1", "U2", "U3", "M"};
        String[] descriptions = {
                "2 x speed",
                "PAUSE",
                "Build\ntower",
                "Upgrade\nrange",
                "Upgrade\nspeed",
                "Upgrade\ndamage",
                "Mana pool\ncost "+ ((int) manager.getCurrentSpellCost())
        };
        textAlign(CENTER, CENTER);
        textSize(15);

        for (int i = 0; i < 7; i++) {
            int x = sidebarStartX + 5;
            int y = 50 + gap + i * (buttonHeight + gap);

            if (inputHandler.getHoveredButtonIndex() == i) {
                fill(150); // Grey color when hovered
            } else if (inputHandler.isButtonSelected(i)) {
                fill(0, 255, 0); // Green color when selected
            } else {
                fill(255, 165, 0); // Default button color
            }

            rect(x, y, buttonWidth, buttonHeight);
            fill(0);
            text(labels[i], x + (float) buttonWidth / 2, y + (float) buttonHeight / 2);

            // Drawing the descriptions to the right of the button
            textSize(12);
            textAlign(LEFT, CENTER);
            fill(0);
            text(descriptions[i], x + buttonWidth + 5, y + (float) buttonHeight / 2);
            textSize(15);
        }
    }

    @Override
    public void keyPressed() {
        //for restart game
        if (key == 'R' || key == 'r') {
            if (!isLooping()) {
                resetGame();
            }
        }
        inputHandler.keyPressed(this.key);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        inputHandler.mousePressed(e);
    }

    public static void main(String[] args) {
        PApplet.main("WizardTD.App");
    }

    //restart a game
    public void resetGame() {
        setup();
        loop();
    }

    public void reversePaused(){
        this.isPaused = ! this.isPaused;
    }
    @Override
    public void keyReleased() {
        inputHandler.keyReleased(this.key);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        inputHandler.mouseReleased(e);
    }

    // draw the background for GUI
    public void drawRectangles() {
        fill(100, 133, 63);
        rect(0, 0, 760, 40);
        rect(640, 40, 120, 640);
        noStroke();
        noFill();
    }

}





