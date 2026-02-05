
package entity.NPCS.Enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;

import entity.Coin.Coin;
import entity.NPCS.NPC_Trader.TR_menu;
import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class Enemy extends Enemy_Manager {


    // WalkRight
    String urlWalkRight;
    int NFWalkRight;
    // WalkLeft
    String urlWalkLeft;
    int NFWalkLeft;
    // IdleRight
    String urlIdleRight;
    int NFIdleRight;
    // IdleLeft
    String urlIdleLeft;
    int NFIdleLeft;
    // HitRight
    String urlHitRight;
    int NFHitRight;
    // HitLeft
    String urlHitLeft;
    int NFHitLeft;
    // DeathRight
    String urlDeathRight;
    int NFDeathRight;
    // DeathLeft
    String urlDeathLeft;
    int NFDeathLeft;
    // AttackRight
    String urlAttackRight;
    int NFAttackRight;
    // AttackLeft
    String urlAttackLeft;
    int NFAttackLeft;

    // Coin
    Coin coin;
    boolean onespawn = false;

    public Enemy(GamePanel gp,
            Player pl,
            Sound sd,
            TileManager tm,
            TR_menu trm,
            int NFWalkRight,
            String urlWalkRight,
            int NFWalkLeft,
            String urlWalkLeft,
            int NFIdleRight,
            String urlIdleRight,
            int NFIdleLeft,
            String urlIdleLeft,
            int NFHitRight,
            String urlHitRight,
            int NFHitLeft,
            String urlHitLeft,
            int NFDeathRight,
            String urlDeathRight,
            int NFDeathLeft,
            String urlDeathLeft,
            int NFAttackRight,
            String urlAttackRight,
            int NFAttackLeft,
            String urlAttackLeft,
            String name,
            int hitboxX,
            int hitboxY,
            int hitboxWidth,
            int hitboxHeight,
            int maxLife,
            int damage,
            int drawMultiplier) {

        this.gp = gp;
        this.tm = tm;
        this.pl = pl;
        this.sd = sd;
        this.trm = trm;

        StayinZone = new Rectangle(96, 96, 576, 384);
        stayin = new Rectangle(hitboxX, hitboxY, hitboxWidth, hitboxHeight);

        detectionRange = new Rectangle(0, 0, 400, 400);

        this.hitboxX = hitboxX;
        this.hitboxY = hitboxY;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.maxLife = maxLife;
        this.damage = damage;
        this.drawMultiplier = drawMultiplier;
        this.name = name;
        this.life = maxLife;

        EnemyWalkRight = new BufferedImage[NFWalkRight];
        EnemyWalkLeft = new BufferedImage[NFWalkLeft];
        EnemyIdleRight = new BufferedImage[NFIdleRight];
        EnemyIdleLeft = new BufferedImage[NFIdleLeft];
        EnemyHitRight = new BufferedImage[NFHitRight];
        EnemyHitLeft = new BufferedImage[NFHitLeft];
        EnemyDeathRight = new BufferedImage[NFDeathRight];
        EnemyDeathLeft = new BufferedImage[NFDeathLeft];
        EnemyAttackRight = new BufferedImage[NFAttackRight];
        EnemyAttackLeft = new BufferedImage[NFAttackLeft];

        this.urlWalkRight = urlWalkRight;
        this.NFWalkRight = NFWalkRight;
        this.urlWalkLeft = urlWalkLeft;
        this.NFWalkLeft = NFWalkLeft;
        this.urlIdleRight = urlIdleRight;
        this.NFIdleRight = NFIdleRight;
        this.urlIdleLeft = urlIdleLeft;
        this.NFIdleLeft = NFIdleLeft;
        this.urlHitRight = urlHitRight;
        this.NFHitRight = NFHitRight;
        this.urlHitLeft = urlHitLeft;
        this.NFHitLeft = NFHitLeft;
        this.urlDeathRight = urlDeathRight;
        this.NFDeathRight = NFDeathRight;
        this.urlDeathLeft = urlDeathLeft;
        this.NFDeathLeft = NFDeathLeft;
        this.urlAttackRight = urlAttackRight;
        this.NFAttackRight = NFAttackRight;
        this.urlAttackLeft = urlAttackLeft;
        this.NFAttackLeft = NFAttackLeft;

        coin = new Coin(pl, tm, sd);

        

        deicidiMappaSpawn();
        SpwanEnemy();
        GetAllEnemyImages();
        randomStarDirection();
        spriteSet();
    }

    public void GetImagesWalkRight(int NFWalkRight, String urlWalkRight) {
        try {
            for (int i = 0; i < NFWalkRight; i++) {
                java.net.URL path = getClass().getResource(urlWalkRight + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlWalkRight + i + ".png");
                } else {
                    EnemyWalkRight[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesWalkLeft(int NFWalkLeft, String urlWalkLeft) {
        try {
            for (int i = 0; i < NFWalkLeft; i++) {
                java.net.URL path = getClass().getResource(urlWalkLeft + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlWalkLeft + i + ".png");
                } else {
                    EnemyWalkLeft[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesIdleRight(int NFIdleRight, String urlIdleRight) {
        try {
            for (int i = 0; i < NFIdleRight; i++) {
                java.net.URL path = getClass().getResource(urlIdleRight + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlIdleRight + i + ".png");
                } else {
                    EnemyIdleRight[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesIdleLeft(int NFIdleLeft, String urlIdleLeft) {
        try {
            for (int i = 0; i < NFIdleLeft; i++) {
                java.net.URL path = getClass().getResource(urlIdleLeft + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlIdleLeft + i + ".png");
                } else {
                    EnemyIdleLeft[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesHitRight(int NFHitRight, String urlHitRight) {
        try {
            for (int i = 0; i < NFHitRight; i++) {
                java.net.URL path = getClass().getResource(urlHitRight + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlHitRight + i + ".png");
                } else {
                    EnemyHitRight[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesHitLeft(int NFHitLeft, String urlHitLeft) {
        try {
            for (int i = 0; i < NFHitLeft; i++) {
                java.net.URL path = getClass().getResource(urlHitLeft + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlHitLeft + i + ".png");
                } else {
                    EnemyHitLeft[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesDeathRight(int NFDeathRight, String urlDeathRight) {
        try {
            for (int i = 0; i < NFDeathRight; i++) {
                java.net.URL path = getClass().getResource(urlDeathRight + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlDeathRight + i + ".png");
                } else {
                    EnemyDeathRight[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesDeathLeft(int NFDeathLeft, String urlDeathLeft) {
        try {
            for (int i = 0; i < NFDeathLeft; i++) {
                java.net.URL path = getClass().getResource(urlDeathLeft + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlDeathLeft + i + ".png");
                } else {
                    EnemyDeathLeft[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesAttackRight(int NFAttackRight, String urlAttackRight) {
        try {
            for (int i = 0; i < NFAttackRight; i++) {
                java.net.URL path = getClass().getResource(urlAttackRight + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlAttackRight + i + ".png");
                } else {
                    EnemyAttackRight[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetImagesAttackLeft(int NFAttackLeft, String urlAttackLeft) {
        try {
            for (int i = 0; i < NFAttackLeft; i++) {
                java.net.URL path = getClass().getResource(urlAttackLeft + i + ".png");
                if (path == null) {
                    System.out.println("ERRORE: File non trovato: " + urlAttackLeft + i + ".png");
                } else {
                    EnemyAttackLeft[i] = ImageIO.read(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetAllEnemyImages() {
        GetImagesWalkRight(NFWalkRight, urlWalkRight);
        GetImagesWalkLeft(NFWalkLeft, urlWalkLeft);
        GetImagesIdleRight(NFIdleRight, urlIdleRight);
        GetImagesIdleLeft(NFIdleLeft, urlIdleLeft);
        GetImagesHitRight(NFHitRight, urlHitRight);
        GetImagesHitLeft(NFHitLeft, urlHitLeft);
        GetImagesDeathRight(NFDeathRight, urlDeathRight);
        GetImagesDeathLeft(NFDeathLeft, urlDeathLeft);
        GetImagesAttackRight(NFAttackRight, urlAttackRight);
        GetImagesAttackLeft(NFAttackLeft, urlAttackLeft);
        try {
            barraVitaImg = ImageIO.read(getClass().getResource("/src/Enemies/Boss/BarraVita.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        if (onespawn == true && coin.takeit == false && tm.currentMap.equalsIgnoreCase(MapSpawn)) {
            coin.draw(g2);
        }

        if (!alive)
            return;

        if (gp.cicle == "NIGHT" && trm.isOpen != true) {

            if (!tm.currentMap.equalsIgnoreCase(MapSpawn)) {
                return;
            }

            spriteSet();
            BufferedImage imageToDraw = null;

            switch (action) {
                case "hit":
                    g2.setFont(new Font("Arial", Font.BOLD, 12));
                    g2.setColor(Color.RED);
                    g2.drawString("- " + pl.Damage, this.x + 80, this.y +65);
                    if (direction.equals("right") || direction.equals("up")) {
                        imageToDraw = EnemyHitRight[HitRightSpriteNum - 1];
                    } else {
                        imageToDraw = EnemyHitLeft[HitLeftSpriteNum - 1];
                    }
                    break;
                case "death":
                    if (direction.equals("right") || direction.equals("up")) {
                        imageToDraw = EnemyDeathRight[DeathRightSpriteNum - 1];
                    } else {
                        imageToDraw = EnemyDeathLeft[DeathLeftSpriteNum - 1];
                    }
                    break;
                case "walk":
                    if (direction.equals("right") || direction.equals("up")) {
                        imageToDraw = EnemyWalkRight[WalkRightSpriteNum - 1];
                    } else {
                        imageToDraw = EnemyWalkLeft[WalkLeftSpriteNum - 1];
                    }
                    break;
                case "idle":
                    if (direction.equals("right") || direction.equals("up")) {
                        imageToDraw = EnemyIdleRight[IdleRightSpriteNum - 1];
                    } else {
                        imageToDraw = EnemyIdleLeft[IdleLeftSpriteNum - 1];
                    }
                    break;
                case "attack":
                    if (direction.equals("right") || direction.equals("up")) {
                        imageToDraw = EnemyAttackRight[AttackRightSpriteNum - 1];
                    } else {
                        imageToDraw = EnemyAttackLeft[AttackLeftSpriteNum - 1];
                    }
                    break;
            }

            // Disegna l'immagine scelta
            if (imageToDraw != null) {
                switch(this.name){
                    case"Slime":
                        g2.drawImage(imageToDraw, x, y, gp.tileSize * drawMultiplier, gp.tileSize * drawMultiplier, null);
                        break;
                    case"Mushroom":
                        g2.drawImage(imageToDraw, x+50, y+30, gp.tileSize * drawMultiplier, gp.tileSize * drawMultiplier, null);
                        break;
                    case"Orc":
                        g2.drawImage(imageToDraw, x, y, gp.tileSize * drawMultiplier, gp.tileSize * drawMultiplier, null);
                        break;
                    case"Hallokin":
                        g2.drawImage(imageToDraw, x, y, gp.tileSize * drawMultiplier, gp.tileSize * drawMultiplier, null);
                        break;
                    case"Shadowed":
                        g2.drawImage(imageToDraw, x, y-70, gp.tileSize * drawMultiplier, gp.tileSize * drawMultiplier, null);
                        break;
                    case"Boss":
                        g2.drawImage(imageToDraw, x-85, y-55, 48 * drawMultiplier, 30 * drawMultiplier, null);
                        break;
                }

            }

            // Barra della vita ai mostri normali
            if(this.name!="Boss"){
                xBar = x + 70;
                yBar = y + 70;
                maxWidth = 50;
                height = 5;

                g2.setColor(new Color(50, 50, 50));
                g2.fillRect(xBar, yBar, maxWidth, height);

                healthRatio = (double) life / maxLife;
                currentWidth = (int) (healthRatio * maxWidth);

                g2.setColor(new Color(203, 50, 52));
                g2.fillRect(xBar, yBar, currentWidth, height);
            }else if(this.name=="Boss"){
                //barra della vita boss
                xBar = 190;
                yBar = 30;
                maxWidth = 400;
                height = 10;

                g2.setColor(new Color(50, 50, 50));
                g2.fillRect(xBar, yBar, maxWidth, height);

                healthRatio = (double) life / maxLife;
                currentWidth = (int) (healthRatio * maxWidth);

                g2.setColor(new Color(203, 50, 52));
                g2.fillRect(xBar, yBar, currentWidth, height);

                g2.drawImage(barraVitaImg, xBar-100, yBar - 25, 600, 50 , null);
            }
            
            //g2.setColor(Color.RED);
            //g2.draw(stayin);

            //g2.setColor(Color.BLUE);
            //g2.drawRect(EnemyHitboxX, EnemyHitboxY, EnemyHitboxWidth, EnemyHitboxHeight);
        }
    }

    public void spriteSet() {
        switch (this.name) {
            case "Slime":
                if (WalkRightSpriteNum == 1)
                    WalkRightImage = EnemyWalkRight[0];
                if (WalkRightSpriteNum == 2)
                    WalkRightImage = EnemyWalkRight[1];
                if (WalkRightSpriteNum == 3)
                    WalkRightImage = EnemyWalkRight[2];
                if (WalkRightSpriteNum == 4)
                    WalkRightImage = EnemyWalkRight[3];
                if (WalkRightSpriteNum == 5)
                    WalkRightImage = EnemyWalkRight[4];
                if (WalkRightSpriteNum == 6)
                    WalkRightImage = EnemyWalkRight[5];
                if (WalkRightSpriteNum == 7)
                    WalkRightImage = EnemyWalkRight[6];
                if (WalkRightSpriteNum == 8)
                    WalkRightImage = EnemyWalkRight[7];

                if (WalkLeftSpriteNum == 1)
                    WalkLeftImage = EnemyWalkLeft[0];
                if (WalkLeftSpriteNum == 2)
                    WalkLeftImage = EnemyWalkLeft[1];
                if (WalkLeftSpriteNum == 3)
                    WalkLeftImage = EnemyWalkLeft[2];
                if (WalkLeftSpriteNum == 4)
                    WalkLeftImage = EnemyWalkLeft[3];
                if (WalkLeftSpriteNum == 5)
                    WalkLeftImage = EnemyWalkLeft[4];
                if (WalkLeftSpriteNum == 6)
                    WalkLeftImage = EnemyWalkLeft[5];
                if (WalkLeftSpriteNum == 7)
                    WalkLeftImage = EnemyWalkLeft[6];
                if (WalkLeftSpriteNum == 8)
                    WalkLeftImage = EnemyWalkLeft[7];

                if (IdleRightSpriteNum == 1)
                    IdleRightImage = EnemyIdleRight[0];
                if (IdleRightSpriteNum == 2)
                    IdleRightImage = EnemyIdleRight[1];
                if (IdleRightSpriteNum == 3)
                    IdleRightImage = EnemyIdleRight[2];
                if (IdleRightSpriteNum == 4)
                    IdleRightImage = EnemyIdleRight[3];
                if (IdleRightSpriteNum == 5)
                    IdleRightImage = EnemyIdleRight[4];
                if (IdleRightSpriteNum == 6)
                    IdleRightImage = EnemyIdleRight[5];

                if (IdleLeftSpriteNum == 1)
                    IdleLeftImage = EnemyIdleLeft[0];
                if (IdleLeftSpriteNum == 2)
                    IdleLeftImage = EnemyIdleLeft[1];
                if (IdleLeftSpriteNum == 3)
                    IdleLeftImage = EnemyIdleLeft[2];
                if (IdleLeftSpriteNum == 4)
                    IdleLeftImage = EnemyIdleLeft[3];
                if (IdleLeftSpriteNum == 5)
                    IdleLeftImage = EnemyIdleLeft[4];
                if (IdleLeftSpriteNum == 6)
                    IdleLeftImage = EnemyIdleLeft[5];

                if (HitRightSpriteNum == 1)
                    HitRightImage = EnemyHitRight[0];
                if (HitRightSpriteNum == 2)
                    HitRightImage = EnemyHitRight[1];
                if (HitRightSpriteNum == 3)
                    HitRightImage = EnemyHitRight[2];
                if (HitRightSpriteNum == 4)
                    HitRightImage = EnemyHitRight[3];

                if (HitLeftSpriteNum == 1)
                    HitLeftImage = EnemyHitLeft[0];
                if (HitLeftSpriteNum == 2)
                    HitLeftImage = EnemyHitLeft[1];
                if (HitLeftSpriteNum == 3)
                    HitLeftImage = EnemyHitLeft[2];
                if (HitLeftSpriteNum == 4)
                    HitLeftImage = EnemyHitLeft[3];

                if (DeathRightSpriteNum == 1)
                    DeathRightImage = EnemyDeathRight[0];
                if (DeathRightSpriteNum == 2)
                    DeathRightImage = EnemyDeathRight[1];
                if (DeathRightSpriteNum == 3)
                    DeathRightImage = EnemyDeathRight[2];
                if (DeathRightSpriteNum == 4)
                    DeathRightImage = EnemyDeathRight[3];
                if (DeathRightSpriteNum == 5)
                    DeathRightImage = EnemyDeathRight[4];
                if (DeathRightSpriteNum == 6)
                    DeathRightImage = EnemyDeathRight[5];

                if (DeathLeftSpriteNum == 1)
                    DeathLeftImage = EnemyDeathLeft[0];
                if (DeathLeftSpriteNum == 2)
                    DeathLeftImage = EnemyDeathLeft[1];
                if (DeathLeftSpriteNum == 3)
                    DeathLeftImage = EnemyDeathLeft[2];
                if (DeathLeftSpriteNum == 4)
                    DeathLeftImage = EnemyDeathLeft[3];
                if (DeathLeftSpriteNum == 5)
                    DeathLeftImage = EnemyDeathLeft[4];
                if (DeathLeftSpriteNum == 6)
                    DeathLeftImage = EnemyDeathLeft[5];

                if (AttackRightSpriteNum == 1)
                    AttackRightImage = EnemyAttackRight[0];
                if (AttackRightSpriteNum == 2)
                    AttackRightImage = EnemyAttackRight[1];
                if (AttackRightSpriteNum == 3)
                    AttackRightImage = EnemyAttackRight[2];
                if (AttackRightSpriteNum == 4)
                    AttackRightImage = EnemyAttackRight[3];
                if (AttackRightSpriteNum == 5)
                    AttackRightImage = EnemyAttackRight[4];
                if (AttackRightSpriteNum == 6)
                    AttackRightImage = EnemyAttackRight[5];
                if (AttackRightSpriteNum == 7)
                    AttackRightImage = EnemyAttackRight[6];
                if (AttackRightSpriteNum == 8)
                    AttackRightImage = EnemyAttackRight[7];

                if (AttackLeftSpriteNum == 1)
                    AttackLeftImage = EnemyAttackLeft[0];
                if (AttackLeftSpriteNum == 2)
                    AttackLeftImage = EnemyAttackLeft[1];
                if (AttackLeftSpriteNum == 3)
                    AttackLeftImage = EnemyAttackLeft[2];
                if (AttackLeftSpriteNum == 4)
                    AttackLeftImage = EnemyAttackLeft[3];
                if (AttackLeftSpriteNum == 5)
                    AttackLeftImage = EnemyAttackLeft[4];
                if (AttackLeftSpriteNum == 6)
                    AttackLeftImage = EnemyAttackLeft[5];
                if (AttackLeftSpriteNum == 7)
                    AttackLeftImage = EnemyAttackLeft[6];
                if (AttackLeftSpriteNum == 8)
                    AttackLeftImage = EnemyAttackLeft[7];
                break;
            case "Mushroom":
                if (WalkRightSpriteNum == 1)
                    WalkRightImage = EnemyWalkRight[0];
                if (WalkRightSpriteNum == 2)
                    WalkRightImage = EnemyWalkRight[1];
                if (WalkRightSpriteNum == 3)
                    WalkRightImage = EnemyWalkRight[2];
                if (WalkRightSpriteNum == 4)
                    WalkRightImage = EnemyWalkRight[3];
                if (WalkRightSpriteNum == 5)
                    WalkRightImage = EnemyWalkRight[4];
                if (WalkRightSpriteNum == 6)
                    WalkRightImage = EnemyWalkRight[5];
                if (WalkRightSpriteNum == 7)
                    WalkRightImage = EnemyWalkRight[6];
                if (WalkRightSpriteNum == 8)
                    WalkRightImage = EnemyWalkRight[7];

                if (WalkLeftSpriteNum == 1)
                    WalkLeftImage = EnemyWalkLeft[0];
                if (WalkLeftSpriteNum == 2)
                    WalkLeftImage = EnemyWalkLeft[1];
                if (WalkLeftSpriteNum == 3)
                    WalkLeftImage = EnemyWalkLeft[2];
                if (WalkLeftSpriteNum == 4)
                    WalkLeftImage = EnemyWalkLeft[3];
                if (WalkLeftSpriteNum == 5)
                    WalkLeftImage = EnemyWalkLeft[4];
                if (WalkLeftSpriteNum == 6)
                    WalkLeftImage = EnemyWalkLeft[5];
                if (WalkLeftSpriteNum == 7)
                    WalkLeftImage = EnemyWalkLeft[6];
                if (WalkLeftSpriteNum == 8)
                    WalkLeftImage = EnemyWalkLeft[7];

                if (IdleRightSpriteNum == 1)
                    IdleRightImage = EnemyIdleRight[0];
                if (IdleRightSpriteNum == 2)
                    IdleRightImage = EnemyIdleRight[1];
                if (IdleRightSpriteNum == 3)
                    IdleRightImage = EnemyIdleRight[2];
                if (IdleRightSpriteNum == 4)
                    IdleRightImage = EnemyIdleRight[3];
                if (IdleRightSpriteNum == 5)
                    IdleRightImage = EnemyIdleRight[4];
                if (IdleRightSpriteNum == 6)
                    IdleRightImage = EnemyIdleRight[5];

                if (IdleLeftSpriteNum == 1)
                    IdleLeftImage = EnemyIdleLeft[0];
                if (IdleLeftSpriteNum == 2)
                    IdleLeftImage = EnemyIdleLeft[1];
                if (IdleLeftSpriteNum == 3)
                    IdleLeftImage = EnemyIdleLeft[2];
                if (IdleLeftSpriteNum == 4)
                    IdleLeftImage = EnemyIdleLeft[3];
                if (IdleLeftSpriteNum == 5)
                    IdleLeftImage = EnemyIdleLeft[4];
                if (IdleLeftSpriteNum == 6)
                    IdleLeftImage = EnemyIdleLeft[5];

                if (HitRightSpriteNum == 1)
                    HitRightImage = EnemyHitRight[0];
                if (HitRightSpriteNum == 2)
                    HitRightImage = EnemyHitRight[1];
                if (HitRightSpriteNum == 3)
                    HitRightImage = EnemyHitRight[2];
                if (HitRightSpriteNum == 4)
                    HitRightImage = EnemyHitRight[3];

                if (HitLeftSpriteNum == 1)
                    HitLeftImage = EnemyHitLeft[0];
                if (HitLeftSpriteNum == 2)
                    HitLeftImage = EnemyHitLeft[1];
                if (HitLeftSpriteNum == 3)
                    HitLeftImage = EnemyHitLeft[2];
                if (HitLeftSpriteNum == 4)
                    HitLeftImage = EnemyHitLeft[3];

                if (DeathRightSpriteNum == 1)
                    DeathRightImage = EnemyDeathRight[0];
                if (DeathRightSpriteNum == 2)
                    DeathRightImage = EnemyDeathRight[1];
                if (DeathRightSpriteNum == 3)
                    DeathRightImage = EnemyDeathRight[2];
                if (DeathRightSpriteNum == 4)
                    DeathRightImage = EnemyDeathRight[3];
                if (DeathRightSpriteNum == 5)
                    DeathRightImage = EnemyDeathRight[4];
                if (DeathRightSpriteNum == 6)
                    DeathRightImage = EnemyDeathRight[5];

                if (DeathLeftSpriteNum == 1)
                    DeathLeftImage = EnemyDeathLeft[0];
                if (DeathLeftSpriteNum == 2)
                    DeathLeftImage = EnemyDeathLeft[1];
                if (DeathLeftSpriteNum == 3)
                    DeathLeftImage = EnemyDeathLeft[2];
                if (DeathLeftSpriteNum == 4)
                    DeathLeftImage = EnemyDeathLeft[3];
                if (DeathLeftSpriteNum == 5)
                    DeathLeftImage = EnemyDeathLeft[4];
                if (DeathLeftSpriteNum == 6)
                    DeathLeftImage = EnemyDeathLeft[5];

                if (AttackRightSpriteNum == 1)
                    AttackRightImage = EnemyAttackRight[0];
                if (AttackRightSpriteNum == 2)
                    AttackRightImage = EnemyAttackRight[1];
                if (AttackRightSpriteNum == 3)
                    AttackRightImage = EnemyAttackRight[2];
                if (AttackRightSpriteNum == 4)
                    AttackRightImage = EnemyAttackRight[3];
                if (AttackRightSpriteNum == 5)
                    AttackRightImage = EnemyAttackRight[4];
                if (AttackRightSpriteNum == 6)
                    AttackRightImage = EnemyAttackRight[5];
                if (AttackRightSpriteNum == 7)
                    AttackRightImage = EnemyAttackRight[6];
                if (AttackRightSpriteNum == 8)
                    AttackRightImage = EnemyAttackRight[7];

                if (AttackLeftSpriteNum == 1)
                    AttackLeftImage = EnemyAttackLeft[0];
                if (AttackLeftSpriteNum == 2)
                    AttackLeftImage = EnemyAttackLeft[1];
                if (AttackLeftSpriteNum == 3)
                    AttackLeftImage = EnemyAttackLeft[2];
                if (AttackLeftSpriteNum == 4)
                    AttackLeftImage = EnemyAttackLeft[3];
                if (AttackLeftSpriteNum == 5)
                    AttackLeftImage = EnemyAttackLeft[4];
                if (AttackLeftSpriteNum == 6)
                    AttackLeftImage = EnemyAttackLeft[5];
                if (AttackLeftSpriteNum == 7)
                    AttackLeftImage = EnemyAttackLeft[6];
                if (AttackLeftSpriteNum == 8)
                    AttackLeftImage = EnemyAttackLeft[7];
                break;
            case "Orc":
                if (WalkRightSpriteNum == 1)
                    WalkRightImage = EnemyWalkRight[0];
                if (WalkRightSpriteNum == 2)
                    WalkRightImage = EnemyWalkRight[1];
                if (WalkRightSpriteNum == 3)
                    WalkRightImage = EnemyWalkRight[2];
                if (WalkRightSpriteNum == 4)
                    WalkRightImage = EnemyWalkRight[3];
                if (WalkRightSpriteNum == 5)
                    WalkRightImage = EnemyWalkRight[4];
                if (WalkRightSpriteNum == 6)
                    WalkRightImage = EnemyWalkRight[5];
                if (WalkRightSpriteNum == 7)
                    WalkRightImage = EnemyWalkRight[6];
                if (WalkRightSpriteNum == 8)
                    WalkRightImage = EnemyWalkRight[7];

                if (WalkLeftSpriteNum == 1)
                    WalkLeftImage = EnemyWalkLeft[0];
                if (WalkLeftSpriteNum == 2)
                    WalkLeftImage = EnemyWalkLeft[1];
                if (WalkLeftSpriteNum == 3)
                    WalkLeftImage = EnemyWalkLeft[2];
                if (WalkLeftSpriteNum == 4)
                    WalkLeftImage = EnemyWalkLeft[3];
                if (WalkLeftSpriteNum == 5)
                    WalkLeftImage = EnemyWalkLeft[4];
                if (WalkLeftSpriteNum == 6)
                    WalkLeftImage = EnemyWalkLeft[5];
                if (WalkLeftSpriteNum == 7)
                    WalkLeftImage = EnemyWalkLeft[6];
                if (WalkLeftSpriteNum == 8)
                    WalkLeftImage = EnemyWalkLeft[7];

                if (IdleRightSpriteNum == 1)
                    IdleRightImage = EnemyIdleRight[0];
                if (IdleRightSpriteNum == 2)
                    IdleRightImage = EnemyIdleRight[1];
                if (IdleRightSpriteNum == 3)
                    IdleRightImage = EnemyIdleRight[2];
                if (IdleRightSpriteNum == 4)
                    IdleRightImage = EnemyIdleRight[3];
                if (IdleRightSpriteNum == 5)
                    IdleRightImage = EnemyIdleRight[4];
                if (IdleRightSpriteNum == 6)
                    IdleRightImage = EnemyIdleRight[5];

                if (IdleLeftSpriteNum == 1)
                    IdleLeftImage = EnemyIdleLeft[0];
                if (IdleLeftSpriteNum == 2)
                    IdleLeftImage = EnemyIdleLeft[1];
                if (IdleLeftSpriteNum == 3)
                    IdleLeftImage = EnemyIdleLeft[2];
                if (IdleLeftSpriteNum == 4)
                    IdleLeftImage = EnemyIdleLeft[3];
                if (IdleLeftSpriteNum == 5)
                    IdleLeftImage = EnemyIdleLeft[4];
                if (IdleLeftSpriteNum == 6)
                    IdleLeftImage = EnemyIdleLeft[5];

                if (HitRightSpriteNum == 1)
                    HitRightImage = EnemyHitRight[0];
                if (HitRightSpriteNum == 2)
                    HitRightImage = EnemyHitRight[1];
                if (HitRightSpriteNum == 3)
                    HitRightImage = EnemyHitRight[2];
                if (HitRightSpriteNum == 4)
                    HitRightImage = EnemyHitRight[3];

                if (HitLeftSpriteNum == 1)
                    HitLeftImage = EnemyHitLeft[0];
                if (HitLeftSpriteNum == 2)
                    HitLeftImage = EnemyHitLeft[1];
                if (HitLeftSpriteNum == 3)
                    HitLeftImage = EnemyHitLeft[2];
                if (HitLeftSpriteNum == 4)
                    HitLeftImage = EnemyHitLeft[3];

                if (DeathRightSpriteNum == 1)
                    DeathRightImage = EnemyDeathRight[0];
                if (DeathRightSpriteNum == 2)
                    DeathRightImage = EnemyDeathRight[1];
                if (DeathRightSpriteNum == 3)
                    DeathRightImage = EnemyDeathRight[2];
                if (DeathRightSpriteNum == 4)
                    DeathRightImage = EnemyDeathRight[3];

                if (DeathLeftSpriteNum == 1)
                    DeathLeftImage = EnemyDeathLeft[0];
                if (DeathLeftSpriteNum == 2)
                    DeathLeftImage = EnemyDeathLeft[1];
                if (DeathLeftSpriteNum == 3)
                    DeathLeftImage = EnemyDeathLeft[2];
                if (DeathLeftSpriteNum == 4)
                    DeathLeftImage = EnemyDeathLeft[3];

                if (AttackRightSpriteNum == 1)
                    AttackRightImage = EnemyAttackRight[0];
                if (AttackRightSpriteNum == 2)
                    AttackRightImage = EnemyAttackRight[1];
                if (AttackRightSpriteNum == 3)
                    AttackRightImage = EnemyAttackRight[2];
                if (AttackRightSpriteNum == 4)
                    AttackRightImage = EnemyAttackRight[3];
                if (AttackRightSpriteNum == 5)
                    AttackRightImage = EnemyAttackRight[4];
                if (AttackRightSpriteNum == 6)
                    AttackRightImage = EnemyAttackRight[5];

                if (AttackLeftSpriteNum == 1)
                    AttackLeftImage = EnemyAttackLeft[0];
                if (AttackLeftSpriteNum == 2)
                    AttackLeftImage = EnemyAttackLeft[1];
                if (AttackLeftSpriteNum == 3)
                    AttackLeftImage = EnemyAttackLeft[2];
                if (AttackLeftSpriteNum == 4)
                    AttackLeftImage = EnemyAttackLeft[3];
                if (AttackLeftSpriteNum == 5)
                    AttackLeftImage = EnemyAttackLeft[4];
                if (AttackLeftSpriteNum == 6)
                    AttackLeftImage = EnemyAttackLeft[5];
                break;
            case "Hallokin":
                if (WalkRightSpriteNum == 1)
                    WalkRightImage = EnemyWalkRight[0];
                if (WalkRightSpriteNum == 2)
                    WalkRightImage = EnemyWalkRight[1];
                if (WalkRightSpriteNum == 3)
                    WalkRightImage = EnemyWalkRight[2];
                if (WalkRightSpriteNum == 4)
                    WalkRightImage = EnemyWalkRight[3];

                if (WalkLeftSpriteNum == 1)
                    WalkLeftImage = EnemyWalkLeft[0];
                if (WalkLeftSpriteNum == 2)
                    WalkLeftImage = EnemyWalkLeft[1];
                if (WalkLeftSpriteNum == 3)
                    WalkLeftImage = EnemyWalkLeft[2];
                if (WalkLeftSpriteNum == 4)
                    WalkLeftImage = EnemyWalkLeft[3];

                if (IdleRightSpriteNum == 1)
                    IdleRightImage = EnemyIdleRight[0];
                if (IdleRightSpriteNum == 2)
                    IdleRightImage = EnemyIdleRight[1];
                if (IdleRightSpriteNum == 3)
                    IdleRightImage = EnemyIdleRight[2];
                if (IdleRightSpriteNum == 4)
                    IdleRightImage = EnemyIdleRight[3];
                if (IdleRightSpriteNum == 5)
                    IdleRightImage = EnemyIdleRight[4];
                if (IdleRightSpriteNum == 6)
                    IdleRightImage = EnemyIdleRight[5];

                if (IdleLeftSpriteNum == 1)
                    IdleLeftImage = EnemyIdleLeft[0];
                if (IdleLeftSpriteNum == 2)
                    IdleLeftImage = EnemyIdleLeft[1];
                if (IdleLeftSpriteNum == 3)
                    IdleLeftImage = EnemyIdleLeft[2];
                if (IdleLeftSpriteNum == 4)
                    IdleLeftImage = EnemyIdleLeft[3];
                if (IdleLeftSpriteNum == 5)
                    IdleLeftImage = EnemyIdleLeft[4];
                if (IdleLeftSpriteNum == 6)
                    IdleLeftImage = EnemyIdleLeft[5];

                if (HitRightSpriteNum == 1)
                    HitRightImage = EnemyHitRight[0];

                if (HitLeftSpriteNum == 1)
                    HitLeftImage = EnemyHitLeft[0];

                if (DeathRightSpriteNum == 1)
                    DeathRightImage = EnemyDeathRight[0];
                if (DeathRightSpriteNum == 2)
                    DeathRightImage = EnemyDeathRight[1];
                if (DeathRightSpriteNum == 3)
                    DeathRightImage = EnemyDeathRight[2];

                if (DeathLeftSpriteNum == 1)
                    DeathLeftImage = EnemyDeathLeft[0];
                if (DeathLeftSpriteNum == 2)
                    DeathLeftImage = EnemyDeathLeft[1];
                if (DeathLeftSpriteNum == 3)
                    DeathLeftImage = EnemyDeathLeft[2];

                if (AttackRightSpriteNum == 1)
                    AttackRightImage = EnemyAttackRight[0];
                if (AttackRightSpriteNum == 2)
                    AttackRightImage = EnemyAttackRight[1];
                if (AttackRightSpriteNum == 3)
                    AttackRightImage = EnemyAttackRight[2];
                if (AttackRightSpriteNum == 4)
                    AttackRightImage = EnemyAttackRight[3];
                if (AttackRightSpriteNum == 5)
                    AttackRightImage = EnemyAttackRight[4];
                if (AttackRightSpriteNum == 6)
                    AttackRightImage = EnemyAttackRight[5];

                if (AttackLeftSpriteNum == 1)
                    AttackLeftImage = EnemyAttackLeft[0];
                if (AttackLeftSpriteNum == 2)
                    AttackLeftImage = EnemyAttackLeft[1];
                if (AttackLeftSpriteNum == 3)
                    AttackLeftImage = EnemyAttackLeft[2];
                if (AttackLeftSpriteNum == 4)
                    AttackLeftImage = EnemyAttackLeft[3];
                if (AttackLeftSpriteNum == 5)
                    AttackLeftImage = EnemyAttackLeft[4];
                if (AttackLeftSpriteNum == 6)
                    AttackLeftImage = EnemyAttackLeft[5];
                break;
            case "Shadowed":
                if (WalkRightSpriteNum == 1)
                    WalkRightImage = EnemyWalkRight[0];
                if (WalkRightSpriteNum == 2)
                    WalkRightImage = EnemyWalkRight[1];
                if (WalkRightSpriteNum == 3)
                    WalkRightImage = EnemyWalkRight[2];
                if (WalkRightSpriteNum == 4)
                    WalkRightImage = EnemyWalkRight[3];
                if (WalkRightSpriteNum == 5)
                    WalkRightImage = EnemyWalkRight[4];
                if (WalkRightSpriteNum == 6)
                    WalkRightImage = EnemyWalkRight[5];
                if (WalkRightSpriteNum == 7)
                    WalkRightImage = EnemyWalkRight[6];
                if (WalkRightSpriteNum == 8)
                    WalkRightImage = EnemyWalkRight[7];

                if (WalkLeftSpriteNum == 1)
                    WalkLeftImage = EnemyWalkLeft[0];
                if (WalkLeftSpriteNum == 2)
                    WalkLeftImage = EnemyWalkLeft[1];
                if (WalkLeftSpriteNum == 3)
                    WalkLeftImage = EnemyWalkLeft[2];
                if (WalkLeftSpriteNum == 4)
                    WalkLeftImage = EnemyWalkLeft[3];
                if (WalkLeftSpriteNum == 5)
                    WalkLeftImage = EnemyWalkLeft[4];
                if (WalkLeftSpriteNum == 6)
                    WalkLeftImage = EnemyWalkLeft[5];
                if (WalkLeftSpriteNum == 7)
                    WalkLeftImage = EnemyWalkLeft[6];
                if (WalkLeftSpriteNum == 8)
                    WalkLeftImage = EnemyWalkLeft[7];

                if (IdleRightSpriteNum == 1)
                    IdleRightImage = EnemyIdleRight[0];
                if (IdleRightSpriteNum == 2)
                    IdleRightImage = EnemyIdleRight[1];
                if (IdleRightSpriteNum == 3)
                    IdleRightImage = EnemyIdleRight[2];
                if (IdleRightSpriteNum == 4)
                    IdleRightImage = EnemyIdleRight[3];
                if (IdleRightSpriteNum == 5)
                    IdleRightImage = EnemyIdleRight[4];
                if (IdleRightSpriteNum == 6)
                    IdleRightImage = EnemyIdleRight[5];
                if (IdleRightSpriteNum == 7)
                    IdleRightImage = EnemyIdleRight[6];
                if (IdleRightSpriteNum == 8)
                    IdleRightImage = EnemyIdleRight[7];

                if (IdleLeftSpriteNum == 1)
                    IdleLeftImage = EnemyIdleLeft[0];
                if (IdleLeftSpriteNum == 2)
                    IdleLeftImage = EnemyIdleLeft[1];
                if (IdleLeftSpriteNum == 3)
                    IdleLeftImage = EnemyIdleLeft[2];
                if (IdleLeftSpriteNum == 4)
                    IdleLeftImage = EnemyIdleLeft[3];
                if (IdleLeftSpriteNum == 5)
                    IdleLeftImage = EnemyIdleLeft[4];
                if (IdleLeftSpriteNum == 6)
                    IdleLeftImage = EnemyIdleLeft[5];
                if (IdleLeftSpriteNum == 7)
                    IdleLeftImage = EnemyIdleLeft[6];
                if (IdleLeftSpriteNum == 8)
                    IdleLeftImage = EnemyIdleLeft[7];

                if (HitRightSpriteNum == 1)
                    HitRightImage = EnemyHitRight[0];

                if (HitLeftSpriteNum == 1)
                    HitLeftImage = EnemyHitLeft[0];

                if (DeathRightSpriteNum == 1)
                    DeathRightImage = EnemyDeathRight[0];
                if (DeathRightSpriteNum == 2)
                    DeathRightImage = EnemyDeathRight[1];
                if (DeathRightSpriteNum == 3)
                    DeathRightImage = EnemyDeathRight[2];
                if (DeathRightSpriteNum == 4)
                    DeathRightImage = EnemyDeathRight[3];
                if (DeathRightSpriteNum == 5)
                    DeathRightImage = EnemyDeathRight[4];

                if (DeathLeftSpriteNum == 1)
                    DeathLeftImage = EnemyDeathLeft[0];
                if (DeathLeftSpriteNum == 2)
                    DeathLeftImage = EnemyDeathLeft[1];
                if (DeathLeftSpriteNum == 3)
                    DeathLeftImage = EnemyDeathLeft[2];
                if (DeathLeftSpriteNum == 4)
                    DeathLeftImage = EnemyDeathLeft[3];
                if (DeathLeftSpriteNum == 5)
                    DeathLeftImage = EnemyDeathLeft[4];

                if (AttackRightSpriteNum == 1)
                    AttackRightImage = EnemyAttackRight[0];
                if (AttackRightSpriteNum == 2)
                    AttackRightImage = EnemyAttackRight[1];
                if (AttackRightSpriteNum == 3)
                    AttackRightImage = EnemyAttackRight[2];
                if (AttackRightSpriteNum == 4)
                    AttackRightImage = EnemyAttackRight[3];
                if (AttackRightSpriteNum == 5)
                    AttackRightImage = EnemyAttackRight[4];
                if (AttackRightSpriteNum == 6)
                    AttackRightImage = EnemyAttackRight[5];
                if (AttackRightSpriteNum == 7)
                    AttackRightImage = EnemyAttackRight[6];
                if (AttackRightSpriteNum == 8)
                    AttackRightImage = EnemyAttackRight[7];
                if (AttackRightSpriteNum == 9)
                    AttackRightImage = EnemyAttackRight[8];
                if (AttackRightSpriteNum == 10)
                    AttackRightImage = EnemyAttackRight[9];

                if (AttackLeftSpriteNum == 1)
                    AttackLeftImage = EnemyAttackLeft[0];
                if (AttackLeftSpriteNum == 2)
                    AttackLeftImage = EnemyAttackLeft[1];
                if (AttackLeftSpriteNum == 3)
                    AttackLeftImage = EnemyAttackLeft[2];
                if (AttackLeftSpriteNum == 4)
                    AttackLeftImage = EnemyAttackLeft[3];
                if (AttackLeftSpriteNum == 5)
                    AttackLeftImage = EnemyAttackLeft[4];
                if (AttackLeftSpriteNum == 6)
                    AttackLeftImage = EnemyAttackLeft[5];
                if (AttackLeftSpriteNum == 7)
                    AttackLeftImage = EnemyAttackLeft[6];
                if (AttackLeftSpriteNum == 8)
                    AttackLeftImage = EnemyAttackLeft[7];
                if (AttackLeftSpriteNum == 9)
                    AttackLeftImage = EnemyAttackLeft[8];
                if (AttackLeftSpriteNum == 10)
                    AttackLeftImage = EnemyAttackLeft[9];
                break;
            case "Boss":
                if (WalkRightSpriteNum == 1)
                    WalkRightImage = EnemyWalkRight[0];
                if (WalkRightSpriteNum == 2)
                    WalkRightImage = EnemyWalkRight[1];
                if (WalkRightSpriteNum == 3)
                    WalkRightImage = EnemyWalkRight[2];
                if (WalkRightSpriteNum == 4)
                    WalkRightImage = EnemyWalkRight[3];
                if (WalkRightSpriteNum == 5)
                    WalkRightImage = EnemyWalkRight[4];
                if (WalkRightSpriteNum == 6)
                    WalkRightImage = EnemyWalkRight[5];
                if (WalkRightSpriteNum == 7)
                    WalkRightImage = EnemyWalkRight[6];
                if (WalkRightSpriteNum == 8)
                    WalkRightImage = EnemyWalkRight[7];
                if (WalkRightSpriteNum == 9)
                    WalkRightImage = EnemyWalkRight[8];
                if (WalkRightSpriteNum == 10)
                    WalkRightImage = EnemyWalkRight[9];
                if (WalkRightSpriteNum == 11)
                    WalkRightImage = EnemyWalkRight[10];
                if (WalkRightSpriteNum == 12)
                    WalkRightImage = EnemyWalkRight[11];

                if (WalkLeftSpriteNum == 1)
                    WalkLeftImage = EnemyWalkLeft[0];
                if (WalkLeftSpriteNum == 2)
                    WalkLeftImage = EnemyWalkLeft[1];
                if (WalkLeftSpriteNum == 3)
                    WalkLeftImage = EnemyWalkLeft[2];
                if (WalkLeftSpriteNum == 4)
                    WalkLeftImage = EnemyWalkLeft[3];
                if (WalkLeftSpriteNum == 5)
                    WalkLeftImage = EnemyWalkLeft[4];
                if (WalkLeftSpriteNum == 6)
                    WalkLeftImage = EnemyWalkLeft[5];
                if (WalkLeftSpriteNum == 7)
                    WalkLeftImage = EnemyWalkLeft[6];
                if (WalkLeftSpriteNum == 8)
                    WalkLeftImage = EnemyWalkLeft[7];
                if (WalkLeftSpriteNum == 9)
                    WalkLeftImage = EnemyWalkLeft[8];
                if (WalkLeftSpriteNum == 10)
                    WalkLeftImage = EnemyWalkLeft[9];
                if (WalkLeftSpriteNum == 11)
                    WalkLeftImage = EnemyWalkLeft[10];
                if (WalkLeftSpriteNum == 12)
                    WalkLeftImage = EnemyWalkLeft[11];

                if (IdleRightSpriteNum == 1)
                    IdleRightImage = EnemyIdleRight[0];
                if (IdleRightSpriteNum == 2)
                    IdleRightImage = EnemyIdleRight[1];
                if (IdleRightSpriteNum == 3)
                    IdleRightImage = EnemyIdleRight[2];
                if (IdleRightSpriteNum == 4)
                    IdleRightImage = EnemyIdleRight[3];
                if (IdleRightSpriteNum == 5)
                    IdleRightImage = EnemyIdleRight[4];
                if (IdleRightSpriteNum == 6)
                    IdleRightImage = EnemyIdleRight[5];

                if (IdleLeftSpriteNum == 1)
                    IdleLeftImage = EnemyIdleLeft[0];
                if (IdleLeftSpriteNum == 2)
                    IdleLeftImage = EnemyIdleLeft[1];
                if (IdleLeftSpriteNum == 3)
                    IdleLeftImage = EnemyIdleLeft[2];
                if (IdleLeftSpriteNum == 4)
                    IdleLeftImage = EnemyIdleLeft[3];
                if (IdleLeftSpriteNum == 5)
                    IdleLeftImage = EnemyIdleLeft[4];
                if (IdleLeftSpriteNum == 6)
                    IdleLeftImage = EnemyIdleLeft[5];

                if (HitRightSpriteNum == 1)
                    HitRightImage = EnemyHitRight[0];
                if (HitRightSpriteNum == 2)
                    HitRightImage = EnemyHitRight[1];
                if (HitRightSpriteNum == 3)
                    HitRightImage = EnemyHitRight[2];
                if (HitRightSpriteNum == 4)
                    HitRightImage = EnemyHitRight[3];
                if (HitRightSpriteNum == 5)
                    HitRightImage = EnemyHitRight[4];

                if (HitLeftSpriteNum == 1)
                    HitLeftImage = EnemyHitLeft[0];
                if (HitLeftSpriteNum == 2)
                    HitLeftImage = EnemyHitLeft[1];
                if (HitLeftSpriteNum == 3)
                    HitLeftImage = EnemyHitLeft[2];
                if (HitLeftSpriteNum == 4)
                    HitLeftImage = EnemyHitLeft[3];
                if (HitLeftSpriteNum == 5)
                    HitLeftImage = EnemyHitLeft[4];
                

                if (DeathRightSpriteNum == 1)
                    DeathRightImage = EnemyDeathRight[0];
                if (DeathRightSpriteNum == 2)
                    DeathRightImage = EnemyDeathRight[1];
                if (DeathRightSpriteNum == 3)
                    DeathRightImage = EnemyDeathRight[2];
                if (DeathRightSpriteNum == 4)
                    DeathRightImage = EnemyDeathRight[3];
                if (DeathRightSpriteNum == 5)
                    DeathRightImage = EnemyDeathRight[4];
                if (DeathRightSpriteNum == 6)
                    DeathRightImage = EnemyDeathRight[5];
                if (DeathRightSpriteNum == 7)
                    DeathRightImage = EnemyDeathRight[6];
                if (DeathRightSpriteNum == 8)
                    DeathRightImage = EnemyDeathRight[7];
                if (DeathRightSpriteNum == 9)
                    DeathRightImage = EnemyDeathRight[8];
                if (DeathRightSpriteNum == 10)
                    DeathRightImage = EnemyDeathRight[9];
                if (DeathRightSpriteNum == 11)
                    DeathRightImage = EnemyDeathRight[10];
                if (DeathRightSpriteNum == 12)
                    DeathRightImage = EnemyDeathRight[11];
                if (DeathRightSpriteNum == 13)
                    DeathRightImage = EnemyDeathRight[12];
                if (DeathRightSpriteNum == 14)
                    DeathRightImage = EnemyDeathRight[13];
                if (DeathRightSpriteNum == 15)
                    DeathRightImage = EnemyDeathRight[14];
                if (DeathRightSpriteNum == 16)
                    DeathRightImage = EnemyDeathRight[15];
                if (DeathRightSpriteNum == 17)
                    DeathRightImage = EnemyDeathRight[16];
                if (DeathRightSpriteNum == 18)
                    DeathRightImage = EnemyDeathRight[17];
                if (DeathRightSpriteNum == 19)
                    DeathRightImage = EnemyDeathRight[18];
                if (DeathRightSpriteNum == 20)
                    DeathRightImage = EnemyDeathRight[19];
                if (DeathRightSpriteNum == 21)
                    DeathRightImage = EnemyDeathRight[20];
                if (DeathRightSpriteNum == 22)
                    DeathRightImage = EnemyDeathRight[21];

                if (DeathLeftSpriteNum == 1)
                    DeathLeftImage = EnemyDeathLeft[0];
                if (DeathLeftSpriteNum == 2)
                    DeathLeftImage = EnemyDeathLeft[1];
                if (DeathLeftSpriteNum == 3)
                    DeathLeftImage = EnemyDeathLeft[2];
                if (DeathLeftSpriteNum == 4)
                    DeathLeftImage = EnemyDeathLeft[3];
                if (DeathLeftSpriteNum == 5)
                    DeathLeftImage = EnemyDeathLeft[4];
                if (DeathLeftSpriteNum == 6)
                    DeathLeftImage = EnemyDeathLeft[5];
                if (DeathLeftSpriteNum == 7)
                    DeathLeftImage = EnemyDeathLeft[6];
                if (DeathLeftSpriteNum == 8)
                    DeathLeftImage = EnemyDeathLeft[7];
                if (DeathLeftSpriteNum == 9)
                    DeathLeftImage = EnemyDeathLeft[8];
                if (DeathLeftSpriteNum == 10)
                    DeathLeftImage = EnemyDeathLeft[9];
                if (DeathLeftSpriteNum == 11)
                    DeathLeftImage = EnemyDeathLeft[10];
                if (DeathLeftSpriteNum == 12)
                    DeathLeftImage = EnemyDeathLeft[11];
                if (DeathLeftSpriteNum == 13)
                    DeathLeftImage = EnemyDeathLeft[12];
                if (DeathLeftSpriteNum == 14)
                    DeathLeftImage = EnemyDeathLeft[13];
                if (DeathLeftSpriteNum == 15)
                    DeathLeftImage = EnemyDeathLeft[14];
                if (DeathLeftSpriteNum == 16)
                    DeathLeftImage = EnemyDeathLeft[15];
                if (DeathLeftSpriteNum == 17)
                    DeathLeftImage = EnemyDeathLeft[16];
                if (DeathLeftSpriteNum == 18)
                    DeathLeftImage = EnemyDeathLeft[17];
                if (DeathLeftSpriteNum == 19)
                    DeathLeftImage = EnemyDeathLeft[18];
                if (DeathLeftSpriteNum == 20)
                    DeathLeftImage = EnemyDeathLeft[19];
                if (DeathLeftSpriteNum == 21)
                    DeathLeftImage = EnemyDeathLeft[20];
                if (DeathLeftSpriteNum == 22)
                    DeathLeftImage = EnemyDeathLeft[21];

                if (AttackRightSpriteNum == 1)
                    AttackRightImage = EnemyAttackRight[0];
                if (AttackRightSpriteNum == 2)
                    AttackRightImage = EnemyAttackRight[1];
                if (AttackRightSpriteNum == 3)
                    AttackRightImage = EnemyAttackRight[2];
                if (AttackRightSpriteNum == 4)
                    AttackRightImage = EnemyAttackRight[3];
                if (AttackRightSpriteNum == 5)
                    AttackRightImage = EnemyAttackRight[4];
                if (AttackRightSpriteNum == 6)
                    AttackRightImage = EnemyAttackRight[5];
                if (AttackRightSpriteNum == 7)
                    AttackRightImage = EnemyAttackRight[6];
                if (AttackRightSpriteNum == 8)
                    AttackRightImage = EnemyAttackRight[7];
                if (AttackRightSpriteNum == 9)
                    AttackRightImage = EnemyAttackRight[8];
                if (AttackRightSpriteNum == 10)
                    AttackRightImage = EnemyAttackRight[9];
                if (AttackRightSpriteNum == 11)
                    AttackRightImage = EnemyAttackRight[10];
                if (AttackRightSpriteNum == 12)
                    AttackRightImage = EnemyAttackRight[11];
                if (AttackRightSpriteNum == 13)
                    AttackRightImage = EnemyAttackRight[12];
                if (AttackRightSpriteNum == 14)
                    AttackRightImage = EnemyAttackRight[13];
                if (AttackRightSpriteNum == 15)
                    AttackRightImage = EnemyAttackRight[14];

                if (AttackLeftSpriteNum == 1)
                    AttackLeftImage = EnemyAttackLeft[0];
                if (AttackLeftSpriteNum == 2)
                    AttackLeftImage = EnemyAttackLeft[1];
                if (AttackLeftSpriteNum == 3)
                    AttackLeftImage = EnemyAttackLeft[2];
                if (AttackLeftSpriteNum == 4)
                    AttackLeftImage = EnemyAttackLeft[3];
                if (AttackLeftSpriteNum == 5)
                    AttackLeftImage = EnemyAttackLeft[4];
                if (AttackLeftSpriteNum == 6)
                    AttackLeftImage = EnemyAttackLeft[5];
                if (AttackLeftSpriteNum == 7)
                    AttackLeftImage = EnemyAttackLeft[6];
                if (AttackLeftSpriteNum == 8)
                    AttackLeftImage = EnemyAttackLeft[7];
                if (AttackLeftSpriteNum == 9)
                    AttackLeftImage = EnemyAttackLeft[8];
                if (AttackLeftSpriteNum == 10)
                    AttackLeftImage = EnemyAttackLeft[9];
                if (AttackLeftSpriteNum == 11)
                    AttackLeftImage = EnemyAttackLeft[10];
                if (AttackLeftSpriteNum == 12)
                    AttackLeftImage = EnemyAttackLeft[11];
                if (AttackLeftSpriteNum == 13)
                    AttackLeftImage = EnemyAttackLeft[12];
                if (AttackLeftSpriteNum == 14)
                    AttackLeftImage = EnemyAttackLeft[13];
                if (AttackLeftSpriteNum == 15)
                    AttackLeftImage = EnemyAttackLeft[14];
                break;

        }
    }

    public void deicidiMappaSpawn() {
        int n = 1 + (int) (Math.random() * 5);
        MapSpawSet(n);
    }

    public void MapSpawSet(int n) {
        switch (n) {
            case 1:
                MapSpawn = "center";
                break;
            case 2:
                MapSpawn = "down";
                break;
            case 3:
                MapSpawn = "left";
                break;
            case 4:
                MapSpawn = "right";
                break;
            case 5:
                MapSpawn = "top";
                break;
        }

    }

    public void EnemyDirectionSet(int n) {
        switch (n) {
            case 1:
                direction = "up";
                break;
            case 2:
                direction = "down";
                break;
            case 3:
                direction = "left";
                break;
            case 4:
                direction = "right";
                break;
        }

    }

    public void SpwanEnemy() {
        posizionato = false;

        // 1. DEFINIZIONE PARAMETRI HITBOX (Devono essere identici a quelli in update)
        // Se questi valori sono diversi da quelli che usi per muoverti, il bug rimarrà.
        offsetX = 73;
        offsetY = 77;
        corpoWidth = 46;
        corpoHeight = 48;

        // Usiamo un limite di sicurezza per evitare loop infiniti se la zona è troppo
        // piccola
        tentativi = 0;

        while (!posizionato && tentativi < 1000) {
            tentativi++;

            // 2. CALCOLO RANGE DI SPAWN (Garantisce che stayin stia dentro StayinZone)
            // Restringiamo il campo d'azione in modo che il rettangolo verde non esca mai
            // dai bordi
            minX = StayinZone.x - offsetX;
            maxX = StayinZone.x + StayinZone.width - offsetX - corpoWidth;
            minY = StayinZone.y - offsetY;
            maxY = StayinZone.y + StayinZone.height - offsetY - corpoHeight;

            // Generazione posizione casuale in pixel
            this.x = minX + (int) (Math.random() * (maxX - minX));
            this.y = minY + (int) (Math.random() * (maxY - minY));

            // 3. CALCOLO AREA OCCUPATA DAL RETTANGOLO VERDE (In pixel)
            corpoLeft = this.x + offsetX;
            corpoRight = corpoLeft + corpoWidth;
            corpoTop = this.y + offsetY;
            corpoBottom = corpoTop + corpoHeight;

            // 4. TRASFORMAZIONE IN COORDINATE TILE (Griglia della mappa)
            startCol = corpoLeft / gp.tileSize;
            endCol = corpoRight / gp.tileSize;
            startRow = corpoTop / gp.tileSize;
            endRow = corpoBottom / gp.tileSize;

            // 5. CONTROLLO DI OGNI SINGOLO TILE TOCCATO
            collisione = false;
            for (int colonna = startCol; colonna <= endCol; colonna++) {
                for (int riga = startRow; riga <= endRow; riga++) {
                    // Sicurezza per non uscire dall'array della mappa
                    if (colonna >= 0 && colonna < tm.maptileNum.length &&
                            riga >= 0 && riga < tm.maptileNum[0].length) {

                        tileID = tm.maptileNum[colonna][riga];

                        // Controlliamo i tile proibiti (1, 2, 5) definiti nel tuo CollisionManager
                        if (tileID == 1 || tileID == 2 || tileID == 5) {
                            collisione = true;
                            break;
                        }
                    } else {
                        collisione = true; // Se tocca i bordi del mondo è collisione
                        break;
                    }
                }
                if (collisione)
                    break;
            }

            // 6. VERIFICA FINALE
            if (!collisione) {
                // Se arriviamo qui, l'area sotto il rettangolo verde è TUTTA camminabile
                this.stayin.x = corpoLeft;
                this.stayin.y = corpoTop;
                posizionato = true;
            }
        }
    }

    public boolean tileValidi(int tileNum) {
        if (tileNum == 0 || tileNum == 3 || tileNum == 4) {
            return true;
        }
        return false;
    }

    public void randomStarDirection() {
        int n = 1 + (int) (Math.random() * 4);
        EnemyDirectionSet(n);
    }

    public void update() {

        if (gp.cicle.equals("NIGHT") && !trm.isOpen) {

            if (onespawn == true && coin.presounavolta == false) {
                if (pl.PlInteractRect.intersects(coin.pickupZone)) {
                    coin.takeit = true;
                    coin.aumentaNcoin();
                    coin.presounavolta = true;
                }
            }

            if (dying) {
                action = "death";
                updateDeathAnimation();
                return;
            }

            if (invincible) {
                action = "hit";
                updateHitAnimation();

                invincibleCounter++;
                if (invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }

                return;
            }

            stayin.x = x + 73;
            stayin.y = y + 77;
            detectionRange.x = x - 120 + (gp.tileSize / 2);
            detectionRange.y = y - 120 + (gp.tileSize / 2);

            if (tm.currentMap.equals(MapSpawn)) {
                // COME RISOLVERE IL PRBLEMA DELLO SPINNING E DELLO SPAWN SBAGLIATO
                // LA TOLLERANZA
                sT++;
                if (sT >= 60) {
                    sC = 0;
                    sT = 0;
                    if (tollerance < 20) {
                        tollerance++;
                    }
                }

                // Se ha ruotato più di 1 volte in un secondo, è incastrato!
                if (sC > tollerance) {
                    // System.out.println("INCASTRO");
                    SpwanEnemy();
                    sC = 0;
                }

                if (pl.PlInteractRect.intersects(stayin)) {
                    action = "attack";
                    attack();
                } else if (pl.PlInteractRect.intersects(detectionRange)) {
                    action = "walk";
                    followPlayer();
                } else {
                    action = "walk";
                    tick++;
                    if (Stayin()) {
                        if (tick >= 60) {
                            randomStarDirection();
                            tick = 0;
                        }
                        moveNPC();
                    } else {
                        moveNPC();
                    }
                }
            } else {
                tollerance = 1;
            }
        }
    }

    private void updateHitAnimation() {

        switch (this.name) {
            case "Slime":
                if (direction.equals("right") || direction.equals("up")) {
                    HitRightSpriteCounter++;
                    if (HitRightSpriteCounter > 4) {
                        HitRightSpriteNum++;
                        if (HitRightSpriteNum > 4)
                            HitRightSpriteNum = 1;
                        HitRightSpriteCounter = 0;
                    }
                } else {
                    HitLeftSpriteCounter++;
                    if (HitLeftSpriteCounter > 4) {
                        HitLeftSpriteNum++;
                        if (HitLeftSpriteNum > 4)
                            HitLeftSpriteNum = 1;
                        HitLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Mushroom":
                if (direction.equals("right") || direction.equals("up")) {
                    HitRightSpriteCounter++;
                    if (HitRightSpriteCounter > 4) {
                        HitRightSpriteNum++;
                        if (HitRightSpriteNum > 4)
                            HitRightSpriteNum = 1;
                        HitRightSpriteCounter = 0;
                    }
                } else {
                    HitLeftSpriteCounter++;
                    if (HitLeftSpriteCounter > 4) {
                        HitLeftSpriteNum++;
                        if (HitLeftSpriteNum > 4)
                            HitLeftSpriteNum = 1;
                        HitLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Orc":
                if (direction.equals("right") || direction.equals("up")) {
                    HitRightSpriteCounter++;
                    if (HitRightSpriteCounter > 4) {
                        HitRightSpriteNum++;
                        if (HitRightSpriteNum > 4)
                            HitRightSpriteNum = 1;
                        HitRightSpriteCounter = 0;
                    }
                } else {
                    HitLeftSpriteCounter++;
                    if (HitLeftSpriteCounter > 4) {
                        HitLeftSpriteNum++;
                        if (HitLeftSpriteNum > 4)
                            HitLeftSpriteNum = 1;
                        HitLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Hallokin":
                if (direction.equals("right") || direction.equals("up")) {
                    HitRightSpriteCounter++;
                    if (HitRightSpriteCounter > 1) {
                        HitRightSpriteNum++;
                        if (HitRightSpriteNum > 1)
                            HitRightSpriteNum = 1;
                        HitRightSpriteCounter = 0;
                    }
                } else {
                    HitLeftSpriteCounter++;
                    if (HitLeftSpriteCounter > 1) {
                        HitLeftSpriteNum++;
                        if (HitLeftSpriteNum > 1)
                            HitLeftSpriteNum = 1;
                        HitLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Shadowed":
                if (direction.equals("right") || direction.equals("up")) {
                    HitRightSpriteCounter++;
                    if (HitRightSpriteCounter > 1) {
                        HitRightSpriteNum++;
                        if (HitRightSpriteNum > 1)
                            HitRightSpriteNum = 1;
                        HitRightSpriteCounter = 0;
                    }
                } else {
                    HitLeftSpriteCounter++;
                    if (HitLeftSpriteCounter > 1) {
                        HitLeftSpriteNum++;
                        if (HitLeftSpriteNum > 1)
                            HitLeftSpriteNum = 1;
                        HitLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Boss":
                if (direction.equals("right") || direction.equals("up")) {
                    HitRightSpriteCounter++;
                    if (HitRightSpriteCounter > 5) {
                        HitRightSpriteNum++;
                        if (HitRightSpriteNum > 5)
                            HitRightSpriteNum = 1;
                        HitRightSpriteCounter = 0;
                    }
                } else {
                    HitLeftSpriteCounter++;
                    if (HitLeftSpriteCounter > 5) {
                        HitLeftSpriteNum++;
                        if (HitLeftSpriteNum > 5)
                            HitLeftSpriteNum = 1;
                        HitLeftSpriteCounter = 0;
                    }
                }
                break;
        }

    }

    private void updateDeathAnimation() {

        switch (this.name) {
            case "Slime":
                if (direction.equals("right") || direction.equals("up")) {

                    if (DeathRightSpriteNum < 6) {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 10) {
                            DeathRightSpriteNum++;
                            DeathRightSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                } else {

                    if (DeathLeftSpriteNum < 6) {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 10) {
                            DeathLeftSpriteNum++;
                            DeathLeftSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                }
                break;
            case "Mushroom":
                if (direction.equals("right") || direction.equals("up")) {

                    if (DeathRightSpriteNum < 6) {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 10) {
                            DeathRightSpriteNum++;
                            DeathRightSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                } else {

                    if (DeathLeftSpriteNum < 6) {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 10) {
                            DeathLeftSpriteNum++;
                            DeathLeftSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                }
                break;
            case "Orc":
                if (direction.equals("right") || direction.equals("up")) {

                    if (DeathRightSpriteNum < 4) {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 10) {
                            DeathRightSpriteNum++;
                            DeathRightSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                } else {

                    if (DeathLeftSpriteNum < 4) {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 10) {
                            DeathLeftSpriteNum++;
                            DeathLeftSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                }
                break;
            case "Hallokin":
                if (direction.equals("right") || direction.equals("up")) {

                    if (DeathRightSpriteNum < 3) {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 10) {
                            DeathRightSpriteNum++;
                            DeathRightSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                } else {

                    if (DeathLeftSpriteNum < 3) {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 10) {
                            DeathLeftSpriteNum++;
                            DeathLeftSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                }
                break;
            case "Shadowed":
                if (direction.equals("right") || direction.equals("up")) {

                    if (DeathRightSpriteNum < 5) {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 5) {
                            DeathRightSpriteNum++;
                            DeathRightSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                } else {

                    if (DeathLeftSpriteNum < 5) {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 5) {
                            DeathLeftSpriteNum++;
                            DeathLeftSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                }
                break;
            case "Boss":
                if (direction.equals("right") || direction.equals("up")) {

                    if (DeathRightSpriteNum < 22) {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 5) {
                            DeathRightSpriteNum++;
                            DeathRightSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathRightSpriteCounter++;
                        if (DeathRightSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                } else {

                    if (DeathLeftSpriteNum < 22) {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 5) {
                            DeathLeftSpriteNum++;
                            DeathLeftSpriteCounter = 0;
                        }
                    }

                    else {
                        DeathLeftSpriteCounter++;
                        if (DeathLeftSpriteCounter > 60) {
                            alive = false;
                        }
                    }
                }
                break;
        }

    }

    public void followPlayer() {
        // Salva la posizione attuale
        oldX = x;
        oldY = y;

        // inseguimento orizzontale
        if (pl.x > this.x) {
            this.x += 1;
            direction = "right";
        } else if (pl.x < this.x) {
            this.x -= 1;
            direction = "left";
        }

        // Se dopo il movimento X c'è una collisione, torna indietro
        if (checkCollision()) {
            this.x = oldX;
        }

        // inseguimento verticale
        if (pl.y > this.y) {
            this.y += 1;
            if (pl.x == this.x)
                direction = "down";
        } else if (pl.y < this.y) {
            this.y -= 1;
            if (pl.x == this.x)
                direction = "up";
        }

        // se dopo il movimento Y c'è una collisione, torna indietro
        if (checkCollision()) {
            this.y = oldY;
        }

        // aggiorna animazione

        switch (this.name) {
            case "Slime":
                if (direction.equals("right") || direction.equals("up")) {
                    WalkRightSpriteCounter++;
                    if (WalkRightSpriteCounter > 10) {
                        WalkRightSpriteNum = (WalkRightSpriteNum % 8) + 1;
                        WalkRightSpriteCounter = 0;
                    }
                } else {
                    WalkLeftSpriteCounter++;
                    if (WalkLeftSpriteCounter > 10) {
                        WalkLeftSpriteNum = (WalkLeftSpriteNum % 8) + 1;
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Mushroom":
                if (direction.equals("right") || direction.equals("up")) {
                    WalkRightSpriteCounter++;
                    if (WalkRightSpriteCounter > 10) {
                        WalkRightSpriteNum = (WalkRightSpriteNum % 8) + 1;
                        WalkRightSpriteCounter = 0;
                    }
                } else {
                    WalkLeftSpriteCounter++;
                    if (WalkLeftSpriteCounter > 10) {
                        WalkLeftSpriteNum = (WalkLeftSpriteNum % 8) + 1;
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Orc":
                if (direction.equals("right") || direction.equals("up")) {
                    WalkRightSpriteCounter++;
                    if (WalkRightSpriteCounter > 10) {
                        WalkRightSpriteNum = (WalkRightSpriteNum % 8) + 1;
                        WalkRightSpriteCounter = 0;
                    }
                } else {
                    WalkLeftSpriteCounter++;
                    if (WalkLeftSpriteCounter > 10) {
                        WalkLeftSpriteNum = (WalkLeftSpriteNum % 8) + 1;
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Hallokin":
                if (direction.equals("right") || direction.equals("up")) {
                    WalkRightSpriteCounter++;
                    if (WalkRightSpriteCounter > 10) {
                        WalkRightSpriteNum = (WalkRightSpriteNum % 4) + 1;
                        WalkRightSpriteCounter = 0;
                    }
                } else {
                    WalkLeftSpriteCounter++;
                    if (WalkLeftSpriteCounter > 10) {
                        WalkLeftSpriteNum = (WalkLeftSpriteNum % 4) + 1;
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Shadowed":
                if (direction.equals("right") || direction.equals("up")) {
                    WalkRightSpriteCounter++;
                    if (WalkRightSpriteCounter > 10) {
                        WalkRightSpriteNum = (WalkRightSpriteNum % 8) + 1;
                        WalkRightSpriteCounter = 0;
                    }
                } else {
                    WalkLeftSpriteCounter++;
                    if (WalkLeftSpriteCounter > 10) {
                        WalkLeftSpriteNum = (WalkLeftSpriteNum % 8) + 1;
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Boss":
                if (direction.equals("right") || direction.equals("up")) {
                    WalkRightSpriteCounter++;
                    if (WalkRightSpriteCounter > 5) {
                        WalkRightSpriteNum = (WalkRightSpriteNum % 12) + 1;
                        WalkRightSpriteCounter = 0;
                    }
                } else {
                    WalkLeftSpriteCounter++;
                    if (WalkLeftSpriteCounter > 5) {
                        WalkLeftSpriteNum = (WalkLeftSpriteNum % 12) + 1;
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
        }

    }

    public void attack() {

        switch (this.name) {
            case "Slime":
                // Attacco a destra e su
                if (direction.equals("right") || direction.equals("up")) {
                    AttackRightSpriteCounter++;
                    if (AttackRightSpriteCounter > 8) {
                        AttackRightSpriteNum++;
                        AttackRightSpriteCounter = 0;

                        if (AttackRightSpriteNum == 5) {
                            pl.takeDamage(damage);
                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackRightSpriteNum > 8) {
                            AttackRightSpriteNum = 1;
                        }
                    }
                } else {
                    // Attacco a sinistra e giù
                    AttackLeftSpriteCounter++;
                    if (AttackLeftSpriteCounter > 8) {
                        AttackLeftSpriteNum++;
                        AttackLeftSpriteCounter = 0;

                        if (AttackLeftSpriteNum == 5) {
                            pl.takeDamage(damage);

                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackLeftSpriteNum > 8) {
                            AttackLeftSpriteNum = 1;
                        }
                    }
                }
                break;
            case "Mushroom":
                // Attacco a destra e su
                if (direction.equals("right") || direction.equals("up")) {
                    AttackRightSpriteCounter++;
                    if (AttackRightSpriteCounter > 8) {
                        AttackRightSpriteNum++;
                        AttackRightSpriteCounter = 0;

                        if (AttackRightSpriteNum == 5) {
                            pl.takeDamage(damage);
                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackRightSpriteNum > 8) {
                            AttackRightSpriteNum = 1;
                        }
                    }
                } else {
                    // Attacco a sinistra e giù
                    AttackLeftSpriteCounter++;
                    if (AttackLeftSpriteCounter > 8) {
                        AttackLeftSpriteNum++;
                        AttackLeftSpriteCounter = 0;

                        if (AttackLeftSpriteNum == 5) {
                            pl.takeDamage(damage);

                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackLeftSpriteNum > 8) {
                            AttackLeftSpriteNum = 1;
                        }
                    }
                }
                break;
            case "Orc":
                // Attacco a destra e su
                if (direction.equals("right") || direction.equals("up")) {
                    AttackRightSpriteCounter++;
                    if (AttackRightSpriteCounter > 6) {
                        AttackRightSpriteNum++;
                        AttackRightSpriteCounter = 0;

                        if (AttackRightSpriteNum == 4) {
                            pl.takeDamage(damage);
                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackRightSpriteNum > 6) {
                            AttackRightSpriteNum = 1;
                        }
                    }
                } else {
                    // Attacco a sinistra e giù
                    AttackLeftSpriteCounter++;
                    if (AttackLeftSpriteCounter > 6) {
                        AttackLeftSpriteNum++;
                        AttackLeftSpriteCounter = 0;

                        if (AttackLeftSpriteNum == 4) {
                            pl.takeDamage(damage);

                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackLeftSpriteNum > 6) {
                            AttackLeftSpriteNum = 1;
                        }
                    }
                }
                break;
            case "Hallokin":
                // Attacco a destra e su
                if (direction.equals("right") || direction.equals("up")) {
                    AttackRightSpriteCounter++;
                    if (AttackRightSpriteCounter > 6) {
                        AttackRightSpriteNum++;
                        AttackRightSpriteCounter = 0;

                        if (AttackRightSpriteNum == 4) {
                            pl.takeDamage(damage);
                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackRightSpriteNum > 6) {
                            AttackRightSpriteNum = 1;
                        }
                    }
                } else {
                    // Attacco a sinistra e giù
                    AttackLeftSpriteCounter++;
                    if (AttackLeftSpriteCounter > 6) {
                        AttackLeftSpriteNum++;
                        AttackLeftSpriteCounter = 0;

                        if (AttackLeftSpriteNum == 4) {
                            pl.takeDamage(damage);

                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackLeftSpriteNum > 6) {
                            AttackLeftSpriteNum = 1;
                        }
                    }
                }
                break;
            case "Shadowed":
                // Attacco a destra e su
                if (direction.equals("right") || direction.equals("up")) {
                    AttackRightSpriteCounter++;
                    if (AttackRightSpriteCounter > 8) {
                        AttackRightSpriteNum++;
                        AttackRightSpriteCounter = 0;

                        if (AttackRightSpriteNum == 6) {
                            pl.takeDamage(damage);
                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackRightSpriteNum > 8) {
                            AttackRightSpriteNum = 1;
                        }
                    }
                } else {
                    // Attacco a sinistra e giù
                    AttackLeftSpriteCounter++;
                    if (AttackLeftSpriteCounter > 8) {
                        AttackLeftSpriteNum++;
                        AttackLeftSpriteCounter = 0;

                        if (AttackLeftSpriteNum == 6) {
                            pl.takeDamage(damage);

                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackLeftSpriteNum > 8) {
                            AttackLeftSpriteNum = 1;
                        }
                    }
                }
                break;
            case "Boss":
                // Attacco a destra e su
                if (direction.equals("right") || direction.equals("up")) {
                    AttackRightSpriteCounter++;
                    if (AttackRightSpriteCounter > 3) {
                        AttackRightSpriteNum++;
                        AttackRightSpriteCounter = 0;

                        if (AttackRightSpriteNum == 10) {
                            pl.takeDamage(damage);
                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackRightSpriteNum > 15) {
                            AttackRightSpriteNum = 1;
                        }
                    }
                } else {
                    // Attacco a sinistra e giù
                    AttackLeftSpriteCounter++;
                    if (AttackLeftSpriteCounter > 3) {
                        AttackLeftSpriteNum++;
                        AttackLeftSpriteCounter = 0;

                        if (AttackLeftSpriteNum == 10) {
                            pl.takeDamage(damage);

                        }
                        // quando l'animazione finisce ricomincia
                        if (AttackLeftSpriteNum > 15) {
                            AttackLeftSpriteNum = 1;
                        }
                    }
                }
                break;
        }

    }

    public boolean takeDamage(int damage) {
        if (tm.currentMap.equals(MapSpawn)) {
            sd.setFile(8);
            sd.play();
            if (!invincible && !dying) {

                life -= damage;
                invincible = true;
                action = "hit";

                if (life <= 0) {
                    life = 0;
                    die();
                }
                return true;
            }
        } else {
            // System.out.println("nemico non colpito non stessa mappa");
        }
        return false;
    }

    public void die() {
        dying = true;
        if (onespawn == false) {
            onespawn = true;

            nCoins = rand.nextInt(3) + 1;

            if(nCoins==1){
                coin.CoinSpawn(x + 85, y + 83);
            }
            if(nCoins==2){
                coin.CoinSpawn(x + 85, y + 83);
                coin.CoinSpawn(x + 90, y + 90);
            }
            if(nCoins==3){
                coin.CoinSpawn(x + 85, y + 83);
                coin.CoinSpawn(x + 90, y + 90);
                coin.CoinSpawn(x + 80, y + 75);
            }
        }
        action = "death";
    }

    public void randomMove(String dir) {

        switch (this.name) {
            case "Slime":
                if (dir == "up") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "left") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                if (dir == "right") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "down") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Mushroom":
                if (dir == "up") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "left") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                if (dir == "right") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "down") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Orc":
                if (dir == "up") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "left") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                if (dir == "right") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "down") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Hallokin":
                if (dir == "up") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 1;
                        }
                    }
                    WalkRightSpriteCounter = 0;
                }

                if (dir == "left") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 1;
                        }
                    }
                    WalkLeftSpriteCounter = 0;
                }

                if (dir == "right") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }

                if (dir == "down") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;
            case "Shadowed":
                if (dir == "up") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                    }
                    WalkRightSpriteCounter = 0;
                }

                if (dir == "left") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                if (dir == "right") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "down") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;

            case "Boss":
                if (dir == "up") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 9;
                        } else if (WalkRightSpriteNum == 9) {
                            WalkRightSpriteNum = 10;
                        } else if (WalkRightSpriteNum == 10) {
                            WalkRightSpriteNum = 11;
                        } else if (WalkRightSpriteNum == 11) {
                            WalkRightSpriteNum = 12;
                        } else if (WalkRightSpriteNum == 12) {
                            WalkRightSpriteNum = 1;
                        }
                    }
                    WalkRightSpriteCounter = 0;
                }

                if (dir == "left") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 9;
                        } else if (WalkLeftSpriteNum == 9) {
                            WalkLeftSpriteNum = 10;
                        } else if (WalkLeftSpriteNum == 10) {
                            WalkLeftSpriteNum = 11;
                        } else if (WalkLeftSpriteNum == 11) {
                            WalkLeftSpriteNum = 12;
                        } else if (WalkLeftSpriteNum == 12) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                if (dir == "right") {
                    if (WalkRightSpriteCounter > 5) {
                        if (WalkRightSpriteNum == 1) {
                            WalkRightSpriteNum = 2;
                        } else if (WalkRightSpriteNum == 2) {
                            WalkRightSpriteNum = 3;
                        } else if (WalkRightSpriteNum == 3) {
                            WalkRightSpriteNum = 4;
                        } else if (WalkRightSpriteNum == 4) {
                            WalkRightSpriteNum = 5;
                        } else if (WalkRightSpriteNum == 5) {
                            WalkRightSpriteNum = 6;
                        } else if (WalkRightSpriteNum == 6) {
                            WalkRightSpriteNum = 7;
                        } else if (WalkRightSpriteNum == 7) {
                            WalkRightSpriteNum = 8;
                        } else if (WalkRightSpriteNum == 8) {
                            WalkRightSpriteNum = 9;
                        } else if (WalkRightSpriteNum == 9) {
                            WalkRightSpriteNum = 10;
                        } else if (WalkRightSpriteNum == 10) {
                            WalkRightSpriteNum = 11;
                        } else if (WalkRightSpriteNum == 11) {
                            WalkRightSpriteNum = 12;
                        } else if (WalkRightSpriteNum == 12) {
                            WalkRightSpriteNum = 1;
                        }
                        WalkRightSpriteCounter = 0;
                    }
                }
                if (dir == "down") {
                    if (WalkLeftSpriteCounter > 5) {
                        if (WalkLeftSpriteNum == 1) {
                            WalkLeftSpriteNum = 2;
                        } else if (WalkLeftSpriteNum == 2) {
                            WalkLeftSpriteNum = 3;
                        } else if (WalkLeftSpriteNum == 3) {
                            WalkLeftSpriteNum = 4;
                        } else if (WalkLeftSpriteNum == 4) {
                            WalkLeftSpriteNum = 5;
                        } else if (WalkLeftSpriteNum == 5) {
                            WalkLeftSpriteNum = 6;
                        } else if (WalkLeftSpriteNum == 6) {
                            WalkLeftSpriteNum = 7;
                        } else if (WalkLeftSpriteNum == 7) {
                            WalkLeftSpriteNum = 8;
                        } else if (WalkLeftSpriteNum == 8) {
                            WalkLeftSpriteNum = 9;
                        } else if (WalkLeftSpriteNum == 9) {
                            WalkLeftSpriteNum = 10;
                        } else if (WalkLeftSpriteNum == 10) {
                            WalkLeftSpriteNum = 11;
                        } else if (WalkLeftSpriteNum == 11) {
                            WalkLeftSpriteNum = 12;
                        } else if (WalkLeftSpriteNum == 12) {
                            WalkLeftSpriteNum = 1;
                        }
                        WalkLeftSpriteCounter = 0;
                    }
                }
                break;

        }

    }

    public void moveNPC() {
        originalX = x;
        originalY = y;

        if (direction.equals("up")) {
            y--;
            WalkRightSpriteCounter++;
            randomMove("up");
        } else if (direction.equals("down")) {
            y++;
            WalkLeftSpriteCounter++;
            randomMove("down");
        } else if (direction.equals("left")) {
            x--;
            WalkLeftSpriteCounter++;
            randomMove("left");
        } else if (direction.equals("right")) {
            x++;
            WalkRightSpriteCounter++;
            randomMove("right");
        }

        // Controlla se collide con tile non camminabili
        if (checkCollision()) {
            // ritorna alle x e y precedenti
            x = originalX;
            y = originalY;
            // cambia direzione
            changeDirection();
            sC++;
        }
    }

    public boolean Stayin() {
        if (!StayinZone.intersects(stayin)) {
            return false;
        }
        return true;
    }

    // Controlla collisioni
    public boolean checkCollision() {
        // hitbox
        

        switch(this.name){
            case "Slime":
                EnemyHitboxX = x + 84;
                EnemyHitboxY = y + 97;
                EnemyHitboxWidth = 20;
                EnemyHitboxHeight = 20;
                break;
            case "Mushroom":
                EnemyHitboxX = x + 84;
                EnemyHitboxY = y + 97;
                EnemyHitboxWidth = 20;
                EnemyHitboxHeight = 20;
                break;
            case "Orc":
                EnemyHitboxX = x + 84;
                EnemyHitboxY = y + 97;
                EnemyHitboxWidth = 20;
                EnemyHitboxHeight = 20;
                break;
            case "Hallokin":
                EnemyHitboxX = x + 84;
                EnemyHitboxY = y + 97;
                EnemyHitboxWidth = 20;
                EnemyHitboxHeight = 20;
                break;
            case "Shadowed":
                EnemyHitboxX = x + 84;
                EnemyHitboxY = y + 97;
                EnemyHitboxWidth = 20;
                EnemyHitboxHeight = 20;
                break;
            case "Boss":
                EnemyHitboxX = x + 75;
                EnemyHitboxY = y + 120;
                EnemyHitboxWidth = 50;
                EnemyHitboxHeight = 60;
                break;
        }

        

        // lati della hitbox
        leftCol = EnemyHitboxX / gp.tileSize;
        rightCol = (EnemyHitboxX + EnemyHitboxWidth) / gp.tileSize;
        topRow = EnemyHitboxY / gp.tileSize;
        bottomRow = (EnemyHitboxY + EnemyHitboxHeight) / gp.tileSize;

        // controlla se i lati sono su un tile non camminabile
        return isSolidTile(leftCol, topRow) || isSolidTile(rightCol, topRow) || isSolidTile(leftCol, bottomRow)
                || isSolidTile(rightCol, bottomRow);
    }

    // controlla se è in un tile non camminabile
    private boolean isSolidTile(int col, int row) {
        // controllo sui limiti della mappa
        if (col < 0 || col >= tm.maptileNum.length || row < 0 || row >= tm.maptileNum.length) {
            return true;
        }

        int tileNum = tm.maptileNum[col][row];
        return tileNum == 1 || tileNum == 2 || tileNum == 5;
    }

    // cambia direzione
    private void changeDirection() {
        // scegli una direziona casuale diversa da quella attuale
        String[] directions = { "up", "down", "left", "right" };
        String newDirection;
        do {
            int randomIndex = (int) (Math.random() * 4);
            newDirection = directions[randomIndex];
        } while (newDirection.equals(direction));

        direction = newDirection;
    }
}
