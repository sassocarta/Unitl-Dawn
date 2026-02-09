/**
* @version 1.0
* @file TileManager.java 
* 
* @brief Contiene classe TileManager: Classe che stampa i Tile di Tile.java leggendo file di testo
*
*/

package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import entity.Player.Player;

import java.io.InputStream;
import java.io.InputStreamReader;
import main.GamePanel;
import main.Sound;


/** 
* @class TileManager
* 
* @brief Classe che stampa i Tile di Tile.java leggendo file di testo
* 
* Questa classe permette di stampare a schermo le caselle della mappa.
* Ogni tipo di casella equivale a un numero.
* Il tipo di Casella viene letto da file di testo esterni che contengono tutti i numeri posizionati in base alla posizione che dovra avere la casella sullo schermo.
*/ 
public class TileManager {
    /** JPanel su qui lavora il gico */
    GamePanel gp;
    /** Player */
    Player pl;
    /** Vettore della immagini dei tile */
    Tile[] tile;
    /** matrice di posizionameto dei Tile */
    public int maptileNum[][];
    /** mappa attualmente visualizzata su schermo */
    public String currentMap = "";
    /** Ciclo giorno/notte */
    public boolean npcForcingNight = false;
    /** Riproduzi musica */
    public boolean music = false;
    /** suono di transizione tra mappe */
    Sound transiton = new Sound();
    /** valiebile per controllare se tutti i mostri sono morti */
    public boolean isAllDead;

    /**
     @brief costruttore.

    Costruttore di TileManager
    @param  gp JPanel su qui lavora il gico
    @param  pl Player
    */
    public TileManager(GamePanel gp, Player pl) {
        this.gp = gp;
        // assegno lunghezza vettore
        this.tile = new Tile[10];
        // matrice grandezza MaxScreenCol && MaXScreenRow
        maptileNum = new int[gp.MaxScreenCol][gp.MaxScreenRow];
        this.pl = pl;
        // carico mappa centrale
        LoadMap("/src/maps/center.txt");
        // segno che mappo stiamo mostrando
        currentMap = "center";
        // setto il ciclo a DAY
        gp.cicle = "DAY";
        // setto i tile per il ciclo in base a gp.cicle (IN QUESTO CASO USO I TILE PER
        // IL DAY)
        //gp.avviaMusica(5);
        //this.music = true;
        GetTileBaseCicle();

    }

    /**
     @brief Update.

    Metodo update che aggiorna l'oggetto a ogni frame.
    */
    public void update() {
        // OGNI SECONDO
        // poi controllo se devo cambiare i tile da usare per disegnare la mappa
        CurrentCicleSet();
        // in base a dove si trova il Player cambio la mappa da far vedere
        // se il palyer dopo la riga 0 e la mappa e center:
        if (pl.playerRow < 0 && currentMap.equals("center")) {
            // carico la mappa per la stanza inalto
            LoadMap("/src/maps/top.txt");
            // dico che la mappa che stiamo usando e top
            currentMap = "top";
            // metto il player alle cordinate che diano l'illusione si uscire dalla porta di
            // stanza top
            pl.x = pl.x;
            pl.y = 450;
            // poi setto che la musica da usare e la 3 (musica di transazione tra stanze)
            transiton.setFile(3);
            // poi la riproduco per 1 volta
            transiton.play();
        }
        // COSI PER RIGHT LEFT DOWN e tutti gli altri casi:
        else if (pl.playerRow > 9 && currentMap.equals("top")) {
            LoadMap("/src/maps/center.txt");
            currentMap = "center";
            pl.x = pl.x;
            pl.y = 0;
            transiton.setFile(3);
            transiton.play();
        } else if (pl.playerCol < -1 && currentMap.equals("center")) {
            LoadMap("/src/maps/right.txt");
            currentMap = "right";
            pl.x = 600;
            pl.y = pl.y;
            transiton.setFile(3);
            transiton.play();
        } else if (pl.playerCol > 13 && currentMap.equals("right")) {
            LoadMap("/src/maps/center.txt");
            currentMap = "center";
            pl.x = -50;
            pl.y = pl.y;
            transiton.setFile(3);
            transiton.play();
        } else if (pl.playerCol > 13 && currentMap.equals("center")) {
            LoadMap("/src/maps/left.txt");
            currentMap = "left";
            pl.x = -50;
            pl.y = pl.y;
            transiton.setFile(3);
            transiton.play();
        } else if (pl.playerCol < -1 && currentMap.equals("left")) {
            LoadMap("/src/maps/center.txt");
            currentMap = "center";
            pl.x = 630;
            pl.y = pl.y;
            transiton.setFile(3);
            transiton.play();
        } else if (pl.playerRow > 9 && currentMap.equals("center")) {
            LoadMap("/src/maps/down.txt");
            currentMap = "down";
            pl.x = pl.x;
            pl.y = -60;
            transiton.setFile(3);
            transiton.play();
        } else if (pl.playerRow < -1 && currentMap.equals("down")) {
            LoadMap("/src/maps/center.txt");
            currentMap = "center";
            pl.x = pl.x;
            pl.y = 450;
            transiton.setFile(3);
            transiton.play();
        }

    }

    /**
     @brief Metodo che crea le caselle della notte e ci inserisce le immagini.

    Questo metodo serve a creare GLi oggetti tile della notte e a inserirli nel vettore di caselle.
    Successivamente per ogni tile inserisce l'immagine
    */
    public void GetTileImgNight() {
        // carico nel vettore i tile che devo usare (LA VERSIONE NOTTURNA)
        // /Asset/Tile/NIGHT
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/src/Tile/NIGHT/grass1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/src/Tile/NIGHT/rock.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/src/Tile/NIGHT/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/src/Tile/NIGHT/gras.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/src/Tile/NIGHT/grasss.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/src/Tile/NIGHT/tree.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/src/Tile/NIGHT/bush.png"));

        } catch (IOException e) {
            // se non trova i sul terminale vedro apparire lo stack trace : UN ERRORE
            e.printStackTrace();
        }

    }

    /**
     @brief Metodo che crea le caselle del giorno e ci inserisce le immagini.

    Questo metodo serve a creare GLi oggetti tile del giorno e a inserirli nel vettore di caselle.
    Successivamente per ogni tile inserisce l'immagine
    */
    public void GetTileImgDay() {
        try {
            // carico nel vettore i tile che devo usare (LA VERSIONE GIORNO) /Asset/Tile/DAY
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/src/Tile/DAY/grass1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/src/Tile/DAY/rock.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/src/Tile/DAY/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/src/Tile/DAY/gras.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/src/Tile/DAY/grasss.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/src/Tile/DAY/tree.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/src/Tile/DAY/bush.png"));

        } catch (IOException e) {
            // se non trova i sul terminale vedro apparire lo stack trace : UN ERRORE
            e.printStackTrace();
        }
    }

    /**
    @brief Metodo che inserisce le caselle nella matrice.

    Questo metodo serve per inserire nella matrice i numeri delle caselle.
    @param  Map la mappa da caricare e da stampare
    */
    public void LoadMap(String Map) {
        try {
            InputStream is = getClass().getResourceAsStream(Map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.MaxScreenCol && row < gp.MaxScreenRow) {
                String line = br.readLine();

                while (col < gp.MaxScreenCol) {
                    String numb[] = line.split(" ");

                    int num = Integer.parseInt(numb[col]);

                    maptileNum[col][row] = num;
                    col++;
                }
                if (col == gp.MaxScreenCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (Exception e) {

        }
    }

    /**
    @brief Metodo che stampa su schermo le caselle basandosi sulla matrice.

    Questo metodo serve per stampare tutte le caselle. Prende il  numero della casella dalla matrice e stampa quella casella.
    @param  g2 strumento per stampare in 2d
    */
    public void draw(Graphics2D g2) {

        // Indice della colonna corrente della mappa
        int col = 0;

        // Indice della riga corrente della mappa
        int row = 0;

        // Coordinata x sullo schermo dove disegnare il tile
        int x = 0;

        // Coordinata y sullo schermo dove disegnare il tile
        int y = 0;

        // Ciclo che scorre tutte le colonne e le righe visibili sullo schermo
        while (col < gp.MaxScreenCol && row < gp.MaxScreenRow) {

            // Recupera il numero del tile dalla mappa in base a colonna e riga
            int tileNum = maptileNum[col][row];

            // Disegna l'immagine del tile sullo schermo nella posizione (x, y)
            // usando la dimensione standard dei tile
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

            // Passa alla colonna successiva
            col++;

            // Sposta la posizione x alla destra del tile appena disegnato
            x += gp.tileSize;

            // Se si è raggiunta l'ultima colonna visibile
            if (col == gp.MaxScreenCol) {

                // Riparte dalla prima colonna
                col = 0;

                // Riporta la coordinata x all'inizio della riga
                x = 0;

                // Passa alla riga successiva
                row++;

                // Sposta la coordinata y verso il basso di un tile
                y += gp.tileSize;
            }
        }
    }

    /**
    @brief Metodo che cambia ciclo giorno/notte.

    Questo metodo serve per cambiare tempo nel gioco.>
    Quando viene richiamato, se è giorno fa diventare notte e se è notte fa diventare giorno.
    */
    public void CurrentCicleSet() {

        isAllDead = gp.ENEMIES.isAllDead();

        //Se la lista è vuota e è notte, diventa giorno
        if (isAllDead && gp.cicle.equals("NIGHT")) {
            gp.cicle = "DAY";
            gp.day++;
            npcForcingNight = false;
            //music = true; 

            GetTileBaseCicle();  

        }

        //attivazione notte
        else if (npcForcingNight && gp.cicle.equals("DAY")) {

            gp.cicle = "NIGHT";
            //music = true; 

            GetTileBaseCicle();  

        }

    }

    /**
    @brief Metodo che prende le immagini dei tile in base al tempo.

    Questo metodo serve per prendere le immagini dei tile in base al temp nel gioco. se è notte prende i tile della notte mentre se è giorno prende i tile del giorno.
    */
    public void GetTileBaseCicle() {
        // se e giorno
        if (gp.cicle.equals("DAY")) {
            // usa per disegnare mappa i tile del giorno
            GetTileImgDay();
        }
        // se e notte
        else if (gp.cicle.equals("NIGHT")) {
            // usa per disegnare mappa i tile (immagini) della notte
            GetTileImgNight();
        }
    }

}
