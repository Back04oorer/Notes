package WizardTD.Tiles;
import processing.core.PApplet;
import processing.core.PImage;
import WizardTD.*;

public class grass extends Tile {
    public grass(int x, int y, PApplet pApplet , boolean can_place_tower) {
        super(x, y,  pApplet,can_place_tower);
    }
    public void draw(){
        this.pApplet.image(App.grassImage,this.x,this.y);
    }

}
