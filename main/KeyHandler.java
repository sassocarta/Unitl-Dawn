/**
* @version 1.0
* @file KeyHandler.java 
* 
* @brief File che contiene la classe KayHandler
*
*/

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/** 
* @class KayHandler
* 
* @brief Classe che gestisce gli input della tastiera
* 
* Questa classe serve controllare i tasti della tastiera e i loro input
*/

public class KeyHandler implements KeyListener {

    /** variabili che controllano se i tasti W, A, S, D, E, 1, 2, 3 sono stati premuti*/
    public boolean upPressed, dowPressed, leftPressed, rightPressed, EPressed, Pressed1, Pressed2, Pressed3;

    /** JPanel su qui lavora il gico */
    GamePanel gp;

    
    /**
     @brief Costruttore del KayHandler.
     
     Costruttore del KayHandler
    @param  gp JPanel su qui lavora il gico
    */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    /**
     @brief Controlla se un tasto della tastiera è stato premuto.
     
     questo metodo controlla se uno dei tasti della tastiera è stato premuto attraverso keyEvent
    @param  e libreria esterna che permette il controllo della tastiera
    */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     @brief Controlla se un tasto della tastiera è stato premuto.
     
     questo metodo controlla se uno dei tasti della tastiera è stato premuto attraverso keyEvent
    @param  e libreria esterna che permette il controllo della tastiera
    */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            dowPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;

        }
        if (code == KeyEvent.VK_E) {
            EPressed = true;

        }
        if (code == KeyEvent.VK_1) {
            Pressed1 = true;

        }
        if (code == KeyEvent.VK_2) {
            Pressed2 = true;

        }
        if (code == KeyEvent.VK_3) {
            Pressed3 = true;

        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }


    /**
     @brief Controlla se un tasto della tastiera è stato rilasciato.
     
     questo metodo controlla se uno dei tasti della tastiera è stato rilasciato attraverso keyEvent
    @param  e libreria esterna che permette il controllo della tastiera
    */

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            dowPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            EPressed = false;

        }
        if (code == KeyEvent.VK_1) {
            Pressed1 = false;

        }
        if (code == KeyEvent.VK_2) {
            Pressed2 = false;

        }
        if (code == KeyEvent.VK_3) {
            Pressed3 = false;

        }
    }

}
