package entity.NPCS.Enemy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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

    public int x;
    public int y;

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

    public String direction;

    public String MapSpawn;

    public int col;
    public int row;
    public int tileNum;

    public Rectangle StayinZone = null;

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

    public boolean uone = false;

    public int maxLife = 50;
    public int life = maxLife;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean dying = false;
    public boolean alive = true;
    public String action = "idle";
}