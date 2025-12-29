package tile;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import entity.Player;
import java.io.InputStream;
import java.io.InputStreamReader;
import main.GamePanel;
import main.Sound;


public class TileManager {
    //JPanel su qui lavora il gico
    GamePanel gp;
    //Plaeyr
    Player pl;
    //vettore di immagini
    Tile[] tile;
    //matrice di posizionameto Tile
    int maptileNum [][];
    //mappa che e attualmete visualizzata
    String currentMap = "";
    //cicle del giorno (DAY) (NIGHT)
    int CurrentCicle = 0;
    //music (ON) (OFF) se riproduci musica o no
    boolean music = false;
    //aggiungo suono per transizione
    Sound transiton = new Sound();

public TileManager(GamePanel gp,Player pl){
    this.gp = gp;
    //assegno lunghezza vettore
    this.tile = new Tile[10];
    //matrice grandezza MaxScreenCol && MaXScreenRow
    maptileNum = new int[gp.MaxScreenCol][gp.MaxScreenRow];
    this.pl = pl;
    //carico mappa centrale
    LoadMap("/Asset/maps/center.txt");
    //segno che mappo stiamo mostrando
    currentMap = "center";
    //setto il ciclo a DAY
    gp.cicle = "DAY";
    //setto i tile per il ciclo in base a gp.cicle (IN QUESTO CASO USO I TILE PER IL DAY)
    GetTileBaseCicle();
    
    
}
public void update()
{
    //OGNI SECONDO
    //aumento di uno CurrentCicle per vedere tra quanto cambiare da giorno a notte
    CurrentCicle ++;
    //poi controllo se devo cambiare i tile da usare per disegnare la mappa
    CurrentCicleSet();
    //in base a dove si trova il Player cambio la mappa da far vedere
    //se il palyer dopo la riga 0 e la mappa e center:
    if (pl.playerRow < 0 && currentMap.equals("center")) {
    //carico la mappa per la stanza inalto
    LoadMap("/Asset/maps/top.txt");
    //dico che la mappa che stiamo usando e top
    currentMap = "top";
    //metto il player alle cordinate che diano l'illusione si uscire dalla porta di stanza top
    pl.x = 300;
    pl.y = 450;
    //poi setto che la musica da usare e la 3 (musica di transazione tra stanze)
    transiton.setFile(3);
    //poi la riproduco per 1 volta
    transiton.play();
    }
    //COSI PER RIGHT LEFT DOWN e tutti gli altri casi:
    else if (pl.playerRow > 9 && currentMap.equals("top")) {
    LoadMap("/Asset/maps/center.txt");
    currentMap = "center";
    pl.x = 300;
    pl.y = 0;
    transiton.setFile(3);
    transiton.play();
    }
    else if (pl.playerCol < -1 && currentMap.equals("center")) {
    LoadMap("/Asset/maps/right.txt");
    currentMap = "right";
    pl.x = 600;
    pl.y = 200;
    transiton.setFile(3);
    transiton.play();
    }
    else if (pl.playerCol > 13 && currentMap.equals("right")) {
    LoadMap("/Asset/maps/center.txt");
    currentMap = "center";
    pl.x = -50;
    pl.y = 200;
    transiton.setFile(3);
    transiton.play();
    }
    else if (pl.playerCol > 13 && currentMap.equals("center")) {
    LoadMap("/Asset/maps/left.txt"); 
    currentMap = "left";
    pl.x = -50;
    pl.y = 200;
    transiton.setFile(3);
    transiton.play();
    }
    else if (pl.playerCol < -1 && currentMap.equals("left")) {
    LoadMap("/Asset/maps/center.txt");
    currentMap = "center";
    pl.x = 630;
    pl.y = 200;
    transiton.setFile(3);
    transiton.play();
    }
    else if (pl.playerRow > 9 && currentMap.equals("center")) {
    LoadMap("/Asset/maps/down.txt");
    currentMap = "down";
    pl.x = 300;
    pl.y = -60;
    transiton.setFile(3);
    transiton.play();
    }
    else if (pl.playerRow < -1 && currentMap.equals("down")) {
    LoadMap("/Asset/maps/center.txt");
    currentMap = "center";
    pl.x = 300;
    pl.y = 450;
    transiton.setFile(3);
    transiton.play();
    }

}
public void GetTileImgNight()
{
    //carico nel vettore i tile che devo usare (LA VERSIONE NOTTURNA) /Asset/Tile/NIGHT
    try{
        tile[0] = new Tile();
        tile[0].image = ImageIO.read(getClass().getResource("/Asset/Tile/NIGHT/grass1.png"));
        
        tile[1] = new Tile(); 
        tile[1].image = ImageIO.read(getClass().getResource("/Asset/Tile/NIGHT/rock.png"));

        tile[2] = new Tile();
        tile[2].image = ImageIO.read(getClass().getResource("/Asset/Tile/NIGHT/water.png"));

        tile[3] = new Tile();
        tile[3].image = ImageIO.read(getClass().getResource("/Asset/Tile/NIGHT/gras.png"));

        tile[4] = new Tile();
        tile[4].image = ImageIO.read(getClass().getResource("/Asset/Tile/NIGHT/grasss.png"));

        tile[5] = new Tile();
        tile[5].image = ImageIO.read(getClass().getResource("/Asset/Tile/NIGHT/tree.png"));

        tile[6] = new Tile();
        tile[6].image = ImageIO.read(getClass().getResource("/Asset/Tile/NIGHT/bush.png"));



    }catch(IOException e)
    {
        //se non trova i sul terminale vedro apparire lo stack trace : UN ERRORE 
        e.printStackTrace();
    }


}
public void GetTileImgDay(){
try{
    //carico nel vettore i tile che devo usare (LA VERSIONE GIORNO) /Asset/Tile/DAY
        tile[0] = new Tile();
        tile[0].image = ImageIO.read(getClass().getResource("/Asset/Tile/DAY/grass1.png"));
        
        tile[1] = new Tile(); 
        tile[1].image = ImageIO.read(getClass().getResource("/Asset/Tile/DAY/rock.png"));

        tile[2] = new Tile();
        tile[2].image = ImageIO.read(getClass().getResource("/Asset/Tile/DAY/water.png"));

        tile[3] = new Tile();
        tile[3].image = ImageIO.read(getClass().getResource("/Asset/Tile/DAY/gras.png"));

        tile[4] = new Tile();
        tile[4].image = ImageIO.read(getClass().getResource("/Asset/Tile/DAY/grasss.png"));

        tile[5] = new Tile();
        tile[5].image = ImageIO.read(getClass().getResource("/Asset/Tile/DAY/tree.png"));

        tile[6] = new Tile();
        tile[6].image = ImageIO.read(getClass().getResource("/Asset/Tile/DAY/bush.png"));



    }catch(IOException e)
    {
         //se non trova i sul terminale vedro apparire lo stack trace : UN ERRORE 
        e.printStackTrace();
    }
}
public void LoadMap(String Map)
{
    try
    {
        InputStream is = getClass().getResourceAsStream(Map);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int col = 0;
        int row = 0;
        while (col < gp.MaxScreenCol && row < gp.MaxScreenRow) {
            String line = br.readLine();

            while (col < gp.MaxScreenCol) {
                String numb[] = line.split(" ");

                int num =  Integer.parseInt(numb[col]);

                maptileNum[col][row] = num;
                col++;
            }
            if(col == gp.MaxScreenCol){
                col = 0;
                row++;
            }
        }

        br.close();
    }catch(Exception e)
    {
    
    }
}
public void draw(Graphics2D g2){

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
        if(col == gp.MaxScreenCol){

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
public void CurrentCicleSet()
{
    //avvio la musica da far paritire in base al bg
    //la musica DI BG
    SetMUSIC();
    //dopo che il tick = currentCicle a raggiunto i 1000 cambio ciclo
    if (CurrentCicle >= 1000) {
        //se era giorno
        if (gp.cicle.equals("DAY")) {
            //notte
            gp.cicle = "NIGHT";
            //fermo musica giorno
            gp.FermaMusica();
            
        } else {
            // se era notte metto giorno
            gp.cicle = "DAY";
            //fermo musica notte
            gp.FermaMusica();
           
            
        }
        //rifaccio ripartire il tick
        CurrentCicle = 0;
        //setto i tile in base al cicle cosi se e notte usiamo i tile di notte
        GetTileBaseCicle(); 
        //dico che non sta andando nessuna musica di BG
        music = false;
}

}
public void SetMUSIC()
{
    //se il ciclo e uguale a day e non ce muscia
    if (gp.cicle.equals("DAY") && !music) {
    //faccio partire la muscia numero 2 (MUSICA BG DEL GIORNO)
    gp.avviaMusica(2); 
    //ora sta andando della musica
    music = true;
    }
    //se il ciclo e uguale a night e non ce muscia
    else if (gp.cicle.equals("NIGHT") && !music) {
        //faccio partire la muscia numero 0 (MUSICA BG DEL NIGHT)
        gp.avviaMusica(0);
        //ora sta andando della musica
        music = true;
    }
}
public void GetTileBaseCicle()
{
    //se e giorno
    if(gp.cicle.equals("DAY"))
    {
        //usa per disegnare mappa i tile del giorno
        GetTileImgDay();
    }
    //se e notte
    else if(gp.cicle.equals("NIGHT"))
    {
        //usa per disegnare mappa i tile (immagini) della notte
        GetTileImgNight();
    }
}


}
