package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class MouseHandler extends MouseAdapter {

    public boolean leftPressed; 
    public boolean rightPressed;
    public boolean mouseClicked;
    public int mouseX, mouseY;

    public boolean canAttack = true;

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

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftPressed = false;
            mouseClicked = false; // Reset quando rilasci
            canAttack = true;
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Fondamentale per sapere dove si trova il mouse anche senza cliccare
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Aggiorna le coordinate anche se trascini il mouse
        mouseX = e.getX();
        mouseY = e.getY();
    }
}