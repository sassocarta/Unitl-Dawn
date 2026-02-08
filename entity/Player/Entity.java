/**
* @version 1.0
* @file Entity.java 
* 
* @brief File che contiene la classe Entity
*
*/

package entity.Player;

import java.awt.image.BufferedImage;

/** 
* @class Entity
* 
* @brief Classe che permette di creare tutte le entità
* 
* Questa classe è la superclasse per tutte le entita.
* da qui tutte le entità ereditano degli attributi.
*/
public class Entity {
    /** posizione dell'entità*/
    public int x, y;

    /** velocità dell'entità*/
    public int speed;

    /** immagini dei movimenti su cui verranno caricate le immagini PNG*/
    public BufferedImage rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8, lf1, lf2, lf3, lf4, lf5, lf6, lf7, lf8, rgA1, rgA2, rgA3, rgA4, rgA5, rgA6, rgA7, rgA8, lfA1, lfA2, lfA3, lfA4, lfA5, lfA6, lfA7, lfA8;
    
    /** direzione dell'entità*/
    public String direction;

    /** attacco dell'entità*/
    public String Attack;

    /** contatore dei frame di movimento dell'entità*/
    public int SpriteCounter = 0;

    /** numero di frame di movimento dell'entità*/
    public int SpriteNum = 1;

    /** contatore dei frame di attacco dell'entità*/
    public int AttackSpriteCounter = 0;

    /** numero di frame di attacco dell'entità*/
    public int AttackSpriteNum = 1;
}