package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import collision.CollisionManager;
import entity.NPCS.NPC_Vector_main;
import entity.NPCS.NPC_Trader.NPC_Tio;
import entity.NPCS.NPC_Trader.TR_menu;
import entity.NPCS.NPC_Trader.weapons;
import entity.Player.Player;
import tile.TileManager;

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

    // FPS
    int FPS = 60;

    MouseHandler MouseH = new MouseHandler(); // aggiungo un MouseHendler
    Thread gamThread; // thread del game loop

    KeyHandler KeyH = new KeyHandler(); // aggiungo un KeyHendler
    Player player = new Player(this, KeyH, MouseH); // aggiugo Player
    TileManager tileM = new TileManager(this, player); // aggiugo TileManager
    Sound soundBG = new Sound(); // aggiungo il suono del BG
    CollisionManager cl = new CollisionManager(player, tileM, this);
    NPC_Tio Trader = new NPC_Tio(this, player, soundBG, tileM);
    weapons WP = new weapons();
    Sound speek = new Sound(); // aggiungo il suono del BG
    TR_menu TR_menu = new TR_menu(Trader, null, KeyH, WP, speek);
    NPC_Vector_main NPCS = new NPC_Vector_main(this, player, soundBG, tileM);

    public String cicle;

    // set paleyer defoult posizione
    int playerX = 100;
    int playerY = 100;
    int paleyerSpeed = 4;

    public GamePanel() {
        // set la dimensione della classe JPanel
        this.setPreferredSize(new Dimension(ScreeWidth, ScreeHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.addMouseListener((MouseListener) MouseH); // aggiungo a questo JPnale il Mouse lissener (MouseH)
        this.setFocusable(true);

    }

    public void StartGameThread() {
        // passimao la classe Jpanel a questo thread
        gamThread = new Thread(this);
        gamThread.start();
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

        player.update(); 
        tileM.update(); 
        cl.update(); 
        NPCS.update();
        Trader.update();
        TR_menu.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        Trader.draw(g2);
        TR_menu.draw(g2);
        NPCS.draw(g2);
        g2.dispose();

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
