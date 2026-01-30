package entity.NPCS;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import entity.Coin.Coin;
import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class Cnpc extends NPC_Manager {

    int tick = 0;
    Rectangle stayin = null;
    //Face
    String urlFace;
    int NFFace;
    //up
    String urlup;
    int NFup;
    //down
    String urldown;
    int NFdown;
    //left
    String urlleft;
    int NFleft;
    //right
    String urlright;
    int NFright;
    //chat
    String urlchat;
    int NFchat;

    boolean istlk = false;
    boolean onespawn = false;

    Coin coin;

    public Cnpc(GamePanel gp, Player pl, Sound sd, TileManager tm,int NFchat,String urlchat,int NFFace, String urlFace,int NFup, String urlup,int NFdown, String urldown,int NFleft, String urlleft,int NFright, String urlright) {

        this.gp = gp;
        this.tm = tm;
        this.pl = pl;
        this.sd = sd;

        StayinZone = new Rectangle(96, 96, 576, 384);
        stayin = new Rectangle(0, 0, 23, 24);

        NPCUp = new BufferedImage[3];
        NPCLf = new BufferedImage[3];
        NPCRh = new BufferedImage[3];
        NPCDw = new BufferedImage[3];

        NPCfaces = new BufferedImage[6];
        NPCchats = new BufferedImage[6];

        //face
        this.NFFace = NFFace;
        this.urlFace = urlFace;
        //up
        this.NFup = NFup;
        this.urlup = urlup;
        //down
        this.NFdown = NFdown;
        this.urldown = urldown;
        //left
        this.NFleft = NFleft; 
        this.urlleft = urlleft;
        //right
        this.NFright = NFright;
        this.urlright = urlright;
        //chat
        this.NFchat = NFchat;
        this.urlchat = urlchat; 

        coin = new Coin(pl,tm,sd);

        deicidiMappaSpawn();
        SpwanNpc();
        GetAllNpcImages();
        randomStarDirection();
    }

    public void GetFaceImages(int NFFace, String urlFace) {
        try {
            for(int i = 0; i < NFFace; i++)
            {
            NPCfaces[i] =  ImageIO.read(getClass().getResource(urlFace + (i + 1) + ".png"));
            }
            //NPCfaces[0] = ImageIO.read(getClass().getResource("/src/NPC/NPC_type1/FACE/face1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GerImagesTop(int NFup, String urlup ){
        try {
            for(int i = 0; i < NFup; i++)
            {
            NPCUp[i] =  ImageIO.read(getClass().getResource(urlup + (i + 1) + ".png"));
            }
            //NPCUp[0] = ImageIO.read(getClass().getResource("/src/NPC/NPC_type1/up/.1.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void GerImagesDown(int NFdown, String urldown) {
        try {
            for(int i = 0; i < NFdown; i++)
            {
            NPCDw[i] =  ImageIO.read(getClass().getResource(urldown + (i + 1) + ".png"));
            }
            //NPCDw[0] = ImageIO.read(getClass().getResource("/src/NPC/NPC_type1/down/.1.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void GerImagesLeft(int NFleft, String urlleft) {
        try {
            for(int i = 0; i < NFleft; i++)
            {
            NPCLf[i] =  ImageIO.read(getClass().getResource(urlleft + (i + 1) + ".png"));
            }
            //NPCLf[0] = ImageIO.read(getClass().getResource("/src/NPC/NPC_type1/left/.1.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void GerImagesRight(int NFright, String urlright) {
        try {
            for(int i = 0; i < NFright; i++)
            {
            NPCRh[i] =  ImageIO.read(getClass().getResource(urlright + (i + 1) + ".png"));
            }
            //NPCRh[0] = ImageIO.read(getClass().getResource("/src/NPC/NPC_type1/right/.1.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void GetImagesChats(int NFchat, String urlchat)
    {
        try {
            for(int i = 0; i < NFchat; i++)
            {
            NPCchats[i] =  ImageIO.read(getClass().getResource(urlchat + (i + 1) + ".png"));
            }
            //\src\NPC\NPC_type1\Chat
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void GetAllNpcImages() {
        GerImagesTop(NFup,urlup);
        GerImagesDown(NFdown,urldown);
        GerImagesLeft(NFleft,urlleft);
        GerImagesRight(NFright,urlright);
        GetFaceImages(NFFace,urlFace);
        GetImagesChats(NFchat,urlchat);
    }

   /*  public void draw(Graphics2D g2) {
        if(gp.cicle == "DAY"){
            if (!tm.currentMap.equals(MapSpaw)) {
                return;
            }
            spriteSet();
            if (direction == "up") {
                g2.drawImage(UpImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
            }
            if (direction == "down") {
                g2.drawImage(DwImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
            }
            if (direction == "left") {
                g2.drawImage(LfImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
            }
            if (direction == "right") {
                g2.drawImage(RhImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
            }
            if (pl.PlInteractRect.intersects(stayin)) {
                g2.drawImage(face, -10, 130 ,96 * 4, 112 * 4, null);
                g2.drawImage(chat, 210, 80 ,200 * 3, 200 * 3, null);
            }
        }*/

        public void draw(Graphics2D g2) {
        if(gp.cicle == "DAY"){
            if (!tm.currentMap.equals(MapSpaw)) {
                return;
            }
        if(coin.Cx > 0 && coin.Cy> 0 && coin.takeit == false)
        {
            coin.draw(g2);
        }
        spriteSet();
        if (direction == "up") {
            g2.drawImage(UpImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
        }
        if (direction == "down") {
            g2.drawImage(DwImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
        }
        if (direction == "left") {
            g2.drawImage(LfImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
        }
        if (direction == "right") {
            g2.drawImage(RhImage, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
        }
          if (pl.PlInteractRect.intersects(stayin))
             {
            istlk = true;
            g2.drawImage(face, -10, 130 ,96 * 4, 112 * 4, null);
            g2.drawImage(chat, 210, 80 ,200 * 3, 200 * 3, null);
        }
        else
        {
            istlk = false;
        }
        

    }

}

    public void spriteSet() {
        if (UpSpriteNum == 1)
            UpImage = NPCUp[0];
        if (UpSpriteNum == 2)
            UpImage = NPCUp[1];
        if (UpSpriteNum == 3)
            UpImage = NPCUp[2];

        if (DwSpriteNum == 1)
            DwImage = NPCDw[0];
        if (DwSpriteNum == 2)
            DwImage = NPCDw[1];
        if (DwSpriteNum == 3)
            DwImage = NPCDw[2];

        if (LfSpriteNum == 1)
            LfImage = NPCLf[0];
        if (LfSpriteNum == 2)
            LfImage = NPCLf[1];
        if (LfSpriteNum == 3)
            LfImage = NPCLf[2];

        if (RhSpriteNum == 1)
            RhImage = NPCRh[0];
        if (RhSpriteNum == 2)
            RhImage = NPCRh[1];
        if (RhSpriteNum == 3)
            RhImage = NPCRh[2];
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

    public void NPCdirectionSet(int n) {
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

    public void SpwanNpc() {
        boolean posizionato = false;
        
        // 1. DEFINIZIONE PARAMETRI HITBOX (Devono essere identici a quelli in update)
        // Se questi valori sono diversi da quelli che usi per muoverti, il bug rimarrà.
        int offsetX = 73; 
        int offsetY = 77;
        int corpoWidth = 46;
        int corpoHeight = 48;

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
        NPCdirectionSet(n);
    }

    public void update() {
        if(gp.cicle == "DAY")
        {
        stayin.x = x + 85;
        stayin.y = y + 80;
        if (tm.currentMap.equals(MapSpaw)) {
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
                randomFace();
            } else {
                direction = "down";
                uone = false;
            }

        if (pl.PlInteractRect.intersects(stayin) && onespawn == false)
        {
        onespawn = true;
        if(coin.NPCPercSpawnCoin())
        {
        coin.CoinSpawn( x + 85, y + 83); 
        }    
        }

        if(onespawn == true && coin.NPCPercSpawnCoin() == true && coin.presounavolta == false)
        {
        if(pl.PlInteractRect.intersects(coin.pickupZone))
        {
            coin.takeit = true;;
            coin.aumentaNcoin();
            coin.presounavolta = true;
        }
        }

        }
        }

    }


    public void randomMove(String dir) {
        if (dir == "up") {
            if (UpSpriteCounter > 10) {
                if (UpSpriteNum == 1) {
                    UpSpriteNum = 2;
                } else if (UpSpriteNum == 2) {
                    UpSpriteNum = 3;
                } else if (UpSpriteNum == 3) {
                    UpSpriteNum = 1;
                }
                UpSpriteCounter = 0;
            }
        }
        if (dir == "left") {
            if (LfSpriteCounter > 10) {
                if (LfSpriteNum == 1) {
                    LfSpriteNum = 2;
                } else if (LfSpriteNum == 2) {
                    LfSpriteNum = 3;
                } else if (LfSpriteNum == 3) {
                    LfSpriteNum = 1;
                }
                LfSpriteCounter = 0;
            }
        }
        if (dir == "right") {
            if (RhSpriteCounter > 10) {
                if (RhSpriteNum == 1) {
                    RhSpriteNum = 2;
                } else if (RhSpriteNum == 2) {
                    RhSpriteNum = 3;
                } else if (RhSpriteNum == 3) {
                    RhSpriteNum = 1;
                }
                RhSpriteCounter = 0;
            }
        }
        if (dir == "down") {
            if (DwSpriteCounter > 10) {
                if (DwSpriteNum == 1) {
                    DwSpriteNum = 2;
                } else if (DwSpriteNum == 2) {
                    DwSpriteNum = 3;
                } else if (DwSpriteNum == 3) {
                    DwSpriteNum = 1;
                }
                DwSpriteCounter = 0;
            }
        }

    }

    public void moveNPC() {
        int originalX = x;
        int originalY = y;
        
        if (direction.equals("up")) {
            y--;
            UpSpriteCounter++;
            randomMove("up");
        } else if (direction.equals("down")) {
            y++;
            DwSpriteCounter++;
            randomMove("down");
        } else if (direction.equals("left")) {
            x--;
            LfSpriteCounter++;
            randomMove("left");
        } else if (direction.equals("right")) {
            x++;
            RhSpriteCounter++;
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

    public void randomFace() {
        if (uone == false) {
            int n = (int) (Math.random() * 6);
            setFace(n);
            setChat(n);
            face = NPCfaces[n];
            uone = true;
        }
    }
    
    public void setChat(int n)
    {
        switch (n) {
            case 0:
                chat = NPCchats[0];
                break;
            case 1:
                chat = NPCchats[1];
                break;
            case 2:
                chat = NPCchats[2];
                break;
            case 3:
                chat = NPCchats[3];
                break;
            case 4:
                chat = NPCchats[4];
                break;
             case 5:
                chat = NPCchats[5];
                break;
        }
    }

    public void setFace(int n) {
        switch (n) {
            case 0:
                nowFace = "chill";
                break;
            case 1:
                nowFace = "paura";
                break;
            case 2:
                nowFace = "incazzato";
                break;
            case 3:
                nowFace = "felice";
                break;
            case 4:
                nowFace = "arrabiato";
                break;
             case 5:
                nowFace = "soldi";
                break;
        }
    }
    //Controlla collisioni
    public boolean checkCollision() {
        //hitbox
        int npcHitboxX = x + 84;
        int npcHitboxY = y + 97;
        int hitboxWidth = 20;
        int hitboxHeight = 20;
        
        //lati della hitbox
        int leftCol = npcHitboxX / gp.tileSize;
        int rightCol = (npcHitboxX + hitboxWidth) / gp.tileSize;
        int topRow = npcHitboxY / gp.tileSize;
        int bottomRow = (npcHitboxY + hitboxHeight) / gp.tileSize;
        
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
