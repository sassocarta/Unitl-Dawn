package entity.NPCS;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class NPC_Manager {

    GamePanel gp;
    Player pl;
    Sound sd;
    TileManager tm;


    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public void setPl(Player pl) {
        this.pl = pl;
    }

    public void setSd(Sound sd) {
        this.sd = sd;
    }

    public void setTm(TileManager tm) {
        this.tm = tm;
    }

    public int x;
    public int y;

    public BufferedImage []NPCUp;
    public BufferedImage []NPCDw;
    public BufferedImage []NPCLf;
    public BufferedImage []NPCRh;

    public BufferedImage []NPCfaces;
    public BufferedImage []NPCdialog;

    BufferedImage face;

    public int UpSpriteCounter = 0;
    public int UpSpriteNum = 1;

    public int DwSpriteCounter = 0;
    public int DwSpriteNum = 1;

    public int LfSpriteCounter = 0;
    public int LfSpriteNum = 1;

    public int RhSpriteCounter = 0;
    public int RhSpriteNum = 1;

    public String direction;

    public String MapSpaw;

    public String nowFace = "";

    public int col;
    public int row;
    public int tileNum;

    public Rectangle StayinZone = null;

    public BufferedImage UpImage = null;
    public BufferedImage DwImage = null;
    public BufferedImage LfImage = null;
    public BufferedImage RhImage = null;

    public boolean uone = false;


}
