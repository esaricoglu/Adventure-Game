package adventureGame.location.battleLocation;

import adventureGame.Player;
import adventureGame.armor.Armor;
import adventureGame.armor.Heavy;
import adventureGame.armor.Light;
import adventureGame.armor.Medium;
import adventureGame.monster.Snake;
import adventureGame.weapon.Pistol;
import adventureGame.weapon.Rifle;
import adventureGame.weapon.Sword;
import adventureGame.weapon.Weapon;

import java.util.Random;

public class Mine extends BattleLocation {
    public Random rand = new Random();

    public Mine(Player player) {
        super(6, "Maden", player, new Snake(), null);
    }

    @Override
    public void onLocation() {
        int monsterHealth = getMonster().getHealth();
        int numberOfMonster = (int) Math.ceil(Math.random() * 5);
        System.out.println("==== " + this.getName().toUpperCase() + " ==== ");
        System.out.println();
        System.out.println("Dikkat! Bu bölgede " + numberOfMonster + " adet " + getMonster().getName() + " yaşıyor.");
        System.out.println("Eğer burada ölürseniz oyunu kaybedersiniz.");
        System.out.println();
        int choice = -1;
        while (choice != 2 && getPlayer().getHealth() > 0) {
            System.out.println("1- Savaş" +
                    "\n2- Bölgelere Dön");
            System.out.print("Lütfen bir seçim yapınız : ");
            choice = inp.nextInt();
            switch (choice) {
                case 1:
                    if (getMonster().getHealth() > 0) {
                        combat(numberOfMonster);
                    } else {
                        System.out.println("\nŞu anda bu bölgede canavar bulunmamaktadır.\nCanavarların tekrar doğması için bu bölgeden çıkmalısınız.\n");
                    }
                    break;
                case 2:
                    getMonster().setHealth(monsterHealth);
                    break;
                default:
                    System.out.println("Tanımlanmamış bir seçim yaptınız.");
                    System.out.println();
            }
        }
    }

    @Override
    public void combat(int numberOfMonster) {
        int monsterHealth = getMonster().getHealth();
        int tempNumberOfMonster = numberOfMonster;
        int choice = -1;
        System.out.println();
        System.out.println("Oyuncu : " + getPlayer().getName() +
                "\tHasar : " + getPlayer().getTotalDamage() +
                "\tSağlık : " + getPlayer().getHealth() +
                "\tEngelleme : " + getPlayer().getInventory().getArmor().getBlock() +
                "\tPara : " + getPlayer().getCoin());
        System.out.println("Canavar : " + getMonster().getName() +
                "\nHasar : Yılanların hasarları 3 ve 6 arasında değişkenlik göstermektedir." +
                "\nSağlık : " + getMonster().getHealth() +
                "\nGanimet : Yılanlardan silah, zırh veya para düşme ihtimali vardır.");
        System.out.println();
        while (choice != 2 && numberOfMonster != 0 && getPlayer().getHealth() > 0) {
            for (int i = 1; i <= tempNumberOfMonster; i++) {
                if (getPlayer().getHealth() > 0) {
                    getMonster().setDamage(rand.nextInt(4) + 3);
                    System.out.println("Bu " + getMonster().getName() + " " + getMonster().getDamage() + " hasar vermektedir.");
                    if (tempNumberOfMonster != 1) {
                        System.out.println("1- " + i + ". " + getMonster().getName() + " ile savaş");
                    } else {
                        System.out.println("1- " + getMonster().getName() + " ile savaş");
                    }
                    System.out.println("2- Kaç");
                    System.out.print("Lütfen bir seçim yapınız : ");
                    choice = inp.nextInt();
                    if (choice == 2) {
                        System.out.println();
                        break;
                    }
                    System.out.println();
                    switch (choice) {
                        case 1:
                            int firstStrike = (int) (Math.floor(Math.random() * 101));
                            if (firstStrike <= 50) {
                                firstStrikeFromPlayer();
                            } else {
                                firstStrikeFromMonster();
                            }
                            if (getPlayer().getHealth() > 0) {
                                if (getMonster().getHealth() <= 0) {
                                    System.out.println("Tebrikler! " + getMonster().getName() + " yenildi.");
                                    numberOfMonster--;
                                    System.out.println("Kalan sağlığınız : " + getPlayer().getHealth());
                                    loot();
                                    System.out.println();
                                    if (numberOfMonster != 0) {
                                        System.out.println("Savaşılması gereken " + numberOfMonster + " " + getMonster().getName() + " daha var.");
                                        getMonster().setHealth(monsterHealth);
                                    }
                                }
                            } else {
                                System.out.println("Öldünüz.");
                                break;
                            }
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Tanımlanmamış bir seçim yaptınız.");

                    }
                }
            }
        }
    }

    public void loot() {
        int ratioOfDrop = (int) (Math.random() * 101);
        if (ratioOfDrop <= 15) {
            dropWeapon();
        } else if (ratioOfDrop > 15 && ratioOfDrop <= 30) {
            dropArmor();
        } else if (ratioOfDrop > 30 && ratioOfDrop <= 55) {
            dropCoin();
        } else {
            System.out.println("Bu canavardan herhangi bir ganimet düşmedi.");
        }
    }

    public void dropWeapon() {
        Weapon weapon;
        int ratioOfDropWeapon = (int) (Math.random() * 101);
        if (ratioOfDropWeapon <= 50) {
            weapon = new Pistol();
        } else if (ratioOfDropWeapon > 50 && ratioOfDropWeapon <= 80) {
            weapon = new Sword();
        } else {
            weapon = new Rifle();
        }
        System.out.println("Bu canavardan " + weapon.getName() + " düştü.");
        if (!getPlayer().hasWeapon(weapon)) {
            System.out.println("ID : " + weapon.getID() +
                    "\t Silah : " + weapon.getName() +
                    "\t Hasar : " + weapon.getDamage());
            getPlayer().getInventory().setWeapons(weapon);
        } else {
            System.out.println("Siz bu silaha zaten sahipsiniz.");
        }
    }

    public void dropArmor() {
        Armor armor;
        int ratioOfDropArmor = (int) (Math.random() * 101);
        if (ratioOfDropArmor <= 50) {
            armor = new Light();
        } else if (ratioOfDropArmor > 50 && ratioOfDropArmor <= 800) {
            armor = new Medium();
        } else {
            armor = new Heavy();
        }
        System.out.println("Bu canavardan " + armor.getName() + " düştü.");
        if (!getPlayer().hasArmor(armor)) {
            System.out.println("ID : " + armor.getID() +
                    "\t Zırh : " + armor.getName() +
                    "\t Bloklama : " + armor.getBlock());
            getPlayer().getInventory().setArmors(armor);
        } else {
            System.out.println("Siz bu zırha zaten sahipsiniz.");
        }
    }

    public void dropCoin() {
        int ratioOfDropCoin = (int) (Math.random() * 101);
        int coin;
        if (ratioOfDropCoin <= 50) {
            coin = 1;
        } else if (ratioOfDropCoin > 50 && ratioOfDropCoin <= 80) {
            coin = 5;
        } else {
            coin = 10;
        }
        System.out.println("Bu canavardan " + coin + " para düştü.");
        getPlayer().setCoin(getPlayer().getCoin() + coin);
        System.out.println("Yeni bakiyeniz : " + getPlayer().getCoin());
    }
}