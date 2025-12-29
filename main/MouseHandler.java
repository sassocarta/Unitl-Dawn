package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class MouseHandler extends MouseAdapter {

    public boolean leftPressed; // bool per mouse
    public boolean rightPressed;

    @Override
    // Se tasto sinistro mouse premuto
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && rightPressed == false) {
            leftPressed = true;
        }
        if (SwingUtilities.isRightMouseButton(e) && leftPressed == false) {
            rightPressed = true;
        }
    }

    // Se tasto sinistro mouse rilasciato
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftPressed = false;
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            rightPressed = false;
        }
    }
}
