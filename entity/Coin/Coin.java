package entity.Coin;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Player.Player;
import main.Sound;
import tile.TileManager;

public class Coin {
    Player pl;
    TileManager tm;
    BufferedImage FrameCoin;
    public Rectangle pickupZone;
    public int Cx;
    public int Cy;
    public boolean takeit;
    public boolean presounavolta = false;
    Sound sd;

    int CoinSpriteNum = 1;

    public Coin(Player pl,TileManager tm, Sound sd) {
        this.pl = pl;
        this.tm = tm ;  
        this.sd = sd;
        GetImagesCoin();
        pickupZone = new Rectangle(Cx,Cy,16,16);
    }
    public void GetImagesCoin()
    {
        try
        {
             FrameCoin =  ImageIO.read(getClass().getResource("/src/Coin/tile" + 1 + ".png"));
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    public void aumentaNcoin()
    {
        sd.setFile(7);
        sd.play();
        pl.NumeroCoin++;
         //System.out.println("NUMERO SOLDI:" + pl.NumeroCoin);

    }
    public void CoinSpawn(int x,int y)
    {
          int n = 1 + (int) (Math.random() * 4);
          Coinset(x,y,n);
    }

    public void draw(Graphics2D g2)
    {
        
        pickupZone.x = Cx;
        pickupZone.y = Cy;
        g2.drawImage(FrameCoin, Cx, Cy ,16, 16, null);

    }

    public boolean NPCPercSpawnCoin()
    {
        int n = 1 + (int) (Math.random() * 7);
        if(n == 1 || n == 5)
        {
            return true;
        }
        return false;
    }

    public void Coinset(int x,int y,int n)
    {
        switch (n) {
            case 1: //up
                Cx =x;
                Cy = y + 20;
                break;
            case 2: //dw
                Cx =x;
                Cy = y - 20;
                break;
            case 3: //lf
                Cx = x - 20;
                Cy = y;
                break;
            case 4: //rg
                Cx = x + 20;
                Cy = y;
                break;                                           
        }
    }
    
    
    
}
