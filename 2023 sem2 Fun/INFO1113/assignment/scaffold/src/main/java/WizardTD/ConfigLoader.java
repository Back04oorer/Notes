package WizardTD;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    private JSONObject configData;
    private PApplet pApplet;

    public ConfigLoader(String configPath, PApplet p) {
        this.pApplet = p;
        this.configData = loadConfig(configPath);
    }

    private JSONObject loadConfig(String configPath) {
        return pApplet.loadJSONObject(configPath);
    }

    public String getLayout() {
        return configData.getString("layout");
    }
    public String getMode() {
        return configData.getString("Mode");
    }

    public List<Wave> getWaves() {
        List<Wave> waves = new ArrayList<>();
        JSONArray wavesArray = configData.getJSONArray("waves");

        for (int i = 0; i < wavesArray.size(); i++) {
            JSONObject waveObject = wavesArray.getJSONObject(i);
            List<Monster> monsters = new ArrayList<>();
            JSONArray monstersArray = waveObject.getJSONArray("monsters");

            for (int j = 0; j < monstersArray.size(); j++) {
                JSONObject monsterObject = monstersArray.getJSONObject(j);
                monsters.add(new Monster(
                        monsterObject.getString("type"),
                        monsterObject.getInt("hp"),
                        monsterObject.getFloat("speed"),
                        monsterObject.getFloat("armour"),
                        monsterObject.getInt("mana_gained_on_kill"),
                        monsterObject.getInt("quantity")
                ));
            }

            waves.add(new Wave(
                    waveObject.getFloat("duration"),
                    waveObject.getFloat("pre_wave_pause"),
                    monsters
            ));
        }

        return waves;
    }

    public int getInitialTowerRange() {
        return configData.getInt("initial_tower_range");
    }

    public float getInitialTowerFiringSpeed() {
        return configData.getFloat("initial_tower_firing_speed");
    }

    public int getInitialTowerDamage() {
        return configData.getInt("initial_tower_damage");
    }

    public int getInitialMana() {
        return configData.getInt("initial_mana");
    }

    public int getInitialManaCap() {
        return configData.getInt("initial_mana_cap");
    }

    public float getInitialManaGainedPerSecond() {
        return configData.getFloat("initial_mana_gained_per_second");
    }

    public int getTowerCost() {
        return configData.getInt("tower_cost");
    }

    public int getManaPoolSpellInitialCost() {
        return configData.getInt("mana_pool_spell_initial_cost");
    }

    public int getManaPoolSpellCostIncreasePerUse() {
        return configData.getInt("mana_pool_spell_cost_increase_per_use");
    }

    public float getManaPoolSpellCapMultiplier() {
        return configData.getFloat("mana_pool_spell_cap_multiplier");
    }

    public float getManaPoolSpellManaGainedMultiplier() {
        return configData.getFloat("mana_pool_spell_mana_gained_multiplier");
    }



    public float getInf_Quantity_incremental_rate_from_1(){
        return configData.getFloat("Inf_Quantity_incremental_rate_from_1");
    }

    public float getInf_HP_incremental_rate_from_1(){return configData.getFloat("Inf_HP_incremental_rate_from_1");}

    public float getInf_VoidMonster_probability(){return configData.getFloat("Inf_VoidMonster_probability");}

    public float getInf_hp_initial(){return configData.getFloat("Inf_hp_initial");}

    public float getInf_speed_initial(){return configData.getFloat("Inf_speed_initial");}

    public float getInf_speed_random_range(){return configData.getFloat("Inf_speed_random_range");}

    public float getInf_mana_gain_on_kill_initial(){return configData.getFloat("Inf_mana_gain_on_kill_initial");}
    public float getInf_spawn_time_each_wave(){return configData.getFloat("Inf_spawn_time_each_wave");}

    public float getInf_wave_pause_time(){return configData.getFloat("Inf_wave_pause_time");}


    // Inner class representing a wave of monsters in the game.
    public class Wave {
        float duration; // Duration of the wave in seconds
        float preWavePause; // Time to pause before this wave starts
        List<Monster> monsters; // List of monsters that appear in this wave

        public Wave(float duration, float preWavePause, List<Monster> monsters) {
            this.duration = duration;
            this.preWavePause = preWavePause;
            this.monsters = monsters;
        }

        public float getDuration() {
            return duration;
        }

        public float getPreWavePause() {
            return preWavePause;
        }

        public List<Monster> getMonsters() {
            return monsters;
        }
    }


    // Inner class representing a monster's attributes.
    public class Monster {
        String type; // Type of the monster (e.g., "gremlin", "worm")
        int hp; // Health points of the monster
        float speed; // Movement speed of the monster
        float armour; // Armour value of the monster, might reduce incoming damage
        int manaGainedOnKill; // Amount of mana player gains when this monster is killed
        int quantity; // Number of this type of monster in a wave

        public Monster(String type, int hp, float speed, float armour, int manaGainedOnKill, int quantity) {
            this.type = type;
            this.hp = hp;
            this.speed = speed;
            this.armour = armour;
            this.manaGainedOnKill = manaGainedOnKill;
            this.quantity = quantity;
        }

        public String getType() {
            return type;
        }

        public int getHp() {
            return hp;
        }

        public float getSpeed() {
            return speed;
        }

        public float getArmour() {
            return armour;
        }

        public int getManaGainedOnKill() {
            return manaGainedOnKill;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
