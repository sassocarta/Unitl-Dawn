/**
* @version 1.0
* @file GamePanel.java 
* 
* @brief File che contiene la classe GamePanel
*
*/
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


/** 
* @class GamePanel
* 
* @brief Classe che gestisce il pannello di gioco
* 
* Questa classe serve a gestire il pannello di gioco.
* Crea il gioco all'interno del pannello e ne gestisce gli stati.
*/
public class GamePanel extends JPanel implements Runnable {

    /** dimensione fissa delle caselle di gioco*/
    public final int originalTileSize = 16;

    /** moltiplicatore della dimensione delle caselle di gioco*/
    public final int scale = 3;

    /** dimensione finale moltiplicata delle caselle di gioco*/
    public final int tileSize = originalTileSize * scale;

    /** numero di caselle in una riga*/
    public final int MaxScreenCol = 16;

    /** numero di caselle in una colonna*/
    public final int MaxScreenRow = 12;

    /** Larghezza dello schermo*/
    public final int ScreeWidth = tileSize * MaxScreenCol; // 768 pixels

    /** Altezza dello schermo*/
    public final int ScreeHeight = tileSize * MaxScreenRow; // 576 pixel

    /** Stato del gioco*/
    public int gameState;

    /** Stato menù principale*/
    public final int mainMenuState = 0;

    /** Stato gioco*/
    public final int playState = 1;

    /** Stato game over*/
    public final int gameOverState = 2;

    /** Stato pausa*/
    public final int pauseState = 3;

    /** Stato vittoria*/
    public final int winState = 4;

    /** Immagine sfondo del menu*/
    BufferedImage menuBg;

    /** immagine del pulsante PLAY*/
    BufferedImage btnPlayImg;

    /** immagine del pulsante EXIT*/
    BufferedImage btnExitImg;

    /** immagine del pulsante MENU*/
    BufferedImage btnMenuImg;

    /** immagine del pulsante CONTINUE*/
    BufferedImage btnContinueImg;

    /** Numero di giorni*/
    public int day=0;

    /** FPS*/
    public int FPS = 60;

    /** Player*/
    public Player player;

    /** Tile Manager*/
    public TileManager tileM;

    /** Collision Manager*/
    public CollisionManager cl;

    /** Mercante*/
    public NPC_Tio Trader;

    /** Shop del mercante*/
    public TR_menu TR_menu;

    /** Personaggi del giorno*/
    public NPC_Vector_main NPCS;

    /** Mostri della notte*/
    public Enemy_Vector_main ENEMIES;

    /** Campfire*/
    public Campfire cmp;

    /** MouseHandler*/
    public MouseHandler MouseH = new MouseHandler();

    /** Musica di sfondo*/
    public Sound soundBG = new Sound();

    /** KeyHandler*/
    public KeyHandler KeyH = new KeyHandler(this);

    /** Armi nel gioco*/
    public weapons WP = new weapons();

    /** Suono di interazione NPC e mercante*/
    public Sound speek = new Sound();

    /** Thread del game loop*/
    public Thread gamThread;

    /** Ciclo giorno/notte*/
    public String cicle;

    /** Posizione spawn X del player*/
    int playerX = 100;

    /** Posizione spawn Y del player*/
    int playerY = 100;

    /** Velocità del player*/
    int paleyerSpeed = 4;


    /**
     @brief Costruttore della classe gamePanel.
     
     questo metodo è il costruttore che crea il gamePanel
    */
    public GamePanel() {
        
        //imposta la dimensione del pannello
        this.setPreferredSize(new Dimension(ScreeWidth, ScreeHeight));
        //imposta il colore di sfondo
        this.setBackground(Color.black);
        
        this.setDoubleBuffered(true);
        //aggiunge il keyListener
        this.addKeyListener(KeyH);
        //aggiunge il Mouse Listener
        this.addMouseListener((MouseListener) MouseH);
        //aggiunge il movimento del mouse di Mouse Listener
        this.addMouseMotionListener(MouseH);
        //imposta la visualizzazione del pannello
        this.setFocusable(true);

        //carica le immagini necessarie
        loadImages(); 
        
        //imposta il gioco
        resetGame(); 
        
        //imposta lo stato del gioco a stato menu principale
        gameState = mainMenuState;
        
    }

    /**
     @brief Carica immagini.
     
     questo metodo serve per caricare le immagini mettendole in variabili bufferedImage
    */
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

    /**
     @brief Comincia il thread del gioco.
     
     questo metodo serve per far cominciare il thread del gioco
    */
    public void StartGameThread() {
        // passimao la classe Jpanel a questo thread
        gamThread = new Thread(this);
        gamThread.start();
    }

    /**
     @brief Comincia il thread degli NPC.
     
     questo metodo serve per far cominciare il thread delle entità del giorno
    */
    public void StartNPCthread()
    {
        NPCS.StartThread();
    }

    /**
     @brief Imposta il gioco.
     
     questo metodo serve per impostare tutti i valori del gioco quando si preme il pulsante PLAY nel menu principale
    */
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
        
        day = 0;
        
        // thread degli NPC
        StartNPCthread();
    }

    /**
     @brief Run.
     
     questo metodo è quello del thread della classe
    */
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

    /**
     @brief Update.
     
     questo metodo serve per aggiornare lo stato dell'oggetto ogni frame (sessanta volte al secondo)
    */
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
                // Pulsante CONTINUE
                if (isMouseOver(290, 380, 200, 60)) {
                    gameState = playState;
                    MouseH.leftPressed = false;
                }
                // Pulsante MENU
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

    /**
     @brief Controlla la posizione del mouse.
     
     questo metodo serve per controllare se la posizine del click del mouse è all'interno di un rettangolo passato per parametro

     @param x x del rettangolo
     @param y y del rettangolo
     @param width larghezza del rettangolo
     @param height altezza del rettangolo

     @return ritorna true se il mouse è all'interno del rettangolo
    */
    private boolean isMouseOver(int x, int y, int width, int height) {
        return MouseH.mouseX >= x && MouseH.mouseX <= x + width && MouseH.mouseY >= y && MouseH.mouseY <= y + height;
    }

    /**
     @brief Paint Component.
     
     questo metodo serve per disegnare a schermo i frame dell'oggetto.

     @param g strumento per disegnare a schermo
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == mainMenuState) {
            //disegna il menu
            if (menuBg != null) g2.drawImage(menuBg, 0, 0, ScreeWidth, ScreeHeight, null);
            if (btnPlayImg != null) g2.drawImage(btnPlayImg, 550, 300, 140, 60,  null);
            if (btnExitImg != null) g2.drawImage(btnExitImg, 558, 380, 140, 60, null);
        } 
        else {
            // Disegna il gioco
            tileM.draw(g2);
            player.draw(g2);
            Trader.draw(g2);
            TR_menu.draw(g2);
            cmp.draw(g2);
            NPCS.draw(g2);
            ENEMIES.draw(g2);
            
            if (gameState == gameOverState) {
                //disegna il game over

                //Oscura lo schermo
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(0, 0, ScreeWidth, ScreeHeight);
                //testo
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
                String text = "GAME OVER";
                int x = getXforCenteredText(text, g2);
                // Ombra del testo
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
                //disegna la vittoria

                //Oscura lo schermo
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
                //disegna la pausa

                //Oscura lo schermo
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

    /**
     @brief Centra scritta a schermo.
     
     questo metodo serve per disegnare una scritta centrata nello schermo

     @param text testo da centrare nello schermo
     @param g strumento per disegnare a schermo

     @return ritorna coordinate x e y della scritta centrata
    */
    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return ScreeWidth / 2 - length / 2;
    }

    /**
     @brief Avvia musica.
     
     questo metodo serve per avviare un sound e eseguirno in loop. 
     il sound da avviare pene preso dal vettore della clase SOund.java attraverso l'indice in parametro

     @param i indice del vettora da cui prendere il sound
    */
    public void avviaMusica(int i) {
        
        soundBG.setFile(i);// setta il file con numero int i
        soundBG.play(); // lo riproduce
        soundBG.loop();// lo mette in loop
    }

    /**
     @brief Ferma musica.
     
     questo metodo serve per fermare tutti i suoni che sono in esecuzione.
    */
    public void FermaMusica() {
        soundBG.stop(); // ferma il file musicale riprodotto
    }

}
