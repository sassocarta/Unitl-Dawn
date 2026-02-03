package entity.NPCS.NPC_Trader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.Player.Player;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class NPC_Tio {
    GamePanel gp;
    Player pl;

    Sound InteractSound;

    BufferedImage []idel;

    BufferedImage btnUp, btnDw;

    BufferedImage []jump;

    int IdSpriteCounter = 0;
    int IdSpriteNum = 1;
    BufferedImage chillImage = null;
    boolean chill = false;

    Rectangle InteractSquare;


    TileManager tm;
    String MapSpaw;
    int col, row, tileNum;

    int SpriteCounter = 0;
    int SpriteNum = 1;
    BufferedImage idleImage = null;

    BufferedImage BtnImage = null;
    int BTNSpriteCounter = 0 ;
    int BTNSpriteNum = 1;


    int widhtRec = 100;
    int heightRec = 100;

    boolean activeZone = false;
    public Object tileM;



    public NPC_Tio(GamePanel gp,Player pl, Sound interactSound, TileManager tm) {
        idel = new BufferedImage[3];
        jump = new BufferedImage[2];
        this.pl = pl;
        InteractSound = interactSound;
        this.tm = tm;
        this.gp = gp;
        deicidiMappaSpawn();
        GetAllImage();
        SpwanNpc();
    }


    public void GetAllImage()
    {
        getBtnImages();
        GetChillImages();
        getNPCImages();
    }

    public void update() {
        if(tm.currentMap.equals(MapSpaw))
        {
            inActivatingRect();
        }
        
    }

    public void draw(Graphics2D g2) {

        if (!tm.currentMap.equals(MapSpaw)) {
            return;
        }

        if (SpriteNum == 1) idleImage = idel[0];
        if (SpriteNum == 2) idleImage = idel[1];
        if (SpriteNum == 3) idleImage = idel[2];

        if(BTNSpriteNum == 1) BtnImage = btnUp;
        if(BTNSpriteNum == 2) BtnImage = btnDw;

        if(IdSpriteNum == 1)  chillImage = jump[0];
        if(IdSpriteNum == 2)  chillImage = jump[1];



            if(chill == true)
            {
                g2.drawImage(chillImage,col * gp.tileSize,row * gp.tileSize,gp.tileSize * 4 ,gp.tileSize  * 4  , null);
            }
            
            if(activeZone == true)
            {
            chill = false;
            g2.drawImage(idleImage,col * gp.tileSize,row * gp.tileSize,gp.tileSize * 4  ,gp.tileSize  * 4, null);
            g2.drawImage(BtnImage,col * gp.tileSize + 50,row * gp.tileSize + 20 ,gp.tileSize * 2 ,gp.tileSize  * 2, null);  
        }

        //g2.draw(InteractSquare);
        //g2.draw(pl.PlInteractRect);
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

    public boolean tileValidi(int tileNum) {
        if (tileNum == 0 || tileNum == 3 || tileNum == 4) {
            return true;
        }
        return false;
    }

    public  void getNPCImages()
    {
        try{    

            idel[0] =  ImageIO.read(getClass().getResource("/src/Trader/npc_1.png"));
            idel[1] =  ImageIO.read(getClass().getResource("/src/Trader/npc_2.png"));
            idel[2]=  ImageIO.read(getClass().getResource("/src/Trader/npc_3.png"));


        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getBtnImages()
    {
        try{    

        btnUp =  ImageIO.read(getClass().getResource("/src/Trader/btn/keyE1.png"));
        btnDw =  ImageIO.read(getClass().getResource("/src/Trader/btn/keyE2.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void GetChillImages()
    {
        try{    

        jump[0] =  ImageIO.read(getClass().getResource("/src/Trader/npc_1.png"));
        jump[1] = ImageIO.read(getClass().getResource("/src/Trader/npc_4.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public void SpwanNpc()
    {
            do {
                col = (int) (Math.random() * gp.MaxScreenCol / 2);
                row = (int) (Math.random() * gp.MaxScreenRow / 2);
                tileNum = tm.maptileNum[col][row];
            } while (!tileValidi(tileNum));
            System.out.println(tileNum);
    
    }

    public void deicidiMappaSpawn()
    {
        int n = 1 + (int) (Math.random() * 5);
        MapSpawSet(n);
    }

    public void inActivatingRect()
    {
        InteractSquare = new Rectangle(col*gp.tileSize + 45 ,row*gp.tileSize + 50, 100,100);

        if(InteractSquare.intersects(pl.PlInteractRect))
        {
            SpriteCounter++;
            if (SpriteCounter > 10) {
                if (SpriteNum == 1)
                {
                    SpriteNum = 2;
                } else if (SpriteNum == 2) {
                    SpriteNum = 3;
                } else if (SpriteNum == 3) {
                    SpriteNum = 1;
                }
                SpriteCounter = 0;
            }

            activeZone = true;


            BTNSpriteCounter++;
            if ( BTNSpriteCounter> 10) {
                if (BTNSpriteNum == 1)
                {
                    BTNSpriteNum = 2;
                } else if (BTNSpriteNum == 2) {
                    BTNSpriteNum = 1;
                }
                BTNSpriteCounter = 0;
            }
        } 
        else
        {
            activeZone = false; 
            chill = true;
            IdSpriteCounter++;
            if (IdSpriteCounter > 15) {
                if (IdSpriteNum == 1)
                {
                    IdSpriteNum = 2;
                } else if (IdSpriteNum == 2) {
                    IdSpriteNum = 1;
                }
                IdSpriteCounter = 0;
            }
        }

    }

    

}
