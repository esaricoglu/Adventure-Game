package adventureGame.location.battleLocation;

import adventureGame.Player;
import adventureGame.award.Award;
import adventureGame.location.Location;
import adventureGame.monster.Monster;

public abstract class BattleLocation extends Location {
    private Monster monster;
    private Award award;

    public BattleLocation(int ID, String name, Player player, Monster monster, Award award) {
        super(ID, name, player);
        this.monster = monster;
        this.award = award;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    @Override
    public void onLocation() {
        if (hasAward()) {
            int numberOfMonster = (int) (Math.ceil(Math.random() * 3));
            System.out.println("==== " + this.getName().toUpperCase() + " ====");
            System.out.println();
            System.out.println("Dikkat! Bu bölgede " + numberOfMonster + " adet " + getMonster().getName() + " yaşıyor.");
            System.out.println("Eğer burada ölürseniz oyunu kaybedersiniz.");
            System.out.println();
            int choice = -1;
            while (choice != 2 && hasAward() && getPlayer().getHealth() > 0) {
                System.out.println("1- Savaş" +
                        "\n2- Bölgelere Dön");
                System.out.print("Lütfen bir seçim yapınız : ");
                choice = inp.nextInt();
                switch (choice) {
                    case 1:
                        combat(numberOfMonster);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Tanımlanmamış bir seçim yaptınız.");
                        System.out.println();
                }
            }
        } else {
            System.out.println("Bu bölgede öldürülecek canavar kalmadı.\nLütfen başka bir bölge seçiniz.");
        }
    }

    public void combat(int numberOfMonster) {
        int monsterHealth = getMonster().getHealth();
        int tempNumberOfMonster = numberOfMonster;
        int choice = -1;
        System.out.println("Oyuncu : " + getPlayer().getName() +
                "\tHasar : " + getPlayer().getTotalDamage() +
                "\tSağlık : " + getPlayer().getHealth() +
                "\tEngelleme : " + getPlayer().getInventory().getArmor().getBlock() +
                "\tPara : " + getPlayer().getCoin());
        System.out.println("Canavar : " + getMonster().getName() +
                "\tHasar : " + getMonster().getDamage() +
                "\tSağlık : " + getMonster().getHealth() +
                "\tGanimet : " + getMonster().getLoot() + " para");
        System.out.println();
        while (choice != 2 && numberOfMonster != 0 && getPlayer().getHealth() > 0) {
            for (int i = 1; i <= tempNumberOfMonster; i++) {
                if (getPlayer().getHealth() > 0) {
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
                                    if (numberOfMonster != 0) {
                                        System.out.println("Savaşılması gereken " + numberOfMonster + " " + getMonster().getName() + " daha var.");
                                        getMonster().setHealth(monsterHealth);
                                    }
                                    System.out.println("Kalan sağlığınız : " + getPlayer().getHealth());
                                    System.out.println(getMonster().getLoot() + " para kazandınız.");
                                    getPlayer().setCoin(getPlayer().getCoin() + getMonster().getLoot());
                                    System.out.println("Yeni bakineyiz : " + getPlayer().getCoin());
                                    System.out.println();
                                    if (numberOfMonster == 0) {
                                        System.out.println(getName() + " bölgesini canavarlardan temizlediniz.");
                                        System.out.println("Ödülünüz : " + getAward().getName());
                                        getPlayer().getInventory().setAwards(this.award);
                                    }
                                }
                            } else {
                                System.out.println("Öldünüz ve kaybettiniz.");
                                break;
                            }
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Tanımlanmamış bir seçim yaptınız.");
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void firstStrikeFromPlayer() {
        System.out.println("İlk saldırıyı yapan : " + getPlayer().getName());
        System.out.println();
        while (getMonster().getHealth() > 0 && getPlayer().getHealth() > 0) {
            if (getPlayer().getHealth() > 0) {
                System.out.println(getPlayer().getName() + " => " + getMonster().getName() + "\t" + getPlayer().getTotalDamage() + " hasar verdi.");
                getMonster().setHealth(getMonster().getHealth() - getPlayer().getTotalDamage());
            }
            if (getMonster().getHealth() > 0) {
                if(getPlayer().getInventory().getArmor().getBlock() > getMonster().getDamage()){
                    System.out.println(getMonster().getName() + " => " + getPlayer().getName() + "\t" + "0" + " hasar vurdu.");
                } else{
                    System.out.println(getMonster().getName() + " => " + getPlayer().getName() + "\t" + (getMonster().getDamage() - getPlayer().getInventory().getArmor().getBlock()) + " hasar vurdu.");
                    getPlayer().setHealth(getPlayer().getHealth() - (getMonster().getDamage() - getPlayer().getInventory().getArmor().getBlock()));
                }
            }
        }
        System.out.println();
    }

    public void firstStrikeFromMonster() {
        System.out.println("İlk saldırıyı yapan : " + getMonster().getName());
        System.out.println();
        while (getMonster().getHealth() > 0 && getPlayer().getHealth() > 0) {
            if (getMonster().getHealth() > 0) {
                if(getPlayer().getInventory().getArmor().getBlock() > getMonster().getDamage()){
                    System.out.println(getMonster().getName() + " => " + getPlayer().getName() + "\t" + "0" + " hasar vurdu.");
                } else{
                    System.out.println(getMonster().getName() + " => " + getPlayer().getName() + "\t" + (getMonster().getDamage() - getPlayer().getInventory().getArmor().getBlock()) + " hasar vurdu.");
                    getPlayer().setHealth(getPlayer().getHealth() - (getMonster().getDamage() - getPlayer().getInventory().getArmor().getBlock()));
                }
            }
            if (getPlayer().getHealth() > 0) {
                System.out.println(getPlayer().getName() + " => " + getMonster().getName() + "\t" + getPlayer().getTotalDamage() + " hasar vurdu.");
                getMonster().setHealth(getMonster().getHealth() - getPlayer().getTotalDamage());
            }
        }
        System.out.println();
    }

    public boolean hasAward() {
        Award award = this.award;
        for (int i = 0; i < this.getPlayer().getInventory().getAwards().length; i++) {
            if (this.getPlayer().getInventory().getAwards()[i].getID() == award.getID()) {
                return false;
            }
        }
        return true;
    }
}
