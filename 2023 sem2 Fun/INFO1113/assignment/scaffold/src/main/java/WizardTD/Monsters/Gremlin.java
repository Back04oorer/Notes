package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Tiles.Tile;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Gremlin extends Monster {
    private List<PImage> sprites;
    private int deathFrameCount;

    public Gremlin(double Armour,int Mana_gained_on_kill ,double hp, double speed, PApplet pApplet, List<Tile> movePath) {
        super(Armour , Mana_gained_on_kill,hp, speed, pApplet, movePath);
        this.sprites = new ArrayList<PImage>();
        this.sprites.add(App.gremlin0Image);
        this.sprites.add(App.gremlin1Image);
        this.sprites.add(App.gremlin2Image);
        this.sprites.add(App.gremlin3Image);
        this.sprites.add(App.gremlin4Image);
        this.deathFrameCount = 0;
        deathImageMax = 4;
    }

    @Override
    public void display_image() {
        if (isDead && current_status < 4) {
            deathFrameCount++;
            if (deathFrameCount % 4 == 0) {
                current_status++;
            }
        }
        pApplet.image(sprites.get(current_status), (int) this.x, (int) this.y);
    }

    @Override
    public void death() {
        if (!isDead) {
            isDead = true;
            deathFrameCount = 0;
            current_status = 0;
        }
    }

    @Override
    public double getRadius() {
        return 10.0;
    }

    public int getDeathFrameCount(){return this.deathFrameCount;}



}
