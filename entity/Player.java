package entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Player extends Entity{
    //Jpanel
    GamePanel gp;
    //keylissener (main. [me lo fa mettere lui non so cosa serva])
    main.KeyHandler keyH;
    


    public Player(GamePanel gp, main.KeyHandler keyH2) {
        this.gp = gp;
        this.keyH = keyH2;

        setDefaultValues();
        getPlayerImg();
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = "rg1";

    }
    //carichiamo sulle immagini Buffer le immagini nella cartella con segunete posizione
    public void getPlayerImg(){
        try{
            rg1 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile1.png"));
            rg2 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile2.png"));
            rg3 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile3.png"));
            rg4 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile4.png"));
            rg5 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile5.png"));
            rg6 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile6.png"));
            rg7 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile7.png"));
            rg8 =ImageIO.read(getClass().getResource( "/Asset/Player/Player_RIGHT/Player_walk_right/tile8.png"));

            lf1 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left1.png"));
            lf2 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left2.png"));
            lf3 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left3.png"));
            lf4 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left4.png"));
            lf5 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left5.png"));
            lf6 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left6.png"));
            lf7 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left7.png"));
            lf8 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left8.png"));
        }
        catch(IOException e)
        {
            //se non trova le immagini stamapa 
            e.printStackTrace();
        }
    }
// metodo update del player
    public void update()
    {
        // se tasto premuto:
        //1.sposto posizione player
        //2.vado avanti di uno sprite nella animazione
        //3.se sprite counter e minore di 10 (velocita animazione) piu basso numero piu veloce animazione
        //4.in base allo sprite precedente setto quello succesivo 
        if(keyH.upPressed == true || keyH.dowPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
        if(keyH.upPressed == true){

            y -= speed; //1
        }
        else if (keyH.dowPressed == true)
        {   
            y += speed;
        }
        else if (keyH.leftPressed == true)
        {
            direction = "left"; //sto dicendo che deve guardare gli sprite di sinistra
            x -= speed;
        }
        else if (keyH.rightPressed == true)
        {
            direction = "right"; //sto dicendo che deve guardare gli sprite di destra
            x += speed;   
        }
        SpriteCounter++; //2
        if(SpriteCounter > 10){ //3
            if(SpriteNum == 1) //4
            {
                SpriteNum = 2;
            }
            else if(SpriteNum == 2)
            {
                SpriteNum = 3;
            }
            else if(SpriteNum == 3)
            {
                SpriteNum = 4;
            }
            else if(SpriteNum == 4)
            {
                SpriteNum = 5;
            }
            else if(SpriteNum == 5)
            {
                SpriteNum = 6;
            }
            else if(SpriteNum == 6)
            {
                SpriteNum = 7;
            }
            else if(SpriteNum == 7)
            {
                SpriteNum = 8;
            }
            else if(SpriteNum == 8)
            {
                SpriteNum = 1;
            }

            SpriteCounter = 0; //setto a 0
        }
    }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null; // immagine che verra stampata: a null;
        switch (direction) {
            case "left":
                if(SpriteNum == 1) //in base a left o right e allo spriteNum decido che immagine caricare su image
                {
                    image = lf1;
                }
                if(SpriteNum == 2)
                {
                    image = lf2;
                }
                if(SpriteNum == 3)
                {
                    image = lf3;
                }
                if(SpriteNum == 4)
                {
                    image = lf4;
                }
                if(SpriteNum == 5)
                {
                    image = lf5;
                }
                if(SpriteNum == 6)
                {
                    image = lf6;
                }
                if(SpriteNum == 7)
                {
                    image = lf7;
                }
                if(SpriteNum == 8)
                {
                    image = lf8;
                }
                break;
            case "right":
                if(SpriteNum == 1)
                {
                    image = rg1;
                }
                if(SpriteNum == 2)
                {
                    image = rg2;
                }
                if(SpriteNum == 3)
                {
                    image = rg3;
                }
                if(SpriteNum == 4)
                {
                    image = rg4;
                }
                if(SpriteNum == 5)
                {
                    image = rg5;
                }
                if(SpriteNum == 6)
                {
                    image = rg6;
                }
                if(SpriteNum == 7)
                {
                    image = rg7;
                }
                if(SpriteNum == 8)
                {
                    image = rg8;
                }
                break;
            default: 
                image = rg1;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize * 4, gp.tileSize * 4, null); //disegno image con x e y del player (o dovuto aumentare la dimensione del immagine[*4] )
    }
    
}
