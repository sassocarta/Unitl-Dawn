package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Player extends Entity {
    // Jpanel
    GamePanel gp;
    // keylissener (main. [me lo fa mettere lui non so cosa serva])
    main.KeyHandler keyH;
    main.MouseHandler mousH;
    BufferedImage blR1, blR2, blR3, blL1, blL2, blL3;
    int BlockSpriteCounter = 0;
    int BlockSpriteNum = 1;
    String bolck;

    public Player(GamePanel gp, main.KeyHandler keyH2, main.MouseHandler mousH) {
        this.gp = gp;
        this.keyH = keyH2;
        this.mousH = mousH;

        setDefaultValues();
        getPlayerImg();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "rg1";

    }

    // carichiamo sulle immagini Buffer le immagini nella cartella con segunete
    // posizione
    public void getPlayerImg() {
        try {
            rg1 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile1.png"));

            rg2 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile2.png"));

            rg3 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile3.png"));

            rg4 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile4.png"));

            rg5 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile5.png"));

            rg6 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile6.png"));

            rg7 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile7.png"));

            rg8 = ImageIO.read(getClass().getResource("/Asset/Player/Player_RIGHT/Player_walk_right/tile8.png"));

            lf1 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left1.png"));

            lf2 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left2.png"));

            lf3 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left3.png"));

            lf4 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left4.png"));

            lf5 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left5.png"));

            lf6 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left6.png"));

            lf7 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left7.png"));
            
            lf8 = ImageIO.read(getClass().getResource("/Asset/Player/Player_LEFT/Player_walk_left/tile_left8.png"));

            rgA1 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right1.png"));
            rgA2 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right2.png"));
            rgA3 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right3.png"));
            rgA4 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right4.png"));
            rgA5 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right5.png"));
            rgA6 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right6.png"));
            rgA7 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right7.png"));
            rgA8 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_attack1_right/tile_attack1_right8.png"));

            lfA1 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left8.png"));
            lfA2 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left7.png"));
            lfA3 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left6.png"));
            lfA4 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left5.png"));
            lfA5 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left4.png"));
            lfA6 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left3.png"));
            lfA7 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left2.png"));
            lfA8 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_LEFT/Player_attack1_left/tile_attack1_left1.png"));

            blR1 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_block_right/tile_block_right1.png"));
            blR2 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_block_right/tile_block_right2.png"));
            blR3 = ImageIO.read(
                    getClass().getResource("/Asset/Player/Player_RIGHT/Player_block_right/tile_block_right3.png"));
                    
            // pre qualche motvo gli sheet della parata a destra erano al contrario, quindi gli ultimi sono i primi
            blL1 = ImageIO
                    .read(getClass().getResource("/Asset/Player/Player_LEFT/Player_block_left/tile_block_left6.png"));
            blL2 = ImageIO
                    .read(getClass().getResource("/Asset/Player/Player_LEFT/Player_block_left/tile_block_left5.png"));
            blL3 = ImageIO
                    .read(getClass().getResource("/Asset/Player/Player_LEFT/Player_block_left/tile_block_left4.png"));

        } catch (IOException e) {
            // se non trova le immagini stamapa
            e.printStackTrace();
        }
    }

    // metodo update del player
    public void update() {
        // se tasto premuto:
        // 1.sposto posizione player
        // 2.vado avanti di uno sprite nella animazione
        // 3.se sprite counter e minore di 10 (velocita animazione) piu basso numero piu
        // veloce animazione
        // 4.in base allo sprite precedente setto quello succesivo
        if (keyH.upPressed == true || keyH.dowPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {
            if (keyH.upPressed == true && mousH.rightPressed != true) {
                y -= speed; // 1
            } else if (keyH.dowPressed == true && mousH.rightPressed != true) {
                y += speed;
            } else if (keyH.leftPressed == true && mousH.rightPressed != true) {
                direction = "left"; // sto dicendo che deve guardare gli sprite di sinistra
                x -= speed;
            } else if (keyH.rightPressed == true && mousH.rightPressed != true) {
                direction = "right"; // sto dicendo che deve guardare gli sprite di destra
                x += speed;
            }

            SpriteCounter++; // 2
            if (mousH.leftPressed != true && mousH.rightPressed != true) {
                if (SpriteCounter > 5) { // 3
                    if (SpriteNum == 1) // 4
                    {
                        SpriteNum = 2;
                    } else if (SpriteNum == 2) {
                        SpriteNum = 3;
                    } else if (SpriteNum == 3) {
                        SpriteNum = 4;
                    } else if (SpriteNum == 4) {
                        SpriteNum = 5;
                    } else if (SpriteNum == 5) {
                        SpriteNum = 6;
                    } else if (SpriteNum == 6) {
                        SpriteNum = 7;
                    } else if (SpriteNum == 7) {
                        SpriteNum = 8;
                    } else if (SpriteNum == 8) {
                        SpriteNum = 1;
                    }

                    SpriteCounter = 0; // setto a 0
                }
            }
        }

        // se mouse tasto sinistro premuto
        if (mousH.leftPressed && mousH.rightPressed != true) {
            // da 0 passo a 1
            AttackSpriteCounter++;
            // guardo la direzione del Player
            if (direction.equals("left")) {
                Attack = "left";
            }
            if (direction.equals("right")) {
                Attack = "right";
            }
        }
        // se mouse tasto destro premuto
        if (mousH.rightPressed) {

            BlockSpriteCounter++;

            bolck = direction;

            if (BlockSpriteCounter > 2) {
                BlockSpriteNum++;

                if (BlockSpriteNum > 3) {

                    BlockSpriteNum = 3; // resta sull’ultima posa
                }
                BlockSpriteCounter = 0;
            }

        } else {
            // reset quando NON blocchi
            BlockSpriteNum = 1;
            BlockSpriteCounter = 0;
        }

        // scorro direzione
        if (AttackSpriteCounter > 2) {
            if (AttackSpriteNum == 1) // in base a il frame in qui mi trovo setto il prossimo
            {
                AttackSpriteNum = 2;
            } else if (AttackSpriteNum == 2) {
                AttackSpriteNum = 3;
            } else if (AttackSpriteNum == 3) {
                AttackSpriteNum = 4;
            } else if (AttackSpriteNum == 4) {
                AttackSpriteNum = 5;
            } else if (AttackSpriteNum == 5) {
                AttackSpriteNum = 6;
            } else if (AttackSpriteNum == 6) {
                AttackSpriteNum = 7;
            } else if (AttackSpriteNum == 7) {
                AttackSpriteNum = 8;
            } else if (AttackSpriteNum == 8) {
                AttackSpriteNum = 1;
            }

            AttackSpriteCounter = 0; // setto a 0
        }

        if (BlockSpriteCounter > 2) {
            if (BlockSpriteNum == 1) // in base a il frame in qui mi trovo setto il prossimo
            {
                BlockSpriteNum = 2;
            } else if (BlockSpriteNum == 2) {
                BlockSpriteNum = 3;
            }
            if (mousH.rightPressed == false) {
                BlockSpriteNum = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null; // immagine che verra stampata: a null Player che cammina
        BufferedImage Aimage = null; // immagine che verra stampata: a null Player che attacca
        BufferedImage Blimage = null; // immagine che verra stampata: a null Player che attacca

        // finche il mouse non e premuto (tasto sinistro)
        if (mousH.leftPressed != true && mousH.rightPressed != true) {
            switch (direction) {
                case "left":
                    if (SpriteNum == 1) // in base a left o right e allo spriteNum decido che immagine caricare su image
                    {
                        image = lf1;
                    }
                    if (SpriteNum == 2) {
                        image = lf2;
                    }
                    if (SpriteNum == 3) {
                        image = lf3;
                    }
                    if (SpriteNum == 4) {
                        image = lf4;
                    }
                    if (SpriteNum == 5) {
                        image = lf5;
                    }
                    if (SpriteNum == 6) {
                        image = lf6;
                    }
                    if (SpriteNum == 7) {
                        image = lf7;
                    }
                    if (SpriteNum == 8) {
                        image = lf8;
                    }
                    break;
                case "right":
                    if (SpriteNum == 1) {
                        image = rg1;
                    }
                    if (SpriteNum == 2) {
                        image = rg2;
                    }
                    if (SpriteNum == 3) {
                        image = rg3;
                    }
                    if (SpriteNum == 4) {
                        image = rg4;
                    }
                    if (SpriteNum == 5) {
                        image = rg5;
                    }
                    if (SpriteNum == 6) {
                        image = rg6;
                    }
                    if (SpriteNum == 7) {
                        image = rg7;
                    }
                    if (SpriteNum == 8) {
                        image = rg8;
                    }
                    break;
                default:
                    image = rg1;
                    break;
            }
        }

        if (mousH.leftPressed == true) {
            switch (Attack) {
                case "left":
                    if (AttackSpriteNum == 1) // in base a left o right e allo AttackSpriteNum decido che immagine
                                              // caricare su image
                    {
                        Aimage = lfA1;
                    }
                    if (AttackSpriteNum == 2) {
                        Aimage = lfA2;
                    }
                    if (AttackSpriteNum == 3) {
                        Aimage = lfA3;
                    }
                    if (AttackSpriteNum == 4) {
                        Aimage = lfA4;
                    }
                    if (AttackSpriteNum == 5) {
                        Aimage = lfA5;
                    }
                    if (AttackSpriteNum == 6) {
                        Aimage = lfA6;
                    }
                    if (AttackSpriteNum == 7) {
                        Aimage = lfA7;
                    }
                    if (AttackSpriteNum == 8) {
                        Aimage = lfA8;
                    }
                    break;
                case "right":
                    if (AttackSpriteNum == 1) {
                        Aimage = rgA1;
                    }
                    if (AttackSpriteNum == 2) {
                        Aimage = rgA2;
                    }
                    if (AttackSpriteNum == 3) {
                        Aimage = rgA3;
                    }
                    if (AttackSpriteNum == 4) {
                        Aimage = rgA4;
                    }
                    if (AttackSpriteNum == 5) {
                        Aimage = rgA5;
                    }
                    if (AttackSpriteNum == 6) {
                        Aimage = rgA6;
                    }
                    if (AttackSpriteNum == 7) {
                        Aimage = rgA7;
                    }
                    if (AttackSpriteNum == 8) {
                        Aimage = rgA8;
                    }
                    break;
                default:
                    Aimage = rgA1;
                    break;
            }
        }

        if (mousH.rightPressed == true) {
            switch (bolck) {
                case "left":
                    if (BlockSpriteNum == 1) // in base a left o right e allo AttackSpriteNum decido che immagine
                                             // caricare su image
                    {
                        Blimage = blL1;
                    }
                    if (BlockSpriteNum == 2) {
                        Blimage = blL2;
                    }
                    if (BlockSpriteNum == 3) {
                        Blimage = blL3;
                    }
                    break;
                case "right":
                    if (BlockSpriteNum == 1) // in base a left o right e allo AttackSpriteNum decido che immagine
                                             // caricare su image
                    {
                        Blimage = blR1;
                    }
                    if (BlockSpriteNum == 2) {
                        Blimage = blR2;
                    }
                    if (BlockSpriteNum == 3) {
                        Blimage = blR3;
                    }
                    break;
                default:
                    Blimage = blR1;
                    break;
            }
        }

        // DISEGNO USANDO LA GRAPHIC2D LE IMMAGINI
        g2.drawImage(image, x, y, gp.tileSize * 4, gp.tileSize * 4, null); // disegno image con x e y del player (o
                                                                           // dovuto aumentare la dimensione del
                                                                           // immagine[*4] )
        g2.drawImage(Aimage, x, y, gp.tileSize * 4, gp.tileSize * 4, null); // disegno image con x e y del player (o
                                                                            // dovuto aumentare la dimensione del
                                                                            // immagine[*4] )
        g2.drawImage(Blimage, x, y, gp.tileSize * 4, gp.tileSize * 4, null); // disegno image con x e y del player (o
                                                                             // dovuto aumentare la dimensione del
                                                                             // immagine[*4] )
    }

}
