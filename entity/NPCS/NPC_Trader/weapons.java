package entity.NPCS.NPC_Trader;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class weapons {

    ArrayList<Weapon> weapons = new ArrayList<>();

    BufferedImage AxeBlocked;
    BufferedImage BladeBlocked;
    BufferedImage ChaosBladesBlocked;
    BufferedImage HeroSwordBlocked;
    BufferedImage MorningstarBlocked;
    BufferedImage WarAxeBlocked;

    BufferedImage AxeUnlocked;
    BufferedImage BladeUnlocked;
    BufferedImage ChaosBladesUnlocked;
    BufferedImage HeroSwordUnlocked;
    BufferedImage MorningstarUnlocked;
    BufferedImage WarAxeUnlocked;



    public weapons(){
        LoadWeapons();
    }

    public void LoadWeapons()
    {
        try
        {
            AxeBlocked = ImageIO.read(getClass().getResource("/src/weapons/axeBlack.png"));
            BladeBlocked = ImageIO.read(getClass().getResource("/src/weapons/bladeBlack.png"));
            ChaosBladesBlocked = ImageIO.read(getClass().getResource("/src/weapons/chaosBladesBlack.png"));
            HeroSwordBlocked = ImageIO.read(getClass().getResource("/src/weapons/heroswordBlack.png"));
            MorningstarBlocked = ImageIO.read(getClass().getResource("/src/weapons/morningstarBlack.png"));
            WarAxeBlocked = ImageIO.read(getClass().getResource("/src/weapons/waraxeBlack.png"));

            AxeUnlocked = ImageIO.read(getClass().getResource("/src/weapons/axe.png"));
            BladeUnlocked = ImageIO.read(getClass().getResource("/src/weapons/blade.png"));
            ChaosBladesUnlocked = ImageIO.read(getClass().getResource("/src/weapons/chaosBlades.png"));
            HeroSwordUnlocked = ImageIO.read(getClass().getResource("/src/weapons/herosword.png"));
            MorningstarUnlocked = ImageIO.read(getClass().getResource("/src/weapons/morningstar.png"));
            WarAxeUnlocked = ImageIO.read(getClass().getResource("/src/weapons/waraxe.png"));
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        weapons.add(new Weapon("Axe", AxeBlocked, AxeUnlocked, 15, 15));
        weapons.add(new Weapon("Blade", BladeBlocked, BladeUnlocked, 20, 20));
        weapons.add(new Weapon("Chaos Blades", ChaosBladesBlocked, ChaosBladesUnlocked, 200, 100));
        weapons.add(new Weapon("Hero sword", HeroSwordBlocked, HeroSwordUnlocked, 30, 40));
        weapons.add(new Weapon("Morningstar", MorningstarBlocked, MorningstarUnlocked, 25, 30));
        weapons.add(new Weapon("War Axe", WarAxeBlocked, WarAxeUnlocked, 35, 50));
    }

}
