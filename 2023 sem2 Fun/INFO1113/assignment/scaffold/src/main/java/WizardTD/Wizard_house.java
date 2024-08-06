package WizardTD;
import processing.core.PApplet;
import processing.core.PImage;

public class Wizard_house{

    private final PImage WizardhouseImage;
    private int x,y;
    private final PApplet pApplet;
    private double mana_cap;
    private double current_mana;
    private final double  mana_gained_per_sec,mana_pool_spell_initial_cost,mana_pool_spell_cost_increase_per_use,
            mana_pool_spell_cap_multiplier,mana_pool_spell_mana_gained_multiplier;


    public double spell_cast_count; //times of mana spell
    private double current_gain_multiplier;
    private boolean If_lost = false;
    public int timeMultiplier;

    public Wizard_house(int x, int y, ConfigLoader configLoader,PApplet pApplet) {
        this.x = x;
        this.y = y;
        this.pApplet = pApplet;
        WizardhouseImage = App.WizardhouseImage;
        double mana_initial = configLoader.getInitialMana();
        this.mana_cap = configLoader.getInitialManaCap();
        this.mana_gained_per_sec = configLoader.getInitialManaGainedPerSecond();
        this.current_mana = mana_initial;

        //initialize properties
        this.mana_pool_spell_initial_cost = configLoader.getManaPoolSpellInitialCost();
        this.mana_pool_spell_cost_increase_per_use = configLoader.getManaPoolSpellCostIncreasePerUse();
        this.mana_pool_spell_cap_multiplier = configLoader.getManaPoolSpellCapMultiplier();
        this.mana_pool_spell_mana_gained_multiplier = configLoader.getManaPoolSpellManaGainedMultiplier();
        this.current_gain_multiplier = 1;
        this.spell_cast_count = 0;
        this.timeMultiplier = 1;
    }

    public void draw_house() {
        this.pApplet.image(WizardhouseImage, this.x, this.y);
    }

    public void displayManaPool() {
        pApplet.fill(255, 255, 255);

        int barX = 350;
        int barY = 10;
        int red_Width = 275;
        int red_Height = 20;


        pApplet.rect(barX, barY, red_Width, red_Height);

        pApplet.fill(0, 255, 255);
        int blue_Width = (int) (red_Width * (this.current_mana / this.mana_cap));
        pApplet.rect(barX, barY, blue_Width, red_Height);

        pApplet.fill(0);
        pApplet.textSize(16);
        pApplet.textAlign(PApplet.RIGHT, PApplet.CENTER);
        pApplet.text("MANA:", barX - 10, barY + red_Height / 2);

        String manaText = (int) this.current_mana + "/" + (int) this.mana_cap;
        pApplet.textAlign(PApplet.CENTER, PApplet.CENTER); // Center the text both horizontally and vertically
        pApplet.text(manaText, barX + red_Width / 2, barY + red_Height / 2); // Place the text in the center of the bar
    }


    public void lose_mana(double x){
        if(this.current_mana -x < 0){
            this.current_mana = 0;
            this.If_lost = true;
            return;
        }
        this.current_mana = this.current_mana -x;
    }

    public void castManaPoolSpell() {//mana spell
        if(this.current_mana < getCurrentSpellCost()){
            return;
        }
        this.mana_cap = this.mana_cap * this.mana_pool_spell_cap_multiplier;
        this.current_gain_multiplier += (mana_pool_spell_mana_gained_multiplier -1);
        this.current_mana = this.current_mana - getCurrentSpellCost();
        this.spell_cast_count += 1;
    }

    public void add_mana_default() {
        double mana_gain = this.mana_gained_per_sec * this.current_gain_multiplier * this.timeMultiplier/ pApplet.frameRate;
        if (this.current_mana + mana_gain > this.mana_cap) {
            this.current_mana = this.mana_cap;
        } else {
            this.current_mana += mana_gain;
        }
    }

    public void add_Mana(double x){
        if(x + this.current_mana * this.current_gain_multiplier > mana_cap){
            this.current_mana = mana_cap;
            return;
        }
        this.current_mana = this.current_mana + x * this.current_gain_multiplier;

    }

    public double getMana() {
        return this.current_mana;
    }

    public double getCurrentSpellCost() {
        return this.mana_pool_spell_initial_cost + this.spell_cast_count * this.mana_pool_spell_cost_increase_per_use;
    }

    public boolean If_lost(){
        return If_lost;
    }
    public void setTimeMultiplier(int multiplier) {
        this.timeMultiplier = multiplier;
    }
}

