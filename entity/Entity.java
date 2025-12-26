package entity;

import java.awt.image.BufferedImage;

//superclasse per tutte le entita

public class Entity {
    //posizione del entita
    public int x,y;
    //velocita entita
    public int speed;
    //immagini su qui caricheremo le immagini in png
    public BufferedImage rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8, lf1,lf2,lf3,lf4,lf5,lf6,lf7,lf8, rgA1,rgA2,rgA3,rgA4,rgA5,rgA6,rgA7,rgA8, lfA1,lfA2,lfA3,lfA4,lfA5,lfA6,lfA7,lfA8;
    //direzione (tipo di animazione (left) (right))
    public String direction;
    
    public String Attack;
    public int SpriteCounter = 0;
    public int SpriteNum = 1;
    public int AttackSpriteCounter = 0;
    public int AttackSpriteNum = 1;

     
}