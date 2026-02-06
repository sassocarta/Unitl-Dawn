package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    // Oggetto Clip che gestisce la riproduzione dell’audio
    Clip clip;

    // Array di URL che contengono i percorsi dei file audio
    URL soundURL[] = new URL[30];

    // Costruttore della classe Sound
    public Sound() {
        try {
            // Caricamento dei file audio dalla cartella delle risorse

            soundURL[0] = getClass().getResource("/src/Sound/nightMusic.wav");
            soundURL[1] = getClass().getResource("/src/Sound/hit.wav");
            soundURL[2] = getClass().getResource("/src/Sound/SD.wav");
            soundURL[3] = getClass().getResource("/src/Sound/transiton.wav");
            soundURL[4] = getClass().getResource("/src/Sound/entering.wav");
            soundURL[5] = getClass().getResource("/src/Sound/dayMusic.wav");
            soundURL[6] = getClass().getResource("/src/Sound/speek.wav");
            soundURL[7] = getClass().getResource("/src/Sound/coin.wav");
            soundURL[8] = getClass().getResource("/src/Sound/slimehit.wav");
            soundURL[9] = getClass().getResource("/src/Sound/Takehit.wav");
            soundURL[10] = getClass().getResource("/src/Sound/menuMusic.wav");
            
        } catch (Exception e) {
            // In caso di errore nel caricamento dei file
            e.printStackTrace();
        }
    }

    // Imposta il file audio da riprodurre usando l'indice dell'array
    public void setFile(int i) {
        try {
            // Ottiene lo stream audio dal file WAV
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

            // Crea un nuovo Clip
            clip = AudioSystem.getClip();

            // Carica lo stream audio nel Clip
            clip.open(ais);
        } catch (Exception e) {
            // Stampa l’errore in caso di problemi
            e.printStackTrace();
        }
    }

    // Riproduce il suono una sola volta
    public void play() {

        // Se il clip non è stato inizializzato, esce dal metodo
        if (clip == null)
            return;

        // Ferma il clip se è già in riproduzione
        clip.stop();

        // Riporta il clip all'inizio
        clip.setFramePosition(0);

        // Avvia la riproduzione del suono
        clip.start();
    }

    // Riproduce il suono in loop continuo
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Ferma la riproduzione del suono
    public void stop() {
        clip.stop();
    }
}
