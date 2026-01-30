package collision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.Player.Player;
import main.GamePanel;
import tile.TileManager;

public class CollisionManager {

    Player pl;
    TileManager tm;
    Rectangle hitbox;
    GamePanel gm;
    int hbx, hby;
    int hitboxTile;
    boolean cl = false;

    public CollisionManager(Player pl, TileManager tM, GamePanel gm) {
        this.pl = pl;
        this.tm = tM;
        this.gm = gm;
    }

    public void update() {
        hbx = pl.x + 84;
        hby = pl.y + 97;

        hitbox = new Rectangle(hbx, hby, 20, 20);
        Righthit();
        Lefthit();

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.draw(hitbox);
    }

    public void Righthit() {
        
        int rightEdge = hbx + 20;

       
        int nextCol = (rightEdge + pl.speed) / gm.tileSize;
        int row = hby / gm.tileSize;

        
        int rowBottom = (hby + 20) / gm.tileSize;

      
        if (nextCol >= 0 && nextCol < tm.maptileNum.length) {
            
            if (row >= 0 && row < tm.maptileNum[0].length) {
                int nextTileTop = tm.maptileNum[nextCol][row];
                if (nextTileTop == 2 || nextTileTop == 1 || nextTileTop == 5) {
                    //System.out.println("HIT DESTRA");
                    pl.x -= pl.speed;
                    return;
                }
            }

            if (rowBottom >= 0 && rowBottom < tm.maptileNum[0].length) {
                int nextTileBottom = tm.maptileNum[nextCol][rowBottom];

                if (nextTileBottom == 2 || nextTileBottom == 1 || nextTileBottom == 5) {
                    //System.out.println("HIT DESTRA (alto)");
                    pl.y -= pl.speed;
                    return;
                }
            }
        }

    }

    public void Lefthit() {
       
        int rightEdge1 = hbx; 

        
        int nextCol1 = (rightEdge1 - pl.speed) / gm.tileSize;
        int row1 = hby / gm.tileSize;

        
        int rowBottom1 = (hby - 5) / gm.tileSize;

        
        if (nextCol1 >= 0 && nextCol1 < tm.maptileNum.length) {
           
            if (row1 >= 0 && row1 < tm.maptileNum[0].length) {
                int nextTileTop1 = tm.maptileNum[nextCol1][row1];
                if (nextTileTop1 == 2 || nextTileTop1 == 1 || nextTileTop1 == 5 ) {
                   //System.out.println("HIT SINISTRA");
                    pl.x += pl.speed;
                    return;
                }
            }

            if (rowBottom1 >= 0 && rowBottom1 < tm.maptileNum[0].length) {
                int nextTileBottom1 = tm.maptileNum[nextCol1][rowBottom1];

                if (nextTileBottom1 == 2 || nextTileBottom1 == 1 || nextTileBottom1 == 5) {
                    //System.out.println("HIT SINISTRA (BASSO)");
                    pl.y += pl.speed;
                    return;
                }
            }
        }

    }

}
