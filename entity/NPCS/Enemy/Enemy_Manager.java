package entity.NPCS.Enemy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.NPCS.NPC_Trader.TR_menu;
import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class Enemy_Manager {
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

    public BufferedImage []EnemyWalkRight;
    public BufferedImage []EnemyWalkLeft;
    public BufferedImage []EnemyIdleRight;
    public BufferedImage []EnemyIdleLeft;
    public BufferedImage []EnemyHitRight;
    public BufferedImage []EnemyHitLeft;
    public BufferedImage []EnemyDeathRight;
    public BufferedImage []EnemyDeathLeft;
    public BufferedImage []EnemyAttackRight;
    public BufferedImage []EnemyAttackLeft;

    public BufferedImage WalkRightImage = null;
    public BufferedImage WalkLeftImage = null;
    public BufferedImage IdleRightImage = null;
    public BufferedImage IdleLeftImage = null;
    public BufferedImage HitRightImage = null;
    public BufferedImage HitLeftImage = null;
    public BufferedImage DeathRightImage = null;
    public BufferedImage DeathLeftImage = null;
    public BufferedImage AttackRightImage = null;
    public BufferedImage AttackLeftImage = null;

    

    public int WalkRightSpriteCounter = 0;
    public int WalkRightSpriteNum = 1;

    public int WalkLeftSpriteCounter = 0;
    public int WalkLeftSpriteNum = 1;

    public int IdleRightSpriteCounter = 0;
    public int IdleRightSpriteNum = 1;

    public int IdleLeftSpriteCounter = 0;
    public int IdleLeftSpriteNum = 1;

    public int HitRightSpriteCounter = 0;
    public int HitRightSpriteNum = 1;

    public int HitLeftSpriteCounter = 0;
    public int HitLeftSpriteNum = 1;

    public int DeathRightSpriteCounter = 0;
    public int DeathRightSpriteNum = 1;

    public int DeathLeftSpriteCounter = 0;
    public int DeathLeftSpriteNum = 1;

    public int AttackRightSpriteCounter = 0;
    public int AttackRightSpriteNum = 1;

    public int AttackLeftSpriteCounter = 0;
    public int AttackLeftSpriteNum = 1;

    //variabili base
    public int x;
    public int y;
    public String direction;
    public String MapSpawn;
    public int col;
    public int row;
    public int tileNum;
    public Rectangle StayinZone;
    public int tick = 0;
    public Rectangle stayin;
    public Rectangle detectionRange;
    public TR_menu trm;
    public boolean uone = false;
    public int maxLife = 50;
    public int life = maxLife;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean dying = false;
    public boolean alive = true;
    public String action = "idle";
    public int damage = 0;

    //variabili per lo spawn dei nemici
    public boolean posizionato;
    public int offsetX; 
    public int offsetY;
    public int corpoWidth;
    public int corpoHeight;
    public int tentativi;
    public int minX;
    public int maxX;
    public int minY;
    public int maxY;
    public int corpoLeft;
    public int corpoRight;
    public int corpoTop;
    public int corpoBottom;
    public int startCol;
    public int endCol;
    public int startRow;
    public int endRow;
    public boolean collisione;
    public int tileID;

    //variabili per AI namici
    public int oldX;
    public int oldY;
    public int originalX = x;
    public int originalY = y;

    //variabili per controllo collisioni
    public int EnemyHitboxX;
    public int EnemyHitboxY;
    public int EnemyHitboxWidth;
    public int EnemyHitboxHeight;
    public int leftCol;
    public int rightCol;
    public int topRow;
    public int bottomRow;

    //variabili per barra della vita nemici
    public int xBar;
    public int yBar ;
    public int maxWidth; 
    public int height ;
    public double healthRatio;
    public int currentWidth;

    //SPINNING PROBLEM
    public int sC = 0; // Cambio direzione
    public int sT = 0; // reset contatore tot secondi
    public int tollerance = 1;

    
}