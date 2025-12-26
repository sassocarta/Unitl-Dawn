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
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftPressed = true;
            System.out.println("Premuto tasto sinistro");
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            rightPressed = true;
            System.out.println("Premuto tasto destro");
        }
    }

    // Se tasto sinistro mouse rilasciato
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftPressed = false;
            System.out.println("Rilasciato tasto sinistro");
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            rightPressed = false;
            System.out.println("Rilasciato tasto destro");
        }
    }
}
