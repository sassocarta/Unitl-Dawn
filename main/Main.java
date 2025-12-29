package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        // nuova finestra
        JFrame window = new JFrame();
        // close operation X finstra = out
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // dimesione fissa, non modificabile
        window.setResizable(false);
        // titolo
        window.setTitle("PROVA2.GAME");
        // aggiungiamo al JFrame il nostro Jpanel(GamePanel)
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // per vedere il Jpanel sul Jframe
        window.pack();

        // spaw delle finestra = centro schermo
        window.setLocationRelativeTo(null);
        // finestra visibile
        window.setVisible(true);

        // startiamo subito il thread
        gamePanel.StartGameThread();

    }
}