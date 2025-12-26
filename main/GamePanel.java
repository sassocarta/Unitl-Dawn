package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import entity.Player;


public class GamePanel extends JPanel implements Runnable{
    

    //SCREEN SETTINGS
    // final = cosnt

    /*
    ha una dimensione fissa (es. 16×16 pixel)
    rappresenta un pezzo del mondo di gioco (erba, muro, acqua, strada…)
    */

    final int originalTileSize = 16; //16x16 tile //standard size per npc player pezzi mappa etc..
    final int scale = 3; //scale 16x3(scale) = 48 

    public final int tileSize = originalTileSize * scale; //48x48 
    final int MaxScreenCol = 16;
    final int MaxScreenRow = 12; //16 tile orizzontali, e 12 tile verticali, ogni tile gia come detto prima 48x48

    //dimesione dello schermo
    final int ScreeWidth = tileSize * MaxScreenCol; //768 pixels
    final int ScreeHeight = tileSize * MaxScreenRow; //576 pixel

    //FPS 
    int FPS = 60;
    
    KeyHandler KeyH = new KeyHandler(); //aggiungo un KeyHendler
    MouseHandler MouseH = new MouseHandler(); //aggiungo un MouseHendler
    Thread gamThread;
    Player player = new Player(this, KeyH, MouseH);

    //set paleyer defoult posizione
    int playerX = 100;
    int playerY = 100;
    int paleyerSpeed = 4;


    public GamePanel(){
        //set la dimensione della classe JPanel
        this.setPreferredSize(new Dimension(ScreeWidth,ScreeHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.addMouseListener((MouseListener) MouseH); //aggiungo a questo JPnale il Mouse lissener (MouseH)
        this.setFocusable(true);

    }

    public void StartGameThread(){
        //passimao la classe Jpanel a questo thread
        gamThread = new Thread(this);
        gamThread.start();
    }
    
    //GAME LOOP 
    @Override
    public void run() {
        //finche questo game thread esiste

        double drawInterval = 1000000000/FPS; //disegnamo a schermo ogni 0.016666...
        double nextDrawTime = System.nanoTime() + drawInterval;//alva il tempo (in nanosecondi) in cui dovrà avvenire il prossimo frame
        while (gamThread != null) {

            //UPDATE info (qui aggiorni, fisica, collisioni,inpututente, posizione personaggi etc..)
            update();

            //DRAW draw the screen updated information. Il vero disegno avviene nel metodo paintComponent(Graphics g)
            repaint();

            try {

                double rimaningTime = nextDrawTime - System.nanoTime(); //Calcola quanto tempo manca al prossimo frame
                rimaningTime = rimaningTime/1000000; //da nanosecond a millisecondo

                //Se il frame ha impiegato troppo tempo:
                if(rimaningTime < 0){
                    rimaningTime = 0; //non dorme,passa subito al prossimo ciclo
                }

                Thread.sleep((long)rimaningTime);

                //Imposta il tempo previsto per il prossimo ciclo
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        //chimao il metodo Update di player 
        player.update();

    }

    public void paintComponent(Graphics g){


        super.paintComponent(g);
        //Graphics2D a piu funzioni, meglio di Graphics
        Graphics2D g2 = (Graphics2D)g;

        //chimao il metodo draw di player (gli devo passare g2 per funzionare)
        player.draw(g2);

        //disegna
        g2.dispose();
    }
}
