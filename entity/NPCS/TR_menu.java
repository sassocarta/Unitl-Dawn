package entity.NPCS;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.KeyHandler;
import main.MouseHandler;
import main.Sound;

public class TR_menu {

    NPC_TR Trader;
    MouseHandler Mh;
    main.KeyHandler Kh;

    boolean isOpen = false;
    boolean eEraPrem = false;

    BufferedImage MenuGraphic;
    BufferedImage TraderFace;
    BufferedImage chat1, chatt2;
    BufferedImage tioshop;

    int Imgchat1 = 1;
    int ImgChat2 = 250;
    int endChat = 350;

    // da mettere classe comune

    int ChatX = 190;
    int ChatY = 100;
    int Chatsize = 600;

    Sound Speek;
    int chatting = 0;
    boolean Chat1 = false;
    int chat = 0;

    weapons wp;

    BufferedImage WP1,WP2,WP3;
    int wp1size = 128;
    int wp2size = 128;
    int wp3size = 128;

    Sound speek;
    int n = 0;

    public TR_menu(NPC_TR trader, MouseHandler mh, KeyHandler kh, weapons wp,Sound speek) {
        Trader = trader;
        Mh = mh;
        Kh = kh;
        this.wp = wp;
        this.speek = speek;
        GetTrader_FaceImages();
        GetChatImages();
        GetbgImages();
        RandomWp();
    }

    public void GetbgImages() {
        try {
            MenuGraphic = ImageIO.read(getClass().getResource("/src/menu/BG/bg.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void GetTrader_FaceImages() {
        try {
            TraderFace = ImageIO.read(getClass().getResource("/src/Trader/Face/face1.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void GetChatImages() {
        try {

            chat1 = ImageIO.read(getClass().getResource("/src/Trader/chat/chat1.png"));
            chatt2 = ImageIO.read(getClass().getResource("/src/Trader/chat/chatt2.png"));
            tioshop = ImageIO.read(getClass().getResource("/src/Trader/chat/tioshoop.png"));

        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void update() {
        // se pl e nella zona attivazione ed e attivato
        if (Trader.activeZone) {
            // e lui preme e e l'ultima volta nn ha premuto E
            if (Kh.EPressed && !eEraPrem) {
                // allora inverto stato finestra
                isOpen = !isOpen;
                seeDialog();
                n++;
                if (n == 1)
                {
                    speek.setFile(6);
                    speek.play();
                }
            }
            if(isOpen)
                {
                    SelectWeapond();
                }
        } else {
            // chiudo se fupri da zona attivazione
            isOpen = false;
            chatting = 0;
            n = 0;
            Chat1 = false;
        }
        // salvo ultima azione
        eEraPrem = Kh.EPressed;
    }

    public void draw(Graphics2D g2) {
        if (isOpen == true) {

            g2.drawImage(MenuGraphic, 80, 100, 600, 400, null);
            g2.drawImage(tioshop, 180, -70, 400, 400, null);
            g2.drawImage(WP1, 130, 210, wp1size, wp1size, null);
            g2.drawImage(WP2, 310, 210, wp2size, wp2size, null);
            g2.drawImage(WP3, 500, 210, wp3size, wp3size, null);

            if (!seeDialog() && Chat1 == false) {
                if (chat == 1) {
                    g2.drawImage(chat1, ChatX, ChatY, Chatsize, Chatsize, null);
                }
                if (chat == 2) {
                    g2.drawImage(chatt2, ChatX, ChatY, Chatsize, Chatsize, null);
                }
                g2.drawImage(TraderFace, -50, 176, 400, 400, null);
            }
        }
    }

    public boolean seeDialog() {
        chatting++;
        if (chatting == Imgchat1) {
            chat = 1;
        }
        if (chatting == ImgChat2) {
            chat = 2;
        }
        if (chatting == endChat) {
            chatting = 0;
            Chat1 = true;
            return true;
        }
        return false;

    }

    public void RandomWp()
    {
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        do
        {
        n1 = (int) (Math.random() * 5);
        n2 = (int) (Math.random() * 5);
        n3 = (int) (Math.random() * 5);
        }while(n1 == n2 || n2 == n3 ||  n3 == n1);
        WP1 = wp.wp[n1];
        WP2 = wp.wp[n2];
        WP3 = wp.wp[n3];

    }

    public void SelectWeapond()
    {
        if ( seeDialog() == false )
        {
        if(Kh.Pressed1 == true){
            wp1size = 200;
            wp2size = 128;
            wp3size = 128;
        }
        if(Kh.Pressed2 == true){
            wp2size = 200;
            wp3size = 128;
            wp1size = 128;
        }
        if(Kh.Pressed3 == true){
            wp3size = 200;
            wp2size = 128;
            wp1size = 128;     
        }
        }
        else{
            wp1size = 128;
            wp2size = 128;
            wp3size = 128;
        }
    }


}
