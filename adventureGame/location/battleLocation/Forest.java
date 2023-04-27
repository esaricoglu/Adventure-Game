package adventureGame.location.battleLocation;

import adventureGame.Player;
import adventureGame.award.Firewood;
import adventureGame.monster.Vampire;

public class Forest extends BattleLocation{
    public Forest(Player player) {
        super(4, "Orman", player, new Vampire(), new Firewood());
    }
}
