package entity;

import java.awt.image.BufferedImage;

//superclasse per tutte le entita

public class Entity {
    //posizione del entita
    public int x,y;
    //velocita entita
    public int speed;
    //immagini su qui caricheremo le immagini in png
    public BufferedImage rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8, lf1,lf2,lf3,lf4,lf5,lf6,lf7,lf8;
    //direzione (tipo di animazione (left) (right))
    public String direction;
    
    public int SpriteCounter = 0;
    public int SpriteNum = 1;

     
}