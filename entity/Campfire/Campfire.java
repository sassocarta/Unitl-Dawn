package entity.Campfire;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import entity.Player.Player;
import main.GamePanel;
import tile.TileManager;

public class Campfire {
    Rectangle HealRect;
    Rectangle StayinZone;
    GamePanel gp;
    TileManager tm;
    Player pl;
    String urlCampFire = "/src/CampFire/campfire";
    String MapSpaw = "";

    public int x;
    public int y;
    boolean stampaPlusvita = false;
    int heal = 5;

    int contatore = 0;

    public int CampFireSpriteCounter = 0;
    public int CampFireSpriteNum = 1;

    BufferedImage FrameToDraw;
    BufferedImage[] CampFireFrames;

    public Campfire(GamePanel gp, Player pl, TileManager tm) {
        this.gp = gp;
        this.pl = pl;
        this.tm = tm;
        HealRect = new Rectangle(0, 0, 150, 150);
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

    public void GetCampfireImages() {
        try {
            for (int i = 0; i < 14; i++) {
                CampFireFrames[i] = ImageIO.read(getClass().getResource(urlCampFire + (i + 1) + ".png"));
            }
            // NPCfaces[0] =
            // ImageIO.read(getClass().getResource("/src/NPC/NPC_type1/FACE/face1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (gp.cicle.equals("DAY")) {
            HealRect.x = x - 50;
            HealRect.y = y - 50;
            if (tm.currentMap.equals(MapSpaw)) {
                moveFrame();
                contatore++;

                if (contatore >= 60) {
                    contatore = 0;
                    stampaPlusvita = true;
                    if (pl.PlInteractRect.intersects(HealRect)) {
                        if (pl.life < 100) {
                            pl.life += heal;
                        }
                    }
                }
                if (contatore >= 30) {
                    stampaPlusvita = false;
                }

            }
        }
    }

    public void moveFrame() {
        CampFireSpriteCounter++;
        if (CampFireSpriteCounter > 5) {
            if (CampFireSpriteNum == 1) {
                CampFireSpriteNum = 2;
            } else if (CampFireSpriteNum == 2) {
                CampFireSpriteNum = 3;

            } else if (CampFireSpriteNum == 3) {
                CampFireSpriteNum = 4;
            } else if (CampFireSpriteNum == 4) {
                CampFireSpriteNum = 5;
            } else if (CampFireSpriteNum == 5) {
                CampFireSpriteNum = 6;
            } else if (CampFireSpriteNum == 6) {
                CampFireSpriteNum = 7;
            } else if (CampFireSpriteNum == 7) {
                CampFireSpriteNum = 8;
            } else if (CampFireSpriteNum == 8) {
                CampFireSpriteNum = 9;
            } else if (CampFireSpriteNum == 9) {
                CampFireSpriteNum = 10;
            } else if (CampFireSpriteNum == 10) {
                CampFireSpriteNum = 11;
            } else if (CampFireSpriteNum == 11) {
                CampFireSpriteNum = 12;
            } else if (CampFireSpriteNum == 12) {
                CampFireSpriteNum = 13;
            } else if (CampFireSpriteNum == 13) {
                CampFireSpriteNum = 14;
            } else if (CampFireSpriteNum == 14) {
                CampFireSpriteNum = 1;
            }
            CampFireSpriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        if (gp.cicle == "DAY") {

            if (!tm.currentMap.equals(MapSpaw)) {
                return;
            }
            // scritta +5 qunado vai al falò
            if (pl.PlInteractRect.intersects(HealRect) && pl.life < 100 && stampaPlusvita == true) {
                // Suggerimento visivo per il giocatore
                g2.setFont(new Font("Arial", Font.BOLD, 14));
                g2.setColor(Color.GREEN);
                g2.drawString("+ " + heal, pl.x + 113, pl.y + 90);
            }
            spriteSet();

            g2.drawImage(FrameToDraw, x, y, gp.tileSize - 16,  gp.tileSize - 16, null);
        }
    }

    public void SpwanCampFire() {
        int tileNum;
        int col;
        int row;
        do {
            this.x = StayinZone.x + (int) (Math.random() * (StayinZone.width - gp.tileSize));
            this.y = StayinZone.y + (int) (Math.random() * (StayinZone.height - gp.tileSize));
            col = x / gp.tileSize;
            row = y / gp.tileSize;

            tileNum = tm.maptileNum[col][row];
            System.out.println(tileNum);
        } while (tileNum == 1 || tileNum == 2 || tileNum == 5);

        this.x = col * gp.tileSize;
        this.y = row * gp.tileSize;

    }

    public void spriteSet() {
        if (CampFireSpriteNum == 1)
            FrameToDraw = CampFireFrames[0];
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
