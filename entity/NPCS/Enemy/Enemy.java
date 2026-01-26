package entity.NPCS.Enemy;

import java.awt.Color;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;


public class Enemy extends Enemy_Manager{
    int tick = 0;
    Rectangle stayin = null;
    //WalkRight
    String urlWalkRight;
    int NFWalkRight;
    //WalkLeft
    String urlWalkLeft;
    int NFWalkLeft;
    //IdleRight
    String urlIdleRight;
    int NFIdleRight;
    //IdleLeft
    String urlIdleLeft;
    int NFIdleLeft;
    //HitRight
    String urlHitRight;
    int NFHitRight;
    //HitLeft
    String urlHitLeft;
    int NFHitLeft;
    //DeathRight
    String urlDeathRight;
    int NFDeathRight;
    //DeathLeft
    String urlDeathLeft;
    int NFDeathLeft;
    //AttackRight
    String urlAttackRight;
    int NFAttackRight;
    //AttackLeft
    String urlAttackLeft;
    int NFAttackLeft;

    public Enemy(GamePanel gp, 
                 Player pl, 
                 Sound sd, 
                 TileManager tm,
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
                 String urlAttackLeft) {

        this.gp = gp;
        this.tm = tm;
        this.pl = pl;
        this.sd = sd;

        StayinZone = new Rectangle(96, 96, 576, 384);
        stayin = new Rectangle(0, 0, 46, 48);

        EnemyWalkRight = new BufferedImage[8];
        EnemyWalkLeft = new BufferedImage[8];
        EnemyIdleRight = new BufferedImage[6];
        EnemyIdleLeft = new BufferedImage[6];
        EnemyHitRight = new BufferedImage[4];
        EnemyHitLeft = new BufferedImage[4];
        EnemyDeathRight = new BufferedImage[6];
        EnemyDeathLeft = new BufferedImage[6];
        EnemyAttackRight = new BufferedImage[8];
        EnemyAttackLeft = new BufferedImage[8];

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

        GetAllEnemyImages();
        deicidiMappaSpawn();
        SpwanEnemy();
        randomStarDirection();

        this.x = col * gp.tileSize;
        this.y = row * gp.tileSize;
        
        spriteSet();
    }

    public void GetImagesWalkRight(int NFWalkRight, String urlWalkRight){
        try {
            for(int i = 0; i < NFWalkRight; i++)
            {
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

    public void GetImagesWalkLeft(int NFWalkLeft, String urlWalkLeft){
        try {
            for(int i = 0; i < NFWalkLeft; i++)
            {
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

    public void GetImagesIdleRight(int NFIdleRight, String urlIdleRight){
        try {
            for(int i = 0; i < NFIdleRight; i++)
            {
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

    public void GetImagesIdleLeft(int NFIdleLeft, String urlIdleLeft){
        try {
            for(int i = 0; i < NFIdleLeft; i++)
            {
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

    public void GetImagesHitRight(int NFHitRight, String urlHitRight){
        try {
            for(int i = 0; i < NFHitRight; i++)
            {
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

    public void GetImagesHitLeft(int NFHitLeft, String urlHitLeft){
        try {
            for(int i = 0; i < NFHitLeft; i++)
            {
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

    public void GetImagesDeathRight(int NFDeathRight, String urlDeathRight){
        try {
            for(int i = 0; i < NFDeathRight; i++)
            {
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

    public void GetImagesDeathLeft(int NFDeathLeft, String urlDeathLeft){
        try {
            for(int i = 0; i < NFDeathLeft; i++)
            {
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

    public void GetImagesAttackRight(int NFAttackRight, String urlAttackRight){
        try {
            for(int i = 0; i < NFAttackRight; i++)
            {
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

    public void GetImagesAttackLeft(int NFAttackLeft, String urlAttackLeft){
        try {
            for(int i = 0; i < NFAttackLeft; i++)
            {
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
        GetImagesWalkRight(NFWalkRight,urlWalkRight);
        GetImagesWalkLeft(NFWalkLeft,urlWalkLeft);
        GetImagesIdleRight(NFIdleRight,urlIdleRight);
        GetImagesIdleLeft(NFIdleLeft,urlIdleLeft);
        GetImagesHitRight(NFHitRight,urlHitRight);
        GetImagesHitLeft(NFHitLeft,urlHitLeft);
        GetImagesDeathRight(NFDeathRight,urlDeathRight);
        GetImagesDeathLeft(NFDeathLeft,urlDeathLeft);
        GetImagesAttackRight(NFAttackRight,urlAttackRight);
        GetImagesAttackLeft(NFAttackLeft,urlAttackLeft);
    }

    public void draw(Graphics2D g2) {
        if (!tm.currentMap.equalsIgnoreCase(MapSpawn)) return;

        spriteSet();
        BufferedImage image = null;

        switch (direction) {
            case "up":
            case "right":
                image = (WalkRightImage != null) ? WalkRightImage : EnemyIdleRight[0];
                break;
            case "down":
            case "left":
                image = (WalkLeftImage != null) ? WalkLeftImage : EnemyIdleLeft[0];
                break;
        }

        //Se l'immagine è null si utilizza il primo frame dell'idle come backup
        if (image == null && EnemyIdleRight[0] != null) {
            image = EnemyIdleRight[0];
        }

        //disegna
        if (image != null) {
            g2.drawImage(image, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
        } else {
            //Se le immagini non sono state caricate disegna un rettangolo rosso
            g2.setColor(Color.RED);
            g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        }
    }

    public void spriteSet() {
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
        do {
            col = (int) (Math.random() * gp.MaxScreenCol / 2);
            row = (int) (Math.random() * gp.MaxScreenRow / 2);
            tileNum = tm.maptileNum[col][row];
        } while (!tileValidi(tileNum));

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
        stayin.x = x + 70;
        stayin.y = y + 70;
        if (tm.currentMap.equals(MapSpawn)) {
            if (!pl.PlInteractRect.intersects(stayin)) {
                tick++;
                if (Stayin() == true) {
                    if (tick >= 60) {
                        randomStarDirection();
                        tick = 0;
                    }
                    moveNPC();
                } else {
                    if (direction == "up") {
                        direction = "down";
                    } else if (direction == "down") {
                        direction = "up";
                    } else if (direction == "right") {
                        direction = "left";
                    } else if (direction == "left") {
                        direction = "right";
                    }
                    moveNPC();
                    tick = 0;
                }
            } else {
                direction = "down";
                uone = false;
            }
        }

    }

    public void randomMove(String dir) {
        if (dir == "up") {
            if (WalkRightSpriteCounter > 10) {
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
            if (WalkLeftSpriteCounter > 10) {
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
            if (WalkRightSpriteCounter > 10) {
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
            if (WalkLeftSpriteCounter > 10) {
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

    }

    public void moveNPC() {
        int originalX = x;
        int originalY = y;
        
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
        
        //Controlla de collide con tile no camminabili
        if (checkCollision()) {
            //ritorna alle x e y precedenti
            x = originalX;
            y = originalY;
            //cambia direzione
            changeDirection();
        }
    }

    public boolean Stayin() {
        if (!StayinZone.intersects(stayin)) {
            return false;
        }
        return true;
    }
    //Controlla collisioni
    public boolean checkCollision() {
        //hitbox
        int EnemyHitboxX = x + 84;
        int EnemyHitboxY = y + 97;
        int EnemyHitboxWidth = 20;
        int EnemyHitboxHeight = 20;
        
        //lati della hitbox
        int leftCol = EnemyHitboxX / gp.tileSize;
        int rightCol = (EnemyHitboxX + EnemyHitboxWidth) / gp.tileSize;
        int topRow = EnemyHitboxY / gp.tileSize;
        int bottomRow = (EnemyHitboxY + EnemyHitboxHeight) / gp.tileSize;
        
        //controlla se i lati sono su un tile non camminabile
        return isSolidTile(leftCol, topRow) || isSolidTile(rightCol, topRow) || isSolidTile(leftCol, bottomRow) || isSolidTile(rightCol, bottomRow);
    }
    //controlla se è in un tile non camminabile
    private boolean isSolidTile(int col, int row) {
        //controllo sui limiti della mappa
        if (col < 0 || col >= tm.maptileNum.length || row < 0 || row >= tm.maptileNum[0].length) {
            return true;
        }
        
        int tileNum = tm.maptileNum[col][row];
        return tileNum == 1 || tileNum == 2 || tileNum == 5;
    }
    //cambia direzione
    private void changeDirection() {
        //scegli una direziona casuale diversa da quella attuale
        String[] directions = {"up", "down", "left", "right"};
        String newDirection;
        do {
            int randomIndex = (int) (Math.random() * 4);
            newDirection = directions[randomIndex];
        } while (newDirection.equals(direction));
        
        direction = newDirection;
    }
}
