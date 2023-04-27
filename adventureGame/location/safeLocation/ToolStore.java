package adventureGame.location.safeLocation;

import adventureGame.Player;
import adventureGame.armor.Armor;
import adventureGame.armor.Heavy;
import adventureGame.armor.Light;
import adventureGame.armor.Medium;
import adventureGame.weapon.Pistol;
import adventureGame.weapon.Rifle;
import adventureGame.weapon.Sword;
import adventureGame.weapon.Weapon;

public class ToolStore extends SafeLocation {
    public ToolStore(Player player) {
        super(2, "Eşya Dükkanı", player);
    }

    @Override
    public void onLocation() {
        System.out.println();
        System.out.println("==== EŞYA DÜKKANI ====");
        System.out.println();
        int choice = -1;
        while (choice != 2) {
            System.out.println("1- Alışveriş Yap" +
                    "\n2- Bölgelere Dön");
            System.out.print("Lütfen bir seçim yapınız : ");
            choice = inp.nextInt();
            switch (choice) {
                case 1:
                    toolStore();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Tanımlanmamış bir seçim yaptınız.");
                    System.out.println();
            }
        }
    }

    public void toolStore() {
        System.out.println();
        int choice = -1;
        while (choice != 3) {
            System.out.println("1- Silahlar" +
                    "\n2- Zırhlar" +
                    "\n3- Anamenüye Dön");
            System.out.print("Lütfen bir seçim yapınız : ");
            choice = inp.nextInt();
            switch (choice) {
                case 1:
                    buyWeapon();
                    break;
                case 2:
                    buyArmor();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Tanımlanmmaış bir seçim yaptınız.");
                    System.out.println();
            }
        }
    }

    public void buyWeapon() {
        System.out.println();
        System.out.println("==== SİLAHLAR ====");
        System.out.println();
        Weapon[] weapons = {new Pistol(), new Sword(), new Rifle()};
        for (Weapon w : weapons) {
            System.out.println("ID : " + w.getID() +
                    "\tSilah : " + w.getName() +
                    "\tHasar : " + w.getDamage() +
                    "\tFiyat : " + w.getPrice());
        }
        System.out.println();
        System.out.print("Satın almak istediğiniz silahın ID'sini giriniz : ");
        int choice = inp.nextInt();
        Weapon weapon;
        switch (choice) {
            case 1:
                weapon = new Pistol();
                if (!getPlayer().hasWeapon(weapon)) {
                    if (this.getPlayer().getCoin() >= weapon.getPrice()) {
                        this.getPlayer().getInventory().setWeapons(weapon);
                        System.out.println(weapon.getName() + " silahını satın aldınız.");
                        this.getPlayer().setCoin(this.getPlayer().getCoin() - weapon.getPrice());
                        System.out.println("Kalan paranız : " + getPlayer().getCoin());
                    } else {
                        System.out.println("Yeterli paranız bulunmamaktadır.");
                    }
                } else {
                    System.out.println("Bu silaha zaten sahipsiniz.");
                }
                break;
            case 2:
                weapon = new Sword();
                if (!getPlayer().hasWeapon(weapon)) {
                    if (this.getPlayer().getCoin() >= weapon.getPrice()) {
                        this.getPlayer().getInventory().setWeapons(weapon);
                        System.out.println(weapon.getName() + " silahını satın aldınız.");
                        this.getPlayer().setCoin(this.getPlayer().getCoin() - weapon.getPrice());
                        System.out.println("Kalan paranız : " + getPlayer().getCoin());
                    } else {
                        System.out.println("Yeterli paranız bulunmamaktadır.");
                    }
                } else {
                    System.out.println("Bu silaha zaten sahipsiniz.");
                }
                break;
            case 3:
                weapon = new Rifle();
                if (!getPlayer().hasWeapon(weapon)) {
                    if (this.getPlayer().getCoin() >= weapon.getPrice()) {
                        this.getPlayer().getInventory().setWeapons(weapon);
                        System.out.println(weapon.getName() + " silahını satın aldınız.");
                        this.getPlayer().setCoin(this.getPlayer().getCoin() - weapon.getPrice());
                        System.out.println("Kalan paranız : " + getPlayer().getCoin());
                    } else {
                        System.out.println("Yeterli paranız bulunmamaktadır.");
                    }
                } else {
                    System.out.println("Bu silaha zaten sahipsiniz.");
                }
                break;
            default:
                System.out.println("Tanımlanmamış bir değer girdiniz.");
        }
        System.out.println();
    }

    public void buyArmor() {
        System.out.println();
        System.out.println("==== ZIRHLAR ====");
        System.out.println();
        Armor[] armors = {new Light(), new Medium(), new Heavy()};
        for (Armor a : armors) {
            System.out.println("ID : " + a.getID() +
                    "\tZırh : " + a.getName() +
                    "\tBloklama : " + a.getBlock() +
                    "\tFiyat : " + a.getPrice());
        }
        System.out.println();
        System.out.print("Satın almak istediğiniz zırhın ID'sini giriniz : ");
        int choice = inp.nextInt();
        Armor armor;
        switch (choice) {
            case 1:
                armor = new Light();
                if (!getPlayer().hasArmor(armor)) {
                    if (this.getPlayer().getCoin() >= armor.getPrice()) {
                        this.getPlayer().getInventory().setArmors(armor);
                        System.out.println(armor.getName() + " zırhını satın aldınız.");
                        this.getPlayer().setCoin(this.getPlayer().getCoin() - armor.getPrice());
                        System.out.println("Kalan paranız : " + getPlayer().getCoin());
                    } else {
                        System.out.println("Yeterli paranız bulunmamaktadır.");
                    }
                } else {
                    System.out.println("Bu zırha zaten sahipsiniz.");
                }
                break;
            case 2:
                armor = new Medium();
                if (!getPlayer().hasArmor(armor)) {
                    if (this.getPlayer().getCoin() >= armor.getPrice()) {
                        this.getPlayer().getInventory().setArmors(armor);
                        System.out.println(armor.getName() + " zırhını satın aldınız.");
                        this.getPlayer().setCoin(this.getPlayer().getCoin() - armor.getPrice());
                        System.out.println("Kalan paranız : " + getPlayer().getCoin());
                    } else {
                        System.out.println("Yeterli paranız bulunmamaktadır.");
                    }
                } else {
                    System.out.println("Bu zırha zaten sahipsiniz.");
                }
                break;
            case 3:
                armor = new Heavy();
                if (!getPlayer().hasArmor(armor)) {
                    if (this.getPlayer().getCoin() >= armor.getPrice()) {
                        this.getPlayer().getInventory().setArmors(armor);
                        System.out.println(armor.getName() + " zırhını satın aldınız.");
                        this.getPlayer().setCoin(this.getPlayer().getCoin() - armor.getPrice());
                        System.out.println("Kalan paranız : " + getPlayer().getCoin());
                    } else {
                        System.out.println("Yeterli paranız bulunmamaktadır.");
                    }
                } else {
                    System.out.println("Bu zırha zaten sahipsiniz.");
                }
                break;
            default:
                System.out.println("Tanımlanmamış bir değer girdiniz.");
        }
        System.out.println();
    }
}
