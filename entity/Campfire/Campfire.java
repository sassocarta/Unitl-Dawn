package entity.Campfire;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import entity.Player.Player;
import main.GamePanel;
import tile.TileManager;

public class Campfire{
    Rectangle HealRect;
    Rectangle StayinZone;
    GamePanel gp;
    TileManager tm;
    Player pl;
    String urlCampFire = "/src/CampFire/campfire";
    String MapSpaw = "";

    public int x;
    public int y;

    int contatore = 0;

    public int CampFireSpriteCounter = 0;
    public int CampFireSpriteNum = 1;
    
    BufferedImage FrameToDraw;
    BufferedImage []  CampFireFrames;

    public Campfire(GamePanel gp, Player pl,TileManager tm)
    {
        this.gp = gp;
        this.pl = pl;
        this.tm = tm;
        HealRect = new Rectangle(0,0,150,150);
        StayinZone = new Rectangle(96, 96, 576, 384);
        CampFireFrames = new BufferedImage[14];
        GetCampfireImages();
        deicidiMappaSpawn();
        SpwanCampFire();

    }

    public void deicidiMappaSpawn() {
        int n = 1 + (int) (Math.random() * 5);
        MapSpawSet(n);
    }

    public void MapSpawSet(int n) {
        switch (n) {
            case 1:
                MapSpaw = "center";
                break;
            case 2:
                MapSpaw = "down";
                break;
            case 3:
                MapSpaw = "left";
                break;
            case 4:
                MapSpaw = "right";
                break;
            case 5:
                MapSpaw = "top";
                break;
        }

    }

    public void GetCampfireImages()
    {
         try {
            for(int i = 0; i < 14; i++)
            {
            CampFireFrames[i] =  ImageIO.read(getClass().getResource(urlCampFire + (i + 1) + ".png"));
            }
            //NPCfaces[0] = ImageIO.read(getClass().getResource("/src/NPC/NPC_type1/FACE/face1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(gp.cicle.equals("DAY"))
            {
            HealRect.x = x - 50;
            HealRect.y = y - 50;
            if (tm.currentMap.equals(MapSpaw)) {   
            moveFrame();
            contatore++;

            if(contatore >= 60){
                contatore = 0;
                if(pl.PlInteractRect.intersects(HealRect))
                    {
                        if(pl.life < 100)
                        {
                            pl.life += 5;
                        }
                    }
            }

            }   
        } 
    }


    public void moveFrame()
    {
        CampFireSpriteCounter++; 
                if (CampFireSpriteCounter > 5) {
                    if (CampFireSpriteNum == 1) {
                        CampFireSpriteNum = 2;
                    } else if (CampFireSpriteNum == 2) {
                        CampFireSpriteNum = 3;

                    } else if (CampFireSpriteNum == 3) {
                        CampFireSpriteNum = 4;
                    }
                    else if (CampFireSpriteNum == 4) {
                        CampFireSpriteNum = 5;
                    }
                    else if (CampFireSpriteNum == 5) {
                        CampFireSpriteNum = 6;
                    }
                    else if (CampFireSpriteNum == 6) {
                        CampFireSpriteNum = 7;
                    }
                    else if (CampFireSpriteNum == 7) {
                        CampFireSpriteNum = 8;
                    }
                    else if (CampFireSpriteNum == 8) {
                        CampFireSpriteNum = 9;
                    }
                    else if (CampFireSpriteNum == 9) {
                        CampFireSpriteNum = 10;
                    }
                    else if (CampFireSpriteNum == 10) {
                        CampFireSpriteNum = 11;
                    }
                    else if (CampFireSpriteNum == 11) {
                        CampFireSpriteNum = 12;
                    }
                    else if (CampFireSpriteNum == 12) {
                        CampFireSpriteNum = 13;
                    }
                    else if (CampFireSpriteNum == 13) {
                        CampFireSpriteNum = 14;
                    }
                    else if (CampFireSpriteNum == 14) {
                        CampFireSpriteNum = 1;
                    }
                    CampFireSpriteCounter = 0;
                }
    }
    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.GREEN);
        //g2.fill(HealRect);
    if(gp.cicle == "DAY"){

            if (!tm.currentMap.equals(MapSpaw)) {
                return;
            }
            spriteSet();

            g2.drawImage(FrameToDraw, x, y, gp.tileSize, gp.tileSize, null);
        }
    }

    public void SpwanCampFire() {
        boolean posizionato = false;
        
        // 1. DEFINIZIONE PARAMETRI HITBOX (Devono essere identici a quelli in update)
        // Se questi valori sono diversi da quelli che usi per muoverti, il bug rimarrà.
        int offsetX = 84; 
        int offsetY = 97;
        int corpoWidth = 20;
        int corpoHeight = 20;

        // Usiamo un limite di sicurezza per evitare loop infiniti se la zona è troppo piccola
        int tentativi = 0;

        while (!posizionato && tentativi < 1000) {
            tentativi++;

            // 2. CALCOLO RANGE DI SPAWN (Garantisce che stayin stia dentro StayinZone)
            // Restringiamo il campo d'azione in modo che il rettangolo verde non esca mai dai bordi
            int minX = StayinZone.x - offsetX;
            int maxX = StayinZone.x + StayinZone.width - offsetX - corpoWidth;
            int minY = StayinZone.y - offsetY;
            int maxY = StayinZone.y + StayinZone.height - offsetY - corpoHeight;

            // Generazione posizione casuale in pixel
            this.x = minX + (int) (Math.random() * (maxX - minX));
            this.y = minY + (int) (Math.random() * (maxY - minY));

            // 3. CALCOLO AREA OCCUPATA DAL RETTANGOLO VERDE (In pixel)
            int corpoLeft = this.x + offsetX;
            int corpoRight = corpoLeft + corpoWidth;
            int corpoTop = this.y + offsetY;
            int corpoBottom = corpoTop + corpoHeight;

            // 4. TRASFORMAZIONE IN COORDINATE TILE (Griglia della mappa)
            int startCol = corpoLeft / gp.tileSize;
            int endCol = corpoRight / gp.tileSize;
            int startRow = corpoTop / gp.tileSize;
            int endRow = corpoBottom / gp.tileSize;

            // 5. CONTROLLO DI OGNI SINGOLO TILE TOCCATO
            boolean collisione = false;
            for (int colonna = startCol; colonna <= endCol; colonna++) {
                for (int riga = startRow; riga <= endRow; riga++) {
                    // Sicurezza per non uscire dall'array della mappa
                    if (colonna >= 0 && colonna < tm.maptileNum.length && 
                        riga >= 0 && riga < tm.maptileNum[0].length) {
                        
                        int tileID = tm.maptileNum[colonna][riga];
                        
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
                if (collisione) break;
            }

            // 6. VERIFICA FINALE
            if (!collisione) {
                // Se arriviamo qui, l'area sotto il rettangolo verde è TUTTA camminabile
                this.HealRect.x = corpoLeft;
                this.HealRect.y = corpoTop;
                posizionato = true;
            }
        }
    }




    public void spriteSet() {
        if (CampFireSpriteNum == 1)
            FrameToDraw =  CampFireFrames[0];
        if (CampFireSpriteNum == 2)
            FrameToDraw = CampFireFrames[1];
        if (CampFireSpriteNum == 3)
            FrameToDraw = CampFireFrames[2];
        if (CampFireSpriteNum == 4)
            FrameToDraw = CampFireFrames[3];
        if (CampFireSpriteNum == 5)
            FrameToDraw = CampFireFrames[4];
        if (CampFireSpriteNum == 6)
            FrameToDraw = CampFireFrames[5];
        if (CampFireSpriteNum == 7)
            FrameToDraw = CampFireFrames[6];
        if (CampFireSpriteNum == 8)
            FrameToDraw = CampFireFrames[7];
        if (CampFireSpriteNum == 9)
            FrameToDraw = CampFireFrames[8];
        if (CampFireSpriteNum == 10)
            FrameToDraw = CampFireFrames[9];
        if (CampFireSpriteNum == 11)
            FrameToDraw = CampFireFrames[10];
        if (CampFireSpriteNum == 12)
            FrameToDraw = CampFireFrames[11];
        if (CampFireSpriteNum == 13)
            FrameToDraw = CampFireFrames[12];
        if (CampFireSpriteNum == 14)
            FrameToDraw = CampFireFrames[13];
        if (CampFireSpriteNum == 15)
            FrameToDraw = CampFireFrames[14];

        
    }
}

