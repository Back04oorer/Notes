package WizardTD;

import processing.event.MouseEvent;
import processing.core.PApplet;

public class InputHandler {
    private App app;
    private Manager manager;

    int hoveredButtonIndex = -1;
    public boolean[] buttonSelected = new boolean[7];//store status of buttons
    public boolean isFastForward = false;
    public boolean isPaused = false;

    public InputHandler(App app, Manager manager) {
        this.app = app;
        this.manager = manager;
    }

    public void keyPressed(char key) {
        switch (key) {
            case 'f':
            case 'F':
                //Toggle fast forward mode
                isFastForward = !isFastForward;
                if (isFastForward) {
                    manager.setTimeMultiplier(2);
                } else {
                    manager.setTimeMultiplier(1);
                }
                buttonSelected[0] = isFastForward;
                break;

            case 'p':
            case 'P':
                // Toggle pause mode
                isPaused = !isPaused;
                app.reversePaused();
                buttonSelected[1] = isPaused;
                break;

            case 't':
            case 'T':
                buttonSelected[2] = !buttonSelected[2];
                break;

            case '1':
                buttonSelected[3] = !buttonSelected[3];
                break;

            case '2':
                buttonSelected[4] = !buttonSelected[4];
                break;

            case '3':
                buttonSelected[5] = !buttonSelected[5];
                break;

            case 'm':
            case 'M':
                //M button for mana pool spell
                buttonSelected[6] = true;
                manager.castManaPoolSpell();
                break;
        }
    }

    //Mouse cases
    public void mousePressed(MouseEvent e) {
        if (hoveredButtonIndex != -1) {
            buttonSelected[hoveredButtonIndex] = !buttonSelected[hoveredButtonIndex];
        }

        if (e.getButton() == PApplet.LEFT) {
            if (hoveredButtonIndex == 0) {
                isFastForward = buttonSelected[0];
                if (isFastForward) {
                    app.frameRate(App.FPS * 2);
                    manager.setTimeMultiplier(2);
                } else {
                    app.frameRate(App.FPS);
                    manager.setTimeMultiplier(1);
                }
            }
            if (hoveredButtonIndex == 1) {
                isPaused = buttonSelected[1];
                app.reversePaused();
            }
            if (buttonSelected[2]) {
                int X = e.getX();
                int Y = e.getY();
                manager.createTowerAtPosition(X, Y);
            }
            if(buttonSelected[3]){
                manager.upgrade_range(e.getX(),e.getY());
            }
            if(buttonSelected[4]){

                manager.upgrade_speed(e.getX(),e.getY());

            }
            if(buttonSelected[5]){
                manager.upgrade_damage(e.getX(),e.getY());
            }
            if (hoveredButtonIndex == 6) {
                buttonSelected[6] = true;
                manager.castManaPoolSpell();
            }
        }
    }

    //Check if a UI button is hovered
    public void checkButtonHover() {
        int sidebarStartX = 640;
        int buttonWidth = 50;
        int buttonHeight = 50;
        int gap = 10;

        hoveredButtonIndex = -1;

        // Loop over all buttons to see which one is hovered
        for (int i = 0; i < 7; i++) {
            int x = sidebarStartX + 5;
            int y = 50 + gap + i * (buttonHeight + gap);

            if (app.mouseX >= x && app.mouseX <= x + buttonWidth && app.mouseY >= y && app.mouseY <= y + buttonHeight) {
                hoveredButtonIndex = i;
                break;
            }
        }
    }

    public void checkTower_range(boolean[] buttonSelected){
        manager.showTower_range(app.mouseX,app.mouseY,buttonSelected);
    }

    public int getHoveredButtonIndex() {
        return hoveredButtonIndex;
    }

    public boolean isButtonSelected(int index) {
        if (index >= 0 && index < buttonSelected.length) {
            return buttonSelected[index];
        }
        return false;
    }

    public void mouseReleased(MouseEvent e) {
        if (hoveredButtonIndex == 6) { // Reset button color when M is released
            buttonSelected[6] = false;
        }
    }
    public void keyReleased(char key) {
        switch (key) {
            case 'm':
            case 'M':
                buttonSelected[6] = false; // Reset button color when M is released
                break;
        }
    }

}
