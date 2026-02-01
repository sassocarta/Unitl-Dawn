package entity.NPCS.Enemy;

import java.awt.Graphics2D;

import entity.NPCS.NPC_Trader.TR_menu;
import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class Enemy_Vector_main {
    Enemy_Manager EM;
    public Enemy [] enemies;
    GamePanel gp;
    Player pl;
    Sound sd;
    TileManager tm;
    TR_menu trm;
    int nEnemies = 5;

    public Enemy_Vector_main(GamePanel gp, Player pl, Sound sd, TileManager tm, TR_menu trm)
    {
        
        this.gp = gp;
        this.pl = pl;
        this.sd = sd;
        this.tm = tm;
        this.trm = trm;

        this.EM = new Enemy_Manager();
        this.enemies = new Enemy[nEnemies];

        EM.setGp(this.gp);
        EM.setPl(this.pl);
        EM.setSd(this.sd);
        EM.setTm(this.tm);

        CreateNpcs();
    }

    public void CreateNpcs()
    {
        for(int i=0;i<nEnemies;i++){
            enemies[i] = new Enemy(gp, pl, sd, tm, trm,8,"/src/Enemies/Slime/RIGHT/SlimeWalk/", //walk right
                                                  8,"/src/Enemies/Slime/LEFT/SlimeWalk/", //walk left
                                                  6,"/src/Enemies/Slime/RIGHT/SlimeIdle/", //Idle right
                                                  6,"/src/Enemies/Slime/LEFT/SlimeIdle/", //idle left
                                                  4,"/src/Enemies/Slime/RIGHT/SlimeHit/", //hit right
                                                  4,"/src/Enemies/Slime/LEFT/SlimeHit/", //hit left
                                                  6,"/src/Enemies/Slime/RIGHT/SlimeDeath/", //death right
                                                  6,"/src/Enemies/Slime/LEFT/SlimeDeath/", //death left
                                                  8,"/src/Enemies/Slime/RIGHT/SlimeAttack/", //attack right
                                                  8,"/src/Enemies/Slime/LEFT/SlimeAttack/"); //attack left
        }
    }

    public void update()
    {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
<<<<<<< HEAD
                enemies[i].update();
            }
            else if(enemies[i].alive){
=======
                if (enemies[i].alive = true) {
                    enemies[i].update();
                } 
                else if(enemies[i].alive = false){
>>>>>>> origin/main
                    enemies[i] = null; 
                }
            }
    }
    

    public void draw(Graphics2D g2)
    {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemies[i].draw(g2);
            }
        }
    }

}