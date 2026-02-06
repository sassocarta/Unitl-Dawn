package main;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;


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

        //icona app
        try {
            BufferedImage icon = ImageIO.read(Main.class.getResource("/src/menu/AppIcon.png"));
            window.setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dell'icona: " + e.getMessage());
        }


        // startiamo il thread
        gamePanel.StartGameThread();

    }
}