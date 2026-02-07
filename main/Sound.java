/**
* @version 1.0
* @file Sound.java 
* 
* @brief File che contiene la classe Sound
*
*/

package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** 
* @class Sound
* 
* @brief Classe che gestisce i souni nel gioco
* 
* Questa classe serve per caricare i suoni nel gioco e per farli partire durante il gioco
*/ 

public class Sound {

    /** Oggetto Clip che gestisce la riproduzione dell’audio */
    Clip clip;

    /** Array di URL che contengono i percorsi dei file audio */
    URL soundURL[] = new URL[30];


    /**
     @brief Costruttore della classe Sound.

    Costruttore della classe Sound che inserisce i file audio nell'array soundURL
    */
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


    /**
     @brief Imposta il file audio da riprodurre usando l'indice dell'array.

    Metodo che carica l'audio del file.
    Il file lo brende dal vettore basandosi sull'indice in parametro
    @param  i indice del vettore da cui prendere il file
    */
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


    /**
     @brief Riproduce il suono.

    Metodo che riproduce una sola volta il suono caricato
    */
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


    /**
     @brief Riproduce il suono in loop continuo.

    Metodo che riproduce in loop continuo il suono caricato
    */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     @brief Ferma la riproduzione del suono.

    Metodo che ferma la riproduzione del suono in esecuzione
    */
    public void stop() {
        clip.stop();
    }
}
