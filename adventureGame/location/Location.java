package adventureGame.location;

import adventureGame.Player;

import java.util.Scanner;

public abstract class Location {
    public Scanner inp = new Scanner(System.in);
    private int ID;
    private String name;
    private Player player;

    public Location(int ID, String name, Player player) {
        this.ID = ID;
        this.name = name;
        this.player = player;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract void onLocation();
}
