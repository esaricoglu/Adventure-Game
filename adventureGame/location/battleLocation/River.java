package adventureGame.location.battleLocation;

import adventureGame.Player;
import adventureGame.award.Water;
import adventureGame.monster.Bear;

public class River extends BattleLocation{
    public River(Player player) {
        super(5, "Nehir", player, new Bear(), new Water());
    }
}
