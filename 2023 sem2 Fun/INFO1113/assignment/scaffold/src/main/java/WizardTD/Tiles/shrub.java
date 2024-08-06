package WizardTD.Tiles;
import WizardTD.App;
import processing.core.PApplet;
import processing.core.PImage;

public class shrub extends Tile {

    public shrub(int x, int y, PApplet pApplet ,boolean can_place_tower) {
        super(x, y,  pApplet , can_place_tower);

    }
    public void draw(){
        this.pApplet.image(App.shrubImage,this.x,this.y);
    }

}