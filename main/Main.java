/**
* @version 1.0
* @file Main.java 
* 
* @brief File che contiene il main del programma
*
*/

package main;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;

/** 
* @class Main
* 
* @brief Classe che gestisce il programma
* 
* Questa classe serve per far partire il programma creando la finestra e inserendo il pannello con il gioco all'interno della finestra
*/


/** @mainpage Main
* viene creata la finestra con dentro il pannello
* viene creata l'immagine icona dell'applicazione che verrà visualizzata nella barra delle applicazioni
* viene fatto partire il thread del gioco
*/
public class Main {

    public static void main(String[] args) {

        /** Crea nuova finestra */
        JFrame window = new JFrame(); 

        /** Imposta chiusura finestra */
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Imposta dimensione finestra */
        window.setResizable(false);

        /** Imposta titolo finestra */
        window.setTitle("Until Down");

        
        /** Creazione del pannello di gioco */
        GamePanel gamePanel = new GamePanel();

        /** Aggiunta del pannello di gioco alla finestra */
        window.add(gamePanel);

        /** Fai vedere il Jpanel sul Jframe */
        window.pack();

        /** Spawn della finestra al centro dello schermo */
        window.setLocationRelativeTo(null);

        /** Rendi la finestra visibile */
        window.setVisible(true);

        //Icona applicazione
        try {
            BufferedImage icon = ImageIO.read(Main.class.getResource("/src/menu/AppIcon.png"));
            /** Imposta icona applicazione */
            window.setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dell'icona: " + e.getMessage());
        }

        /** Inizio thread del gamePanel */
        gamePanel.StartGameThread();

    }
}