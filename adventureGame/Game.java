package adventureGame;

import adventureGame.location.Location;
import adventureGame.location.battleLocation.Cave;
import adventureGame.location.battleLocation.Forest;
import adventureGame.location.battleLocation.Mine;
import adventureGame.location.battleLocation.River;
import adventureGame.location.safeLocation.SafeHouse;
import adventureGame.location.safeLocation.ToolStore;

import java.util.Scanner;

public class Game {
    private Location location;
    Scanner inp = new Scanner(System.in);

    public void start() {
        System.out.println("==== MACERA OYUNU ====");
        System.out.println();
        System.out.print("Lütfen isminizi giriniz : ");
        String playerName = inp.nextLine();
        System.out.println();
        Player player = new Player(playerName);
        explanation(player);
        System.out.println();
        player.selectChar();
        selectLoc(player);
    }

    public void selectLoc(Player player) {
        Location[] locations = {new SafeHouse(player), new ToolStore(player), new Cave(player),new Forest(player),new River(player), new Mine(player)};
        int selectLoc = -1;
        while (selectLoc != 9 && player.getHealth() > 0) {
            System.out.println();
            System.out.println("==== BÖLGELER ====");
            System.out.println();
            for (Location loc: locations) {
                System.out.println(loc.getID() + "- " + loc.getName());
            }
            System.out.println();
            System.out.println("7- Karakter Bilgileri\n8- Envanter Bilgileri\n9- Oyunu Bitir");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi veya yapmak istediğiniz işlemi seçiniz : ");
            selectLoc = inp.nextInt();
            System.out.println();
            switch (selectLoc){
                case 1 :
                    this.location = new SafeHouse(player);
                    this.location.onLocation();
                    break;
                case 2 :
                    this.location = new ToolStore(player);
                    this.location.onLocation();
                    break;
                case 3 :
                    this.location = new Cave(player);
                    this.location.onLocation();
                    break;
                case 4 :
                    this.location = new Forest(player);
                    this.location.onLocation();
                    break;
                case 5 :
                    this.location = new River(player);
                    this.location.onLocation();
                    break;
                case 6 :
                    this.location = new Mine(player);
                    this.location.onLocation();
                    break;
                case 7:
                    player.printInfo();
                    break;
                case 8:
                    player.printInventory();
                    break;
                case 9 :
                    break;
                default:
                    System.out.println("Tanımlanmamış bir değer girdiniz.");
            }
        }
        if(player.getHealth() <= 0){
            System.out.println("==== GAME OVER ====");
        }
    }
    public void explanation(Player player){
        System.out.println("Hoşgeldiniz " + player.getName() + "! Macera oyunu bir hayatta kalma oyunudur. Eğer ölürseniz kaybedersiniz." +
                "\nOyunu kazanabilmek için :"+
                "\n* Mağara bölgesindeki bütün canavarları temizleyip Yemek ödülünü"+
                "\n* Orman bölgesindeki bütün canavarları temizleyip Odun ödülünü"+
                "\n* Nehir bölgesindeki bütün canavarları temizleyip Su ödülünü kazanmanız ve güvenli eve gitmeniz gerekmektedir."+
                "\nBol Şans!");

    }
}
