package WizardTD.Tiles;
import processing.core.PApplet;

public abstract class Tile {

    protected int x,y;
    protected PApplet pApplet;
    public boolean can_place_tower,have_tower;
    public Tile(int x, int y, PApplet pApplet,boolean can_place_tower) {
        this.x = x;
        this.y = y;
        this.pApplet = pApplet;
        this.have_tower = false;
        this.can_place_tower = can_place_tower;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void draw() {}

    public boolean isHave_tower(){return this.have_tower;}
    public void setHave_tower(boolean x){this.have_tower = x;}
    public boolean isCan_place_tower() {
        return can_place_tower;
    }
}
