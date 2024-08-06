    package WizardTD.Monsters;
    import WizardTD.Tiles.Tile;
    import processing.core.PApplet;
    import java.util.List;

    public class Monster {
        protected double x, y;
        protected int currentTargetIndex;
        protected List<Tile> movePath;
        protected PApplet pApplet;
        public boolean isDead;

        protected double speed,current_hp,hp_cap,Mana_gained_on_kill,Armour;
        protected int deathImageMax = 1;
        public int current_status  = 0;

        public int timeMutiplier;
        public Monster(double Armour,double Mana_gained_on_kill, double hp, double speed, PApplet pApplet, List<Tile> movePath) {
            this.Armour = Armour;
            this.currentTargetIndex = 0;
            this.hp_cap = hp;
            this.current_hp = hp;
            this.speed = speed;
            this.pApplet = pApplet;
            this.movePath = movePath;
            this.isDead = false;
            this.Mana_gained_on_kill = Mana_gained_on_kill;
            this.timeMutiplier = 1;


            this.x = movePath.get(0).getX() + 6;  // Initial positions.
            this.y = movePath.get(0).getY() + 6;

        }

        public void move() {
            // Fetch the next target Tile the object should move towards.
            Tile currentTarget = movePath.get(currentTargetIndex + 1);

            // Calculate the center of the target Tile.
            double targetX = currentTarget.getX() + 6;
            double targetY = currentTarget.getY() + 6;

            // Compute the difference in x and y directions from the object's current position to the target position.
            double deltaX = targetX - this.x;
            double deltaY = targetY - this.y;

            // Calculate the angle of movement using atan2 function.
            double angle = Math.atan2(deltaY, deltaX);

            // Update the object's x and y positions based on its speed, the computed angle, and the time multiplier.
            this.x += speed * Math.cos(angle) * timeMutiplier;
            this.y += speed * Math.sin(angle) * timeMutiplier;

            // Compute the distance between the object's current position and the target position.
            double distanceToTarget = Math.sqrt(Math.pow(targetX - this.x, 2) + Math.pow(targetY - this.y, 2));

            // If the object is close enough to the target position (within the distance it can move in one time unit),
            // set the object's position to the target position and increment the target index.
            if (distanceToTarget < speed * timeMutiplier) {
                this.x = targetX;
                this.y = targetY;
                currentTargetIndex++;
            }
        }


        public void damage(double hp_decrease){
            if (this.current_hp - hp_decrease * Armour > 0){
                this.current_hp = this.current_hp - hp_decrease * Armour;
            } else {
                this.current_hp = 0;//monster dead
            }
        }

        public double getCurrent_hp(){return this.current_hp;}

        public void display_image() {}

        public void death() {}

        public double getRadius() {
            return 10;
        }


        public boolean isDead(){
            return isDead;
        }

        public void display_bar() {
            double barWidth = 28;
            double barHeight = 3;

            double percentageHP = current_hp / hp_cap; // 假设100是最大的HP，可以根据实际需求修改
            double currentBarWidth = barWidth * percentageHP; // 根据当前HP计算血条宽度

            double barX = this.x - 5 ;
            double barY = this.y - 5;

            pApplet.fill(255, 0, 0);
            pApplet.rect((float) barX, (float) barY, (float) barWidth, (float) barHeight);  // 画出血条的背景

            pApplet.fill(0, 255, 0);
            pApplet.rect((float) barX, (float) barY, (float) currentBarWidth, (float) barHeight);  // 画出当前血量
        }

        public void resetPosition() {
            this.x = movePath.get(0).getX() + 6;
            this.y = movePath.get(0).getY() + 6;
            this.currentTargetIndex = 0;
        }

        public boolean hasReachedEndOfPath() {
            return currentTargetIndex == movePath.size() - 1;
        }

        public double getX() {
            return x;
        }
        public double getY() {
            return y;
        }

        public double getMana_gained_on_kill() {
            return Mana_gained_on_kill;
        }

        public boolean hasDeathAnimationFinished() {
            return isDead() && (current_status >= deathImageMax);
        }

        public int getCurrentStatus(){return current_status;}

        public void setTimeMultiplier(int multiplier) {
            this.timeMutiplier = multiplier;
        }

    }
