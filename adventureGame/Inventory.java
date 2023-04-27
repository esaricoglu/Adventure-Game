package adventureGame;

import adventureGame.armor.Armor;
import adventureGame.award.Award;
import adventureGame.weapon.Weapon;

public class Inventory {
    private Weapon weapon;
    private Weapon[] weapons;
    private Armor armor;
    private Armor[] armors;
    private Award[] awards;

    public Inventory(Weapon weapon, Weapon[] weapons, Armor armor, Armor[] armors, Award[] awards) {
        this.weapon = weapon;
        this.weapons = weapons;
        this.armor = armor;
        this.armors = armors;
        this.awards = awards;
    }



    public Weapon[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapon weapon) {
        this.weapons[weapon.getID()-1] = weapon;
    }

    public Armor[] getArmors() {
        return armors;
    }

    public void setArmors(Armor armor) {
        this.armors[armor.getID()-1] = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Award[] getAwards() {
        return awards;
    }

    public void setAwards(Award award) {
        this.awards[award.getID() - 1] = award;
    }
}
