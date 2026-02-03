package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.Font;

import javax.swing.JPanel;

import collision.CollisionManager;
import entity.NPCS.NPC_Vector_main;
import entity.NPCS.Enemy.Enemy_Vector_main;
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

    public int gameState;
    public final int playState = 1;
    public final int gameOverState = 2;

    // FPS
    public int FPS = 60;

    MouseHandler MouseH = new MouseHandler(); // aggiungo un MouseHendler
    public Thread gamThread; // thread del game loop
    Sound soundBG = new Sound(); // aggiungo il suono del BG
    KeyHandler KeyH = new KeyHandler(); // aggiungo un KeyHendler
    Player player = new Player(this, KeyH, MouseH,soundBG); // aggiugo Player
    TileManager tileM = new TileManager(this, player); // aggiugo TileManager
    CollisionManager cl = new CollisionManager(player, tileM, this);
    NPC_Tio Trader = new NPC_Tio(this,player, soundBG, tileM);
    weapons WP = new weapons();
    Sound speek = new Sound(); // aggiungo il suono del BG
    public TR_menu TR_menu = new TR_menu(Trader, MouseH, KeyH, WP, speek, player);
    NPC_Vector_main NPCS = new NPC_Vector_main(this, player, soundBG, tileM);
    public Enemy_Vector_main ENEMIES = new Enemy_Vector_main(this, player, soundBG, tileM,TR_menu);
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
        this.addMouseMotionListener(MouseH);
        this.setFocusable(true);
        StartNPCthread();
        gameState = playState;
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
        if (gameState == playState) {
            player.update(); 
            tileM.update(); 
            cl.update(); 
            ENEMIES.update();
            Trader.update();
            TR_menu.update();
    }
}


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        Trader.draw(g2);
        TR_menu.draw(g2);
        NPCS.draw(g2);
        ENEMIES.draw(g2);
        g2.dispose();

        if (gameState == gameOverState) {
            String text = "GAME OVER: SEI MORTO";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F)); // Font grande e grassetto
            
            // Calcolo per centrare il testo
            int x = getXforCenteredText(text, g2);
            int y = ScreeHeight / 2;

            // Ombra del testo (per leggerlo meglio)
            g2.setColor(Color.black);
            g2.drawString(text, x + 3, y + 3);

            // Testo principale (Rosso sangue)
            g2.setColor(Color.red);
            g2.drawString(text, x, y);
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
