package entity.NPCS.Enemy;

import java.awt.Graphics2D;

import entity.NPCS.NPC_Trader.TR_menu;
import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

import java.util.ArrayList;
import java.util.Random;

public class Enemy_Vector_main {
    Random random = new Random();
    Enemy_Manager EM;
    public ArrayList<Enemy> enemies;
    GamePanel gp;
    Player pl;
    Sound sd;
    TileManager tm;
    TR_menu trm;
    int nEnemies;
    boolean isAllDead;

    String urlWalkRight;
    int NFWalkRight;

    String urlWalkLeft;
    int NFWalkLeft;

    String urlIdleRight;
    int NFIdleRight;

    String urlIdleLeft;
    int NFIdleLeft;

    String urlHitRight;
    int NFHitRight;

    String urlHitLeft;
    int NFHitLeft;

    String urlDeathRight;
    int NFDeathRight;

    String urlDeathLeft;
    int NFDeathLeft;

    String urlAttackRight;
    int NFAttackRight;

    String urlAttackLeft;
    int NFAttackLeft;

    int enemyType;

    public Enemy_Vector_main(GamePanel gp, Player pl, Sound sd, TileManager tm, TR_menu trm) {

        this.gp = gp;
        this.pl = pl;
        this.sd = sd;
        this.tm = tm;
        this.trm = trm;

        this.EM = new Enemy_Manager();
        this.enemies = new ArrayList<>();

        EM.setGp(this.gp);
        EM.setPl(this.pl);
        EM.setSd(this.sd);
        EM.setTm(this.tm);

        CreateNpcs();
    }

    public void CreateNpcs() {
        // modifica la difficoltà in base al giorno
        switch (gp.day) {
            case 0:
                nEnemies = 10;
                break;
            case 1:
                nEnemies = 20;
                break;
            case 2:
                nEnemies = 30;
                break;
            case 3:
                nEnemies = 40;
                break;
            case 4:
                nEnemies = 50;
                break;
            case 5:
                nEnemies = 60;
                break;
            case 6:
                nEnemies = 70;
                break;
            case 7:
                nEnemies = 80;
                break;
            case 8:
                nEnemies = 90;
                break;
            case 9:
                // giorno 10 final boss (da implementare)
                nEnemies = 50; // nemici di "accompagnamento" al boss
                break;
        }

        for (int i = 0; i < nEnemies; i++) {
            //4-5 PROBLEMI
            enemyType = random.nextInt(3) + 1;

            switch (enemyType) {
                case 1:// Slime
                    enemies.add(new Enemy(gp, pl, sd, tm, trm, 8, "/src/Enemies/Slime/RIGHT/SlimeWalk/", // walk right
                            8, "/src/Enemies/Slime/LEFT/SlimeWalk/", // walk left
                            6, "/src/Enemies/Slime/RIGHT/SlimeIdle/", // Idle right
                            6, "/src/Enemies/Slime/LEFT/SlimeIdle/", // idle left
                            4, "/src/Enemies/Slime/RIGHT/SlimeHit/", // hit right
                            4, "/src/Enemies/Slime/LEFT/SlimeHit/", // hit left
                            6, "/src/Enemies/Slime/RIGHT/SlimeDeath/", // death right
                            6, "/src/Enemies/Slime/LEFT/SlimeDeath/", // death left
                            8, "/src/Enemies/Slime/RIGHT/SlimeAttack/", // attack right
                            8, "/src/Enemies/Slime/LEFT/SlimeAttack/",
                            "Slime",
                            0,
                            0,
                            46,
                            48,
                            50,
                            10,
                            4)); // attack left);
                    break;
                case 2:// Hallokin

                    enemies.add(new Enemy(gp, pl, sd, tm, trm, 4, "/src/Enemies/Hallokin/RIGHT/HallokinWalk/", // walk
                                                                                                               // right
                            4, "/src/Enemies/Hallokin/LEFT/HallokinWalk/", // walk left
                            6, "/src/Enemies/Hallokin/RIGHT/HallokinIdle/", // Idle right
                            6, "/src/Enemies/Hallokin/LEFT/HallokinIdle/", // idle left
                            1, "/src/Enemies/Hallokin/RIGHT/HallokinHit/", // hit right
                            1, "/src/Enemies/Hallokin/LEFT/HallokinHit/", // hit left
                            3, "/src/Enemies/Hallokin/RIGHT/HallokinDeath/", // death right
                            3, "/src/Enemies/Hallokin/LEFT/HallokinDeath/", // death left
                            6, "/src/Enemies/Hallokin/RIGHT/HallokinAttack/", // attack right
                            6, "/src/Enemies/Hallokin/LEFT/HallokinAttack/",
                            "Hallokin",
                            0,
                            0,
                            46,
                            48,
                            50,
                            30,
                            4)); // attack left);
                    break;
                case 3:// Orc
                    enemies.add(new Enemy(gp, pl, sd, tm, trm, 8, "/src/Enemies/Orc/RIGHT/OrcWalk/", // walk right
                            8, "/src/Enemies/Orc/LEFT/OrcWalk/", // walk left
                            6, "/src/Enemies/Orc/RIGHT/OrcIdle/", // Idle right
                            6, "/src/Enemies/Orc/LEFT/OrcIdle/", // idle left
                            4, "/src/Enemies/Orc/RIGHT/OrcHit/", // hit right
                            4, "/src/Enemies/Orc/LEFT/OrcHit/", // hit left
                            4, "/src/Enemies/Orc/RIGHT/OrcDeath/", // death right
                            4, "/src/Enemies/Orc/LEFT/OrcDeath/", // death left
                            6, "/src/Enemies/Orc/RIGHT/OrcAttack/", // attack right
                            6, "/src/Enemies/Orc/LEFT/OrcAttack/",
                            "Orc",
                            0,
                            0,
                            46,
                            48,
                            90,
                            20,
                            4)); // attack left);
                    break;
                case 4:// Mushroom
                    enemies.add(new Enemy(gp, pl, sd, tm, trm, 8, "/src/Enemies/Mushroom/RIGHT/MushroomWalk/", // walk
                                                                                                               // right
                            8, "/src/Enemies/Mushroom/LEFT/MushroomWalk/", // walk left
                            6, "/src/Enemies/Mushroom/RIGHT/MushroomIdle/", // Idle right
                            6, "/src/Enemies/Mushroom/LEFT/MushroomIdle/", // idle left
                            4, "/src/Enemies/Mushroom/RIGHT/MushroomHit/", // hit right
                            4, "/src/Enemies/Mushroom/LEFT/MushroomHit/", // hit left
                            6, "/src/Enemies/Mushroom/RIGHT/MushroomDeath/", // death right
                            6, "/src/Enemies/Mushroom/LEFT/MushroomDeath/", // death left
                            8, "/src/Enemies/Mushroom/RIGHT/MushroomAttack/", // attack right
                            8, "/src/Enemies/Mushroom/LEFT/MushroomAttack/",
                            "Mushroom",
                            0,
                            0,
                            46,
                            48,
                            70,
                            15,
                            2)); // attack left);
                    break;
                case 5:// Shadowed
                    enemies.add(new Enemy(gp, pl, sd, tm, trm, 9, "/src/Enemies/Shadowed/RIGHT/ShadowedWalk/", // walk
                                                                                                               // right
                            9, "/src/Enemies/Shadowed/LEFT/ShadowedWalk/", // walk left
                            8, "/src/Enemies/Shadowed/RIGHT/ShadowedIdle/", // Idle right
                            8, "/src/Enemies/Shadowed/LEFT/ShadowedIdle/", // idle left
                            1, "/src/Enemies/Shadowed/RIGHT/ShadowedHit/", // hit right
                            1, "/src/Enemies/Shadowed/LEFT/ShadowedHit/", // hit left
                            4, "/src/Enemies/Shadowed/RIGHT/ShadowedDeath/", // death right
                            4, "/src/Enemies/Shadowed/LEFT/ShadowedDeath/", // death left
                            10, "/src/Enemies/Shadowed/RIGHT/ShadowedAttack/", // attack right
                            10, "/src/Enemies/Shadowed/LEFT/ShadowedAttack/",
                            "Shadowed",
                            0,
                            0,
                            46,
                            48,
                            100,
                            20,
                            4)); // attack left);
                    break;
            }
        }
    }

    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {

                if (enemies.get(i).alive = true) {
                    enemies.get(i).update();
                } else if (enemies.get(i).alive = false) {

                    enemies.remove(i);
                }
            }
        }

        isAllDead = gp.ENEMIES.isAllDead();

        if (isAllDead) {
            gp.Trader.tm.npcForcingNight = false;
            gp.tileM.npcForcingNight = false;

            gp.tileM.CurrentCicleSet();

            gp.cicle = "DAY";
            gp.FermaMusica();
            gp.tileM.GetTileBaseCicle();
            gp.tileM.music = false;
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).draw(g2);
            }
        }
    }

    public boolean isAllDead() {
        return enemies.isEmpty();
    }
}