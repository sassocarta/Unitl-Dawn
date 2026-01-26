package entity.NPCS;
import java.awt.Graphics2D;
import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class NPC_Vector_main {

    NPC_Manager npcM;
    Cnpc [] npcs;
    GamePanel gp;
    Player pl;
    Sound sd;
    TileManager tm;
    int numeroNpc = 5;

    public NPC_Vector_main(GamePanel gp, Player pl, Sound sd, TileManager tm)
    {
        this.gp = gp;
        this.pl = pl;
        this.sd = sd;
        this.tm = tm;

        this.npcM = new NPC_Manager();
        this.npcs = new Cnpc[numeroNpc];

        npcM.setGp(this.gp);
        npcM.setPl(this.pl);
        npcM.setSd(this.sd);
        npcM.setTm(this.tm);

        CreateNpcs();
    }

    public void CreateNpcs()
    {
        for(int i =0;i<numeroNpc;i++){
            npcs[i] = new Cnpc(gp, pl, sd, tm,6,"/src/NPC/NPC_type1/FACE/face",3,"/src/NPC/NPC_type1/up/.",3,"/src/NPC/NPC_type1/down/.",3,"/src/NPC/NPC_type1/left/.",3,"/src/NPC/NPC_type1/right/.");
        }
    }


    public void update()
    {
        for(int i = 0; i < numeroNpc; i++)
        {
            npcs[i].update();
        }
    }

        public void draw(Graphics2D g2)
    {
        for(int i = 0; i < numeroNpc; i++)
        {
            npcs[i].draw(g2);
        }
    }

    
}
