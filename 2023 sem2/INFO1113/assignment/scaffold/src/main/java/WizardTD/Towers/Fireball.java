package WizardTD.Towers;


import WizardTD.Monsters.Monster;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Fireball {
    private PVector position,velocity;
    private float speed,damage;
    private Monster target;
    private PApplet p;
    private PImage sprite;
    public boolean isActive = true; // active or not
    private float timeMultiplier = 1.0f;


    // Modified constructor to include damage and speed
    public Fireball(float x, float y, Monster target, float damage, PApplet p) {
        this.position = new PVector(x, y);
        this.target = target;
        this.speed = 7;
        this.damage = damage;
        this.p = p;
        this.sprite = p.loadImage("src/main/resources/WizardTD/fireball.png");
        updateVelocity();
    }

    // Update fireball direction based on target's position
    private void updateVelocity() {
        PVector targetPosition = new PVector((int) target.getX(), (int) target.getY());
        PVector direction = PVector.sub(targetPosition, position);  // Direction vector
        direction.normalize();  // Make the vector a unit vector

        velocity = direction.mult(speed * timeMultiplier);  // Apply speed and timeMultiplier
        // Apply speed
    }
    public void move() {
        if (!isActive) return;

        if (target.getCurrent_hp() <= 0) {
            isActive = false;
            return;
        }

        updateVelocity();
        position.add(velocity);
    }



    public boolean checkCollision(Monster monster) {
        float distance = PVector.dist(position, new PVector((int) monster.getX(),(int) monster.getY()));
        if(distance <= monster.getRadius()) {  // If monsters have a "getRadius()" method, otherwise adjust accordingly
            monster.damage(damage);
            isActive = false;
            return true;
        }
        return false;
    }

    public void display() {
        p.image(sprite, position.x, position.y);
    }

    public boolean isActive() {
        return isActive;
    }

    // Getters and possibly setters for position, velocity, damage, etc. if needed


    public float getDamage(){
        return this.damage;
    }
    // Add any other necessary getters or setters as your game requires
    public void setTimeMultiplier(float multiplier) {
        this.timeMultiplier = multiplier;
    }

    public float getTTimeMultiplier(){return this.timeMultiplier;}

    public PVector getPosition() {
        return position.copy(); // Return a copy to ensure encapsulation
    }


}
