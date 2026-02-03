package entity.NPCS.NPC_Trader;
import java.awt.image.BufferedImage;

public class Weapon {
    public String name;
    public BufferedImage blockedImage;
    public BufferedImage unlockedImage;
    public int damage;
    public int price;
    
    public Weapon(String name, BufferedImage blockedImage, BufferedImage unlockedImage, int damage, int price) {
        this.name = name;
        this.blockedImage = blockedImage;
        this.unlockedImage = unlockedImage;
        this.damage = damage;
        this.price = price;
    }






}
