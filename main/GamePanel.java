package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import collision.CollisionManager;
import entity.Campfire.Campfire;
import entity.NPCS.NPC_Vector_main;
import entity.NPCS.Enemy.Enemy_Vector_main;
import entity.NPCS.NPC_Trader.NPC_Tio;
import entity.NPCS.NPC_Trader.TR_menu;
import entity.NPCS.NPC_Trader.weapons;
import entity.Player.Player;
import tile.TileManager;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    // final = cosnt

    /*
     * ha una dimensione fissa (es. 16×16 pixel)
     * rappresenta un pezzo del mondo di gioco (erba, muro, acqua, strada…)
     */

    public final int originalTileSize = 16; // 16x16 tile //standard size per npc player pezzi mappa etc..
    public final int scale = 3; // scale 16x3(scale) = 48

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int MaxScreenCol = 16;
    public final int MaxScreenRow = 12; // 16 tile orizzontali, e 12 tile verticali, ogni tile gia come detto prima
    // 48x48

    // dimesione dello schermo
    public final int ScreeWidth = tileSize * MaxScreenCol; // 768 pixels
    public final int ScreeHeight = tileSize * MaxScreenRow; // 576 pixel

    public int gameState;
    public final int mainMenuState = 0;
    public final int playState = 1;
    public final int gameOverState = 2;
    public final int pauseState = 3;
    public final int winState = 4;

    BufferedImage menuBg;
    BufferedImage btnPlayImg;
    BufferedImage btnExitImg;
    BufferedImage btnMenuImg;
    BufferedImage btnContinueImg;

    public int day=0;

    // FPS
    public int FPS = 60;

    public Player player;// aggiugo Player
    public TileManager tileM; // aggiugo TileManager
    public CollisionManager cl;
    public NPC_Tio Trader;
    public TR_menu TR_menu;
    public NPC_Vector_main NPCS;
    public Enemy_Vector_main ENEMIES;
    public Campfire cmp;

    public MouseHandler MouseH = new MouseHandler();// aggiungo un MouseHendler
    public Sound soundBG = new Sound();// aggiungo il suono del BG
    public KeyHandler KeyH = new KeyHandler(this);// aggiungo un KeyHendler
    public weapons WP = new weapons();
    public Sound speek = new Sound();// aggiungo il suono del BG

    public Thread gamThread; // thread del game loop
    public String cicle;

    // set paleyer defoult posizione
    int playerX = 100;
    int playerY = 100;
    int paleyerSpeed = 4;

    public GamePanel() {
        
        
        this.setPreferredSize(new Dimension(ScreeWidth, ScreeHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.addMouseListener((MouseListener) MouseH);
        this.addMouseMotionListener(MouseH);
        this.setFocusable(true);

        loadImages(); 
        
        resetGame(); 
        
        gameState = mainMenuState;
        
    }

    private void loadImages() {
    try {
        menuBg = ImageIO.read(getClass().getResourceAsStream("/src/menu/SfondoMenu.png"));
        btnPlayImg = ImageIO.read(getClass().getResourceAsStream("/src/menu/Buttons/Play.png"));
        btnExitImg = ImageIO.read(getClass().getResourceAsStream("/src/menu/Buttons/Exit.png"));
        btnMenuImg = ImageIO.read(getClass().getResourceAsStream("/src/menu/Buttons/Menu.png"));
        btnContinueImg = ImageIO.read(getClass().getResourceAsStream("/src/menu/Buttons/Continue.png"));
    } catch (Exception e) {
        System.out.println("Errore nel caricamento immagini: " + e.getMessage());
    }
}

    public void StartGameThread() {
        // passimao la classe Jpanel a questo thread
        gamThread = new Thread(this);
        gamThread.start();
    }

    public void StartNPCthread()
    {
        NPCS.StartThread();
    }

    public void resetGame() {
        //inizializza il giocatore
        player = new Player(this, KeyH, MouseH, soundBG); 
        
        //inizializza il mondo e le entità
        tileM = new TileManager(this, player);
        cl = new CollisionManager(player, tileM, this);
        Trader = new NPC_Tio(this, player, soundBG, tileM);
        TR_menu = new TR_menu(Trader, MouseH, KeyH, WP, speek, player);
        NPCS = new NPC_Vector_main(this, player, soundBG, tileM);
        ENEMIES = new Enemy_Vector_main(this, player, soundBG, tileM, TR_menu);
        cmp = new Campfire(this, player, tileM);
        
        // Reset variabili di gioco
        day = 0;
        
        // thread degli NPC
        StartNPCthread();
    }

    // GAME LOOP
    @Override
    public void run() {
        // finche questo game thread esiste

        double drawInterval = 1000000000 / FPS; // disegnamo a schermo ogni 0.016666...
        double nextDrawTime = System.nanoTime() + drawInterval;// alva il tempo (in nanosecondi) in cui dovrà avvenire
                                                               // il prossimo frame
        while (gamThread != null) {

            // UPDATE info (qui aggiorni, fisica, collisioni,inpututente, posizione
            // personaggi etc..)
            update();

            // DRAW draw the screen updated information. Il vero disegno avviene nel metodo
            // paintComponent(Graphics g)
            repaint();

            try {

                double rimaningTime = nextDrawTime - System.nanoTime(); // Calcola quanto tempo manca al prossimo frame
                rimaningTime = rimaningTime / 1000000; // da nanosecond a millisecondo

                // Se il frame ha impiegato troppo tempo:
                if (rimaningTime < 0) {
                    rimaningTime = 0; // non dorme,passa subito al prossimo ciclo
                }

                Thread.sleep((long) rimaningTime);

                // Imposta il tempo previsto per il prossimo ciclo
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() { 
        if (gameState == mainMenuState) {
            // Controlla se l'utente ha cliccato il tasto sinistro
            if (MouseH.leftPressed) { 
                
                // Pulsante PLAY
                if (isMouseOver(550, 300, 140, 60)) {
                    resetGame();
                    gameState = playState;
                    avviaMusica(0); // Avvia la musica del gioco se necessario
                    MouseH.leftPressed = false; // Reset per evitare click multipli
                }
                
                // Pulsante EXIT
                else if (isMouseOver(558, 380, 140, 60)) {
                    System.exit(0);
                }
            }
        }

        if (gameState == pauseState) {
            if (MouseH.leftPressed) {
                // Pulsante RIPRENDI (Resume)
                if (isMouseOver(290, 380, 200, 60)) {
                    gameState = playState;
                    MouseH.leftPressed = false;
                }
                // Pulsante TORNA AL MENU (Quit to Menu)
                else if (isMouseOver(320, 300, 140, 60)) {
                    gameState = mainMenuState;
                    MouseH.leftPressed = false;
                }
            }
        }

        if (gameState == gameOverState) {
            if (MouseH.leftPressed) {
                // Pulsante menu
                if (isMouseOver(320, 300, 140, 60)) {
                    gameState = mainMenuState;
                    MouseH.leftPressed = false;
                }
                // Pulsante exit
                else if (isMouseOver(323, 380, 140, 60)) {
                    System.exit(0);
                    MouseH.leftPressed = false;
                }
            }
        }

        if (gameState == winState) {
            if (MouseH.leftPressed) {
                // Pulsante menu
                if (isMouseOver(320, 300, 140, 60)) {
                    gameState = mainMenuState;
                    MouseH.leftPressed = false;
                }
                // Pulsante exit
                else if (isMouseOver(323, 380, 140, 60)) {
                    System.exit(0);
                    MouseH.leftPressed = false;
                }
            }
        }

        if (gameState == playState) {
            player.update(); 
            tileM.update(); 
            cl.update(); 
            ENEMIES.update();
            cmp.update();
            Trader.update();
            TR_menu.update();

        }
    }

    private boolean isMouseOver(int x, int y, int width, int height) {
        return MouseH.mouseX >= x && MouseH.mouseX <= x + width &&
            MouseH.mouseY >= y && MouseH.mouseY <= y + height;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == mainMenuState) {
            if (menuBg != null) g2.drawImage(menuBg, 0, 0, ScreeWidth, ScreeHeight, null);
            if (btnPlayImg != null) g2.drawImage(btnPlayImg, 550, 300, 140, 60,  null);
            if (btnExitImg != null) g2.drawImage(btnExitImg, 558, 380, 140, 60, null);
        } 
        else {
            // Disegna il gioco solo se non sei nel menu
            tileM.draw(g2);
            player.draw(g2);
            Trader.draw(g2);
            TR_menu.draw(g2);
            cmp.draw(g2);
            NPCS.draw(g2);
            ENEMIES.draw(g2);
            
            if (gameState == gameOverState) {
                // 1. Oscura lo schermo
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(0, 0, ScreeWidth, ScreeHeight);

                //testo
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
                String text = "GAME OVER";
                int x = getXforCenteredText(text, g2);
                // Ombra del testo (per leggerlo meglio)
                g2.setColor(Color.black);
                g2.drawString(text, x + 3, 263);
                // Testo principale
                g2.setColor(Color.RED);
                g2.drawString(text, x, 260);
                

                // Pulsante Continue
                if (btnMenuImg != null) g2.drawImage(btnMenuImg, 320, 300, 140, 60,  null);

                // Pulsante Menu
                if (btnExitImg != null) g2.drawImage(btnExitImg, 323, 380, 140, 60, null);
            }

            if (gameState == winState) {
                // 1. Oscura lo schermo
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(0, 0, ScreeWidth, ScreeHeight);

                //testo
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
                String text = "WIN!";
                int x = getXforCenteredText(text, g2);
                // Ombra del testo (per leggerlo meglio)
                g2.setColor(Color.black);
                g2.drawString(text, x + 3, 263);
                // Testo principale
                g2.setColor(Color.GREEN);
                g2.drawString(text, x, 260);
                

                // Pulsante Continue
                if (btnMenuImg != null) g2.drawImage(btnMenuImg, 320, 300, 140, 60,  null);

                // Pulsante Menu
                if (btnExitImg != null) g2.drawImage(btnExitImg, 323, 380, 140, 60, null);
            }

            if (gameState == pauseState) {
                // 1. Oscura lo schermo
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(0, 0, ScreeWidth, ScreeHeight);

                //testo
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
                g2.setColor(Color.YELLOW);
                String text = "PAUSE";
                int x = getXforCenteredText(text, g2);
                g2.drawString(text, x, 260);

                // Pulsante Continue
                if (btnMenuImg != null) g2.drawImage(btnMenuImg, 320, 300, 140, 60,  null);

                // Pulsante Menu
                if (btnContinueImg != null) g2.drawImage(btnContinueImg, 290, 380, 200, 60, null);
            }
        }
        
        g2.dispose();
    }

    //Metodo per centrare le scritte su schermo
    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return ScreeWidth / 2 - length / 2;
    }

    public void avviaMusica(int i) {
        
        soundBG.setFile(i);// setta il file con numero int i
        soundBG.play(); // lo riproduce
        soundBG.loop();// lo mette in loop
    }

    public void FermaMusica() {
        soundBG.stop(); // ferma il file musicale riprodotto
    }

}
