package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, dowPressed, leftPressed, rightPressed, EPressed, Pressed1, Pressed2, Pressed3;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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
