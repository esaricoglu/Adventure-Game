package adventureGame.location.safeLocation;

import adventureGame.Player;
import adventureGame.award.Firewood;
import adventureGame.award.Food;
import adventureGame.award.Water;

public class SafeHouse extends SafeLocation {
    public SafeHouse(Player player) {
        super(1, "Güvenli Ev", player);
    }

    @Override
    public void onLocation() {
        System.out.println("==== GÜVENLİ EV ====");
        System.out.println();
        System.out.println("Şu anda Güvenli Ev'desiniz. Burada canavar bulunmamaktadır.");
        if (getPlayer().getHealth() < getPlayer().getCharacter().getHealth()) {
            System.out.println("Canınız yenilendi.");
            System.out.println("Eski canınız = " + getPlayer().getHealth());
            getPlayer().setHealth(getPlayer().getCharacter().getHealth());
            System.out.println("Yeni canınız = " + getPlayer().getHealth());
        }
        if (getPlayer().getInventory().getAwards()[0].getID() == new Food().getID() && getPlayer().getInventory().getAwards()[1].getID() == new Firewood().getID() && getPlayer().getInventory().getAwards()[2].getID() == new Water().getID()) {
            System.out.println("Tebrikler " + getPlayer().getName() + "! Bütün ödüller ile birlikte Güvenli Ev'e gelmeyi başardınız ve oyunu kazandınız." +
                    "\nYeni güncellemeler için takipte kalınız.");
            System.exit(0);
        }

    }
}
