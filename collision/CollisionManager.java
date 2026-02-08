/**
* @version 1.0
* @file CollisionManager.java 
* 
* @brief File che contiene la classe CollisionManager
*
*/

package collision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.Player.Player;
import main.GamePanel;
import tile.TileManager;


/** 
* @class CollisionManager
* 
* @brief Classe che gestisce le collisioni del giocatore con caselle della mappa su cui non può camminare
* 
* Questa classe serve a  gestisce le collisioni del giocatore con caselle della mappa su cui non può camminare
*/
public class CollisionManager {
    /** PLayer*/
    Player pl;

    /** TRile manager*/
    TileManager tm;

    /** Hitbox player*/
    Rectangle hitbox;

    /** Game panel*/
    GamePanel gm;

    /** coordinate x e y della hibox*/
    int hbx, hby;

    /** se ha fatto una collisione*/
    boolean cl = false;

    /**
     @brief Costruttore della classe CollisionManager.
     
     questo metodo è il costruttore che crea il CollisionManager

     @param pl player
     @param tm tileManager
     @param gp gamePanel

    */
    public CollisionManager(Player pl, TileManager tM, GamePanel gm) {
        this.pl = pl;
        this.tm = tM;
        this.gm = gm;
    }

    /**
     @brief Update.
     
     questo metodo serve per aggiornare lo stato dell'oggetto ogni frame (sessanta volte al secondo)
    */
    public void update() {
        hbx = pl.x + 84;
        hby = pl.y + 97;

        hitbox = new Rectangle(hbx, hby, 20, 20);
        Righthit();
        Lefthit();

    }

    /**
     @brief Draw.
     
     questo metodo serve per disegnare a schermo i frame dell'oggetto.

     @param g strumento per disegnare a schermo
    */
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.draw(hitbox);
    }

    /**
     @brief Controlla se il player ha fatto delle collisioni a destra.
     
     questo metodo serve per controllare se il player ha fatto delle collisioni su tile non camminabili a destra.
     se ha fatto delle collisioni, non può più camminare in quella direzione.
    */
    public void Righthit() {
        
        int rightEdge = hbx + 20;

       
        int nextCol = (rightEdge + pl.speed) / gm.tileSize;
        int row = hby / gm.tileSize;

        
        int rowBottom = (hby + 20) / gm.tileSize;

      
        if (nextCol >= 0 && nextCol < tm.maptileNum.length) {
            
            if (row >= 0 && row < tm.maptileNum[0].length) {
                int nextTileTop = tm.maptileNum[nextCol][row];
                if (nextTileTop == 2 || nextTileTop == 1 || nextTileTop == 5) {
                    pl.x -= pl.speed;
                    return;
                }
            }

            if (rowBottom >= 0 && rowBottom < tm.maptileNum[0].length) {
                int nextTileBottom = tm.maptileNum[nextCol][rowBottom];

                if (nextTileBottom == 2 || nextTileBottom == 1 || nextTileBottom == 5) {
                    pl.y -= pl.speed;
                    return;
                }
            }
        }

    }

    /**
     @brief Controlla se il player ha fatto delle collisioni a sinistra.
     
     questo metodo serve per controllare se il player ha fatto delle collisioni su tile non camminabili a sinistra.
     se ha fatto delle collisioni, non può più camminare in quella direzione.
    */
    public void Lefthit() {
       
        int rightEdge1 = hbx; 

        
        int nextCol1 = (rightEdge1 - pl.speed) / gm.tileSize;
        int row1 = hby / gm.tileSize;

        
        int rowBottom1 = (hby - 5) / gm.tileSize;

        
        if (nextCol1 >= 0 && nextCol1 < tm.maptileNum.length) {
           
            if (row1 >= 0 && row1 < tm.maptileNum[0].length) {
                int nextTileTop1 = tm.maptileNum[nextCol1][row1];
                if (nextTileTop1 == 2 || nextTileTop1 == 1 || nextTileTop1 == 5 ) {
                    pl.x += pl.speed;
                    return;
                }
            }

            if (rowBottom1 >= 0 && rowBottom1 < tm.maptileNum[0].length) {
                int nextTileBottom1 = tm.maptileNum[nextCol1][rowBottom1];

                if (nextTileBottom1 == 2 || nextTileBottom1 == 1 || nextTileBottom1 == 5) {
                    pl.y += pl.speed;
                    return;
                }
            }
        }

    }

}
