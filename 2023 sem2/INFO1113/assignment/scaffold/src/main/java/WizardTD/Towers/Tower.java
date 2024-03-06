package WizardTD.Towers;

import com.sun.net.ssl.SSLContextSpi;
import processing.core.PApplet;
import processing.core.PImage;
import WizardTD.Monsters.*;
import java.util.*;

public class Tower {

    private float x, y; // Tower's coordinates
    private float range,firingSpeed,damage; // Tower's range
    private int R, D, S; // Levels of Range, Damage, and Speed
    private float lastFiredTime; // Last time the tower fired
    private PApplet p;
    private PImage spirit;
    private String imagePath;
    public int timeMultiplier = 1;

    // Tower's constructor
    public Tower(float x, float y,float range, float firingSpeed, float damage, PApplet p) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.firingSpeed = firingSpeed;
        this.damage = damage;
        this.p = p;
        this.R = 0;
        this.D = 0;
        this.S = 0;
        this.lastFiredTime = Float.NEGATIVE_INFINITY; // To allow the tower to fire immediately upon creation
        this.imagePath = "src/main/resources/WizardTD/tower0.png";
        this.spirit = p.loadImage(this.imagePath);
        this.updateStatus();
    }

    public boolean canFire() {
        return p.millis() - lastFiredTime >= (1000 / firingSpeed) / timeMultiplier;
    }


    // This method checks for the nearest Monster in its range and returns it
    public Monster findNearestMonster(List<Monster> monsters) {
        Monster nearest = null;
        float minDist = Float.MAX_VALUE;

        for (Monster m : monsters) {
            float dist = PApplet.dist(this.x, this.y, (float)m.getX(), (float)m.getY());
            if (dist < this.getRange() && dist < minDist) {
                nearest = m;
                minDist = dist;
            }
        }

        return nearest;
    }


    public String getImagePath() {
        return this.imagePath;
    }

    // This method fires a fireball towards a monster
    public Fireball fireAtMonster(Monster target) {
        lastFiredTime = p.millis();
        return new Fireball(this.x, this.y, target, this.damage, p);
    }

    public int drawEllipse(int count) {
        p.stroke(160, 50, 160);
        p.strokeWeight(3);
        p.noFill();
        for (int i = 0; i < count; i++) {
            p.ellipse(this.x + 4 + i*12, this.y + 4, 7, 7);
        }
        p.noStroke();
        return count;
    }

    public int drawRect(int count){
        p.stroke(0, 80, 160);
        p.strokeWeight(2 * count);
        p.noFill();
        p.rect(this.x + 8, this.y + 8, 16, 16);
        p.noStroke();
        return count;
    }

    public int drawLines(int count) {
        p.stroke(200, 50, 160);
        p.strokeWeight(3);
        for (int i = 0; i < count; i++) {
            int offset = i * 8;
            p.line(this.x + offset, this.y + 24, this.x + 8 + offset, this.y + 32);
            p.line(this.x + 8 + offset, this.y + 24, this.x + offset, this.y + 32);
        }
        p.noStroke();
        return count;
    }

    public void updateStatus() {
        if (R < 1 || D < 1 || S < 1) {
            this.imagePath = "src/main/resources/WizardTD/tower0.png";
        } else if (R < 2 || D < 2 || S < 2) {
            this.imagePath = "src/main/resources/WizardTD/tower1.png";
        } else {
            this.imagePath = "src/main/resources/WizardTD/tower2.png";
        }
        this.spirit = p.loadImage(this.imagePath);
    }

    // Upgrade tower's method
    public void upgrade(String upgradeType) {
        switch (upgradeType) {
            case "range":

                this.range += 32;
                R += 1;
                updateStatus();
                break;
            case "speed":

                this.firingSpeed += 0.5;
                S += 1;
                updateStatus();

                break;
            case "damage":

                this.damage = (float) (this.damage * 1.5);
                D += 1;
                updateStatus();

                break;
        }

    }

    public int getD() {
        return D;
    }

    public int getR(){
        return R;
    }

    public int getS(){
        return S;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRange() {
        return range;
    }

    public float getDamage() {return damage;}

    public float getFiringSpeed(){return firingSpeed;}

    public int[] render() {
        int[] renderResults = new int[3];
        p.image(this.spirit, this.x, this.y);

        if (R < 1 || D < 1 || S < 1) {
            renderResults[0] = drawRect(S);
            renderResults[1] = drawEllipse(R);
            renderResults[2] = drawLines(D);
        } else if (R < 2 || D < 2 || S < 2) {
            renderResults[0] = drawRect(S-1);
            renderResults[1] = drawEllipse(R-1);
            renderResults[2] = drawLines(D-1);
        } else {
            renderResults[0] = drawRect(S-2);
            renderResults[1] = drawEllipse(R-2);
            renderResults[2] = drawLines(D-2);
        }
        return renderResults;
    }

    public void setTimeMultiplier(int multiplier) {
        this.timeMultiplier = multiplier;
    }

}
