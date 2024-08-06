package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Tiles.Tile;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Worm extends Monster {
    private List<PImage> sprites;
    private int deathFrameCount = 0;

    public Worm(double Armour,double Mana_gained_on_kill,double hp, double speed, PApplet pApplet, List<Tile> movePath) {
        super(Armour,Mana_gained_on_kill,hp, speed, pApplet, movePath);
        this.sprites = new ArrayList<PImage>();
        this.sprites.add(App.worm0Image);
        this.deathFrameCount = 0;
        deathImageMax = 0;
    }

    @Override
    public void death() {
        if (!isDead) {
            isDead = true;
            deathFrameCount = 0;  // 重置帧计数器，开始播放死亡动画
            current_status = 0;   // 重置动画帧到第一帧
        }
    }

    @Override
    public void display_image() {
        pApplet.image(sprites.get(current_status), (int) this.x, (int) this.y);
    }

    @Override
    public double getRadius() {
        return 10.0;
    }

    public int getDeathFrameCount(){return this.deathFrameCount;}


}
