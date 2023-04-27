package adventureGame;

import adventureGame.armor.Armor;
import adventureGame.armor.Robe;
import adventureGame.award.Award;
import adventureGame.award.Empty;
import adventureGame.character.Archer;
import adventureGame.character.Character;
import adventureGame.character.Knight;
import adventureGame.character.Samurai;
import adventureGame.weapon.Punch;
import adventureGame.weapon.Weapon;

import java.util.Scanner;

public class Player {
    Scanner inp = new Scanner(System.in);
    private String name;
    private Character character;
    private int damage;
    private int health;
    private int coin;
    private Inventory inventory;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTotalDamage() {
        return this.character.getDamage() + this.inventory.getWeapon().getDamage();
    }

    public int getHealth() {
        if (health < 0) {
            health = 0;
        }
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void selectChar() {
        System.out.println("==== KARAKTERLER ====");
        System.out.println();
        Character[] characters = {new Samurai(), new Archer(), new Knight()};
        for (Character c : characters) {
            System.out.println("ID : " + c.getID() +
                    "\tKarakter : " + c.getName() +
                    "\tHasar : " + c.getDamage() +
                    "\tSağlık : " + c.getHealth() +
                    "\tPara : " + c.getCoin());
        }
        System.out.println();
        System.out.print("Lütfen oynamak istediğiniz karakterin ID'sini giriniz : ");
        int selectChar = inp.nextInt();

        switch (selectChar) {
            case 1:
                this.character = new Samurai();
                System.out.println("Macera Oyunu'na Hoşgeldin " + this.character.getName() + " " + this.name + " !");
                break;
            case 2:
                this.character = new Archer();
                System.out.println("Macera Oyunu'na Hoşgeldin " + this.character.getName() + " " + this.name + " !");
                break;
            case 3:
                this.character = new Knight();
                System.out.println("Macera Oyunu'na Hoşgeldin " + this.character.getName() + " " + this.name + " !");
                break;
            default:
                System.out.println("Tanımlanmamış bir değer girdiniz.");
                System.out.println();
                selectChar();
                break;
        }

        this.setDamage(getCharacter().getDamage());
        this.setHealth(getCharacter().getHealth());
        this.setCoin(getCharacter().getCoin());
        Award[] awards = {new Empty(), new Empty(), new Empty()};
        this.inventory = new Inventory(new Punch(),
                new Weapon[]{new Punch(), new Punch(), new Punch()},
                new Robe(),
                new Armor[]{new Robe(), new Robe(), new Robe()},
                awards);
    }

    public void printInfo() {
        System.out.println(getName().toUpperCase() +
                "\nKarakter : " + getCharacter().getName() +
                "\nSağlık : " + getHealth() +
                "\nSilah : " + getInventory().getWeapon().getName() +
                "\nHasar : " + getTotalDamage() +
                "\nZırh : " + getInventory().getArmor().getName() +
                "\nBloklama : " + getInventory().getArmor().getBlock() +
                "\nPara : " + getCoin());
    }

    public void printInventory() {
        System.out.println("==== ENVANTER ====");
        System.out.println();
        System.out.println("SİLAHLAR");
        for (Weapon weapon : getInventory().getWeapons()) {
            if (weapon.getID() != new Punch().getID()) {
                System.out.println("ID : " + weapon.getID() + "\t " + weapon.getName() + "\tHasar : " + weapon.getDamage());
            }
        }
        System.out.println();
        System.out.println("ZIRHLAR");
        for (Armor armor : getInventory().getArmors()) {
            if (armor.getID() != new Robe().getID()) {
                System.out.println("ID : " + armor.getID() + "\t " + armor.getName() + "\tBloklama : " + armor.getBlock());
            }
        }
        System.out.println();
        System.out.println("ÖDÜLLER");
        for (Award award : getInventory().getAwards()) {
            if (award.getID() != new Empty().getID()) {
                System.out.println("ID : " + award.getID() + "\t " + award.getName());
            }
        }
        int choice = -1;
        while (choice != 2) {
            System.out.println("\n1- Silahını ve Zırhını değiştir" +
                    "\n2- Bölgelere dön");
            System.out.print("Lütfen yapmak istediğiniz işlemi seçiniz : ");
            choice = inp.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    putOn();
                    break;
                case 2:
                    //Bölgelere Dön
                    break;
                default:
                    System.out.println("Tanımlanmamış bir değer girdiniz.");
            }
        }
    }

    public void putOn() {
        int choice = -1;
        while (choice != 3) {
            int selectItem;
            System.out.println("1- Silah Kuşan" +
                    "\n2- Zırh Kuşan" +
                    "\n3- Envantere Dön");
            System.out.print("Lütfen yapmak istediğiniz işlemi seçiniz : ");
            choice = inp.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("ID : 0\t Yumruk \tHasar: 0");
                    for (Weapon weapon : getInventory().getWeapons()) {
                        if (weapon.getID() != new Punch().getID()) {
                            System.out.println("ID : " + weapon.getID() + "\t " + weapon.getName() + "\tHasar : " + weapon.getDamage());
                        }
                    }
                    System.out.print("Lütfen kuşanmak istediğiniz silahın ID'sini giriniz : ");
                    selectItem = inp.nextInt();
                    if (selectItem == 0) {
                        getInventory().setWeapon(new Punch());
                        System.out.println("Silah : " + getInventory().getWeapon().getName());
                    } else {
                        try {
                            if (hasWeapon(getInventory().getWeapons()[selectItem - 1])) {
                                getInventory().setWeapon(getInventory().getWeapons()[selectItem - 1]);
                                System.out.println(getInventory().getWeapon().getName() + " silahını kuşandınız.");
                            } else {
                                System.out.println("ID'si " + selectItem + " olan silaha henüz sahip değilsiniz.");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("ID'si " + selectItem + " olan bir silah yoktur.");
                        }
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("ID : 0\t Cüppe \tBloklama : 0");
                    for (Armor armor : getInventory().getArmors()) {
                        if (armor.getID() != new Robe().getID()) {
                            System.out.println("ID : " + armor.getID() + "\t " + armor.getName() + "\tBloklama : " + armor.getBlock());
                        }
                    }
                    System.out.print("Lütfen kuşanmak istediğiniz zırhın ID'sini giriniz : ");
                    selectItem = inp.nextInt();
                    if (selectItem == 0) {
                        getInventory().setArmor(new Robe());
                        System.out.println("Zırh : " + getInventory().getArmor().getName());
                    } else {
                        try {
                            if (hasArmor(getInventory().getArmors()[selectItem - 1])) {
                                getInventory().setArmor(getInventory().getArmors()[selectItem - 1]);
                                System.out.println(getInventory().getArmor().getName() + " zırhını kuşandınız.");
                            } else {
                                System.out.println("ID'si " + selectItem + " olan zırha henüz sahip değilsiniz.");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("ID'si " + selectItem + " olan bir zırh yoktur.");
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Tanımlanmamış bir seçim yaptınız.");
                    System.out.println();
            }
        }
    }

    public boolean hasWeapon(Weapon weapon) {
        for (int i = 0; i < getInventory().getWeapons().length; i++) {
            if (getInventory().getWeapons()[i].getID() == weapon.getID() && weapon.getID() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasArmor(Armor armor) {
        for (int i = 0; i < getInventory().getArmors().length; i++) {
            if (getInventory().getArmors()[i].getID() == armor.getID() && armor.getID() > 0) {
                return true;
            }
        }
        return false;
    }

}
