package entity.NPCS.NPC_Trader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import entity.Player.Player;
import main.KeyHandler;
import main.MouseHandler;
import main.Sound;

public class TR_menu {

    NPC_Tio Trader;
    MouseHandler Mh;
    main.KeyHandler Kh;

    public boolean isOpen = false;
    boolean eEraPrem = false;

    BufferedImage MenuGraphic;
    BufferedImage TraderFace;
    BufferedImage chat1, chatt2;
    BufferedImage tioshop;

    int chatImageStage = 1;

    int ChatX = 190;
    int ChatY = 100;
    int Chatsize = 600;

    int chatting = 0;
    boolean Chat1 = false;
    int chat = 0;

    weapons wp;
    Weapon WP1, WP2, WP3;
    int wp1size = 128;
    int wp2size = 128;
    int wp3size = 128;

    Sound speek;
    int n = 0;
    Player pl;

    public Weapon armaPosseduta = null;

    public TR_menu(NPC_Tio trader, MouseHandler mh, KeyHandler kh, weapons wp, Sound speek, Player pl) {
        this.Trader = trader;
        this.Mh = mh;
        this.Kh = kh;
        this.wp = wp;
        this.speek = speek;
        this.pl = pl; // Inizializzato il player
        GetTrader_FaceImages();
        GetChatImages();
        GetbgImages();
        RandomWp();
    }

    public void GetbgImages() {
        try { 
            MenuGraphic = ImageIO.read(getClass().getResource("/src/menu/BG/bg.png")); 
        } catch (Exception e) {}
    }

    public void GetTrader_FaceImages() {
        try { 
            TraderFace = ImageIO.read(getClass().getResource("/src/Trader/Face/face1.png")); 
        } catch (Exception e) {}
    }

    public void GetChatImages() {
        try {
            chat1 = ImageIO.read(getClass().getResource("/src/Trader/chat/chat1.png"));
            chatt2 = ImageIO.read(getClass().getResource("/src/Trader/chat/chatt2.png"));
            tioshop = ImageIO.read(getClass().getResource("/src/Trader/chat/tioshoop.png"));
        } catch (Exception e) {}
    }

    public void update() {
        if (Trader.activeZone) {
            if (Kh.EPressed && !eEraPrem) {
                Trader.tm.npcForcingNight = true;
                isOpen = !isOpen;

                chatImageStage = 1;
                Chat1 = false;
                chatting = 0;
                
                if (isOpen) {
                    speek.setFile(6);
                    speek.play();
                }
            }

            if (isOpen) {
                //avanza con il dialogo cliccando con il mouse
                if (Mh.leftPressed && !Chat1) {
                    chatImageStage++;
                    if (chatImageStage > 2) {
                        Chat1 = true;
                    }
                    Mh.leftPressed = false;
                }

                if (Chat1) {
                    SelectWeapond();
                    checkPurchase();
                }
            }
        } else {
            isOpen = false;
            Chat1 = false;
            chatImageStage = 1;
        }
        eEraPrem = Kh.EPressed;
    }

    //funzione per gestire il click del mouse sulle armi
    public void checkPurchase() {
        //Controlla il click
        if (Mh.leftPressed) { 
            
            if (WP1 != null && isMouseOver(130, 210, wp1size, wp1size) && pl.NumeroCoin >= WP1.price) {
                buyWeapon(1);
            }
            else if (WP2 != null && isMouseOver(310, 210, wp2size, wp2size) && pl.NumeroCoin >= WP2.price) {
                buyWeapon(2);
            }
            else if (WP3 != null && isMouseOver(490, 210, wp3size, wp3size) && pl.NumeroCoin >= WP3.price) {
                buyWeapon(3);
            }
            
            Mh.leftPressed = false; 
        }
    }

    //Funzione per vedere se il mouse è sopra l'immagine
    private boolean isMouseOver(int x, int y, int width, int height) {
        return Mh.mouseX >= x && Mh.mouseX <= x + width &&
            Mh.mouseY >= y && Mh.mouseY <= y + height;
    }

    private void buyWeapon(int slot) {
        
        if (slot == 1) armaPosseduta = WP1;
        if (slot == 2) armaPosseduta = WP2;
        if (slot == 3) armaPosseduta = WP3;

        if (armaPosseduta != null && pl.NumeroCoin >= armaPosseduta.price) {
            //Sottrai monete
            pl.NumeroCoin -= armaPosseduta.price;
            
            //Aggiorna il danno del player
            pl.Damage = armaPosseduta.damage;

            speek.play();

            //Rimuovi l'arma dal menu
            if (slot == 1) WP1 = null;
            if (slot == 2) WP2 = null;
            if (slot == 3) WP3 = null;
        } else {
        }
    }

    public void draw(Graphics2D g2) {
        if (isOpen) {
            // 1. SFONDO E TITOLO
            g2.drawImage(MenuGraphic, 80, 100, 600, 400, null);
            g2.drawImage(tioshop, 180, -70, 400, 400, null);

            // 2. ARMI (Sempre visibili o visibili solo dopo il dialogo? 
            // Meglio sempre così il giocatore vede cosa può comprare)
            int itemY = 210;
            drawWeaponSlot(g2, WP1, 130, itemY, wp1size);
            drawWeaponSlot(g2, WP2, 310, itemY, wp2size);
            drawWeaponSlot(g2, WP3, 490, itemY, wp3size);

            // 3. DIALOGO (Copre le armi finché non finisce)
            if (!Chat1) {
                // Faccia del mercante
                g2.drawImage(TraderFace, -40, 176, 400, 400, null);

                // Testi in base al click
                if (chatImageStage == 1) g2.drawImage(chat1, ChatX, ChatY, Chatsize, Chatsize, null);
                if (chatImageStage == 2) g2.drawImage(chatt2, ChatX, ChatY, Chatsize, Chatsize, null);
                if (chatImageStage == 3) g2.drawImage(chatt2, ChatX, ChatY, Chatsize, Chatsize, null); 
                
            }
        }
    }

    //disegna l'arma, il nome e il danno
    private void drawWeaponSlot(Graphics2D g2, Weapon weapon, int x, int y, int size) {

        if (weapon == null) return;

        //Se il player ha monete >= al prezzo, mostra immagine sbloccata
        if (pl.NumeroCoin >= weapon.price) {
            g2.drawImage(weapon.unlockedImage, x, y, size, size, null);
        } else {
            g2.drawImage(weapon.blockedImage, x, y, size, size, null);
        }

        //Testo
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        //Nome Arma
        g2.setColor(new Color(123, 63, 0));
        g2.drawString(weapon.name, x + 20, y + size + 25);

        //Differenza di danno
        int diffDamage = weapon.damage - pl.Damage;
        String diffText;
        
        if(diffDamage >= 0){
            g2.setColor(new Color(80, 200, 120));
            diffText = "+";
        }
        else{
            g2.setColor(new Color(220, 20, 60));
            diffText = "-";
        }

        g2.drawString("Damage: " + diffText + diffDamage, x + 10, y + size + 60);
        
        // Prezzo
        g2.setColor(new Color(123, 63, 0));
        g2.drawString(weapon.price + " monete", x + 20, y + size + 100);
    }
    
    public void RandomWp() {
        WP1 = getRandomWeapon();
        do{ 
            WP2 = getRandomWeapon(); 
        } while (WP2 == WP1);
        do{ 
            WP3 = getRandomWeapon(); 
        } while (WP3 == WP1 || WP3 == WP2);
    }

    private Weapon getRandomWeapon() {

        //possibilità di avere le lame del caos (0,5%)
        int chance = (int) (Math.random() * 200);
        
        //Se esce 67, prendi le lame del caos
        if (chance == 67) {
            for (Weapon w : wp.weapons) {
                if (w.name.equalsIgnoreCase("Chaos Blades")) {
                    return w;
                }
            }
        }
        //altrimenti prendi un'altra arma
        Weapon w;
        do {
            int index = (int) (Math.random() * wp.weapons.size());
            w = wp.weapons.get(index);
        } while (w.name.equalsIgnoreCase("Chaos Blades"));
        
        return w;
    }

    public void SelectWeapond() {
        if (Chat1) { // Se il dialogo è finito, permetti selezione
            if (Kh.Pressed1) { wp1size = 160; wp2size = 128; wp3size = 128; }
            else if (Kh.Pressed2) { wp1size = 128; wp2size = 160; wp3size = 128; }
            else if (Kh.Pressed3) { wp1size = 128; wp2size = 128; wp3size = 160; }
        } else {
            wp1size = 128; wp2size = 128; wp3size = 128;
        }
    }
}