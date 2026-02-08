/**
* @version 1.0
* @file Coin .java 
* 
* @brief File che contiene la classe Coin
*
*/

package entity.Coin;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Player.Player;
import main.Sound;
import tile.TileManager;

/** 
* @class Coin
* 
* @brief Classe che gestisce le monete in gioco
* 
* Questa classe serve a spawnare le monete e a gestire il numero di monete che ha il player
*/
public class Coin {
    /** PLayer*/
    Player pl;

    /** Tile Manager*/
    TileManager tm;

    /** Immagine della moneta*/
    BufferedImage FrameCoin;

    /** rettangolo in cui si raccoglie la moneta*/
    public Rectangle pickupZone;

    /** coordinata x della moneta*/
    public int Cx;

    /** Coordinata y della moneta*/
    public int Cy;

    /** Se è stata presa*/
    public boolean takeit;

    /** se è stat presa una volta*/
    public boolean presounavolta = false;

    /** Suono di quando viene raccolta*/
    Sound sd;

    /** Numero di frame della moneta*/
    int CoinSpriteNum = 1;

     /**
     @brief Costruttore della classe Coin.
     
     questo metodo è il costruttore che crea le monete

     @param pl player
     @param tm tileManager
     @param sd sound

    */
    public Coin(Player pl,TileManager tm, Sound sd) {
        this.pl = pl;
        this.tm = tm ;  
        this.sd = sd;
        GetImagesCoin();
        pickupZone = new Rectangle(Cx,Cy,16,16);
    }

    /**
     @brief Carica immagini.
     
     questo metodo serve per caricare le immagini mettendole in variabili bufferedImage
    */
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

    /**
     @brief Aumenta il numero di monete che ha il player.
     
     Quando una moneta viene racolta il numero di monete che ha il giocatore viene incrementato di 1
    */
    public void aumentaNcoin()
    {
        sd.setFile(7);
        sd.play();
        pl.NumeroCoin++;
         //System.out.println("NUMERO SOLDI:" + pl.NumeroCoin);

    }

    /**
     @brief Spawn delle monete.
     
     questo metodo serve per spawnare le monete in posizioni randomiche attraverso il metodo Coinset

     @param x coordinata x della moneta
     @param y coordinata y della moneta
    */
    public void CoinSpawn(int x,int y)
    {
          int n = 1 + (int) (Math.random() * 4);
          Coinset(x,y,n);
    }

    /**
     @brief Draw.
     
     questo metodo serve per disegnare a schermo i frame dell'oggetto.

     @param g strumento per disegnare a schermo
    */
    public void draw(Graphics2D g2)
    {
        
        pickupZone.x = Cx;
        pickupZone.y = Cy;
        g2.drawImage(FrameCoin, Cx, Cy ,16, 16, null);

    }

    /**
     @brief Probabilità che spawni la moneta.
     
     questo metodo serve calcolare la probabilità che la moneta possa spawnare.
     
     @return 1 se spawna 0 se non spawna
    */
    public boolean NPCPercSpawnCoin()
    {
        int n = 1 + (int) (Math.random() * 7);
        if(n == 1 || n == 5)
        {
            return true;
        }
        return false;
    }

    /**
     @brief Imposta le coordinare randomiche della moneta.
     
     questo metodo serve per impostare delle coordinate in base al parametro n

     @param x coordinata x della moneta
     @param y coordinata y della moneta
     @param n posizione della moneta
    */
    public void Coinset(int x,int y,int n)
    {
        //in base a n vengono scelte diverse coordinate
        switch (n) {
            case 1:
                Cx =x;
                Cy = y + 20;
                break;
            case 2:
                Cx =x;
                Cy = y - 20;
                break;
            case 3:
                Cx = x - 20;
                Cy = y;
                break;
            case 4:
                Cx = x + 20;
                Cy = y;
                break;                                           
        }
    }
}
