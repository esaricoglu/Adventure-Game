package adventureGame.location.battleLocation;

import adventureGame.Player;
import adventureGame.award.Food;
import adventureGame.monster.Zombie;

public class Cave extends BattleLocation{
    public Cave(Player player) {
        super(3, "Mağara", player, new Zombie(), new Food());
    }

}
