/**
* @version 1.0
* @file MouseHandler.java 
* 
* @brief File che contiene la classe MouseHandler
*
*/

package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/** 
* @class MouseHandler
* 
* @brief Classe che gestisce il mouse e gli input del mouse
* 
* Questa classe serve controllare il click sia destro che sinistro del mouse
*/

public class MouseHandler extends MouseAdapter {

    /** Se il tasto sinistro è stato premuto */
    public boolean leftPressed; 

    /** Se il tasto destro è stato premuto */
    public boolean rightPressed;

    /** Se il un pulsante del mouse è stato premuto */
    public boolean mouseClicked;

    /** Coordinate del click del mouse */
    public int mouseX, mouseY;

    /** Se il giocatore può attaccare */
    public boolean canAttack = true;

    /**
     @brief Controlla se un pulsante del mouse è stato premuto.
     
     questo metodo controlla se uno dei due pulsanti del mouse è stato premuto attraverso mouseEvent
    @param  e libreria esterna che permette il controllo dei click
    */
    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (SwingUtilities.isLeftMouseButton(e) && !rightPressed) {
            mouseClicked = true;
            if (canAttack) {
                leftPressed = true;
                canAttack = false; 
            }
        }
        if (SwingUtilities.isRightMouseButton(e) && !leftPressed) {
            rightPressed = true;
        }
    }

    /**
     @brief Controlla se un pulsante del mouse è stato rilasciato.
     
     questo metodo controlla se uno dei due pulsanti del mouse è stato rilasciato attraverso mouseEvent
    @param  e libreria esterna che permette il controllo dei click
    */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftPressed = false;
            mouseClicked = false;
            canAttack = true;
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            rightPressed = false;
        }
    }

    /**
     @brief Controlla le coordinate del mouse.
     
     questo metodo controlla le coordinate del mouse anche senza cliccare
    @param  e libreria esterna che permette il controllo dei click
    */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }


    /**
     @brief Controlla le coordinate del mouse.
     
     questo metodo controlla le coordinate del mouse anche senza cliccare
    @param  e libreria esterna che permette il controllo dei click
    */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}