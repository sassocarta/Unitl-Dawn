package entity.NPCS;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class weapons {
    BufferedImage []wp;
    int damageWp;

    public weapons(){   
        wp = new BufferedImage[5];
        LoadWeaponsImage();
    }

    public void LoadWeaponsImage()
    {
        try
        {
        wp[0] = ImageIO.read(getClass().getResource("/src/weapons/axe.png"));
        wp[1] = ImageIO.read(getClass().getResource("/src/weapons/morningstar.png"));
        wp[2] = ImageIO.read(getClass().getResource("/src/weapons/waraxe.png"));
        wp[3] = ImageIO.read(getClass().getResource("/src/weapons/blade.png"));
        wp[4] = ImageIO.read(getClass().getResource("/src/weapons/herosword.png"));

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
