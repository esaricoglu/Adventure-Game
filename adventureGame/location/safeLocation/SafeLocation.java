package adventureGame.location.safeLocation;

import adventureGame.Player;
import adventureGame.location.Location;

public abstract class SafeLocation extends Location {
    public SafeLocation(int ID, String name, Player player) {
        super(ID, name, player);
    }
}
