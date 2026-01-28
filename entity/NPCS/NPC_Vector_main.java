package entity.NPCS;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Player.Player;
import main.GamePanel;
import main.Sound;
import tile.TileManager;

public class NPC_Vector_main {

    NPC_Manager npcM;
    //lista
    ArrayList<Cnpc> npcs = new ArrayList<>();
    GamePanel gp;
    Player pl;
    Sound sd;
    TileManager tm;
    int numeroNpc1 = 5;
    int numeroNpc2 = 5;
    int numeroNpc3 = 5;
    boolean sequalcunoparla = false;
    public NPC_Vector_main(GamePanel gp, Player pl, Sound sd, TileManager tm)
    {
        this.gp = gp;
        this.pl = pl;
        this.sd = sd;
        this.tm = tm;

        this.npcM = new NPC_Manager();

        npcM.setGp(this.gp);
        npcM.setPl(this.pl);
        npcM.setSd(this.sd);
        npcM.setTm(this.tm);
        
        CreateNpcs();
    }

    public void CreateNpcs()
    {
        for(int i =0;i<numeroNpc1;i++){
            npcs.add(new Cnpc(gp, pl, sd, tm,6,"/src/NPC/Chat/",6,"/src/NPC/NPC_type1/FACE/face",3,"/src/NPC/NPC_type1/up/.",3,"/src/NPC/NPC_type1/down/.",3,"/src/NPC/NPC_type1/left/.",3,"/src/NPC/NPC_type1/right/."));
        }
        for(int i =0;i<numeroNpc2;i++){
            npcs.add(new Cnpc(gp, pl, sd, tm,6,"/src/NPC/Chat/",6,"/src/NPC/NPC_type2/FACE/face",3,"/src/NPC/NPC_type2/up/.",3,"/src/NPC/NPC_type2/down/.",3,"/src/NPC/NPC_type2/left/.",3,"/src/NPC/NPC_type2/right/."));
        }
        for(int i =0;i<numeroNpc3;i++){
            npcs.add(new Cnpc(gp, pl, sd, tm,6,"/src/NPC/Chat/",6,"/src/NPC/NPC_type3/FACE/face",3,"/src/NPC/NPC_type3/up/.",3,"/src/NPC/NPC_type3/down/.",3,"/src/NPC/NPC_type3/left/.",3,"/src/NPC/NPC_type3/right/."));
        }
    }


    public void update()
    {
        //1controllo se anche 1 npc stia parlando
        for(Cnpc n : npcs) {
        if(n.istlk)
        {
        sequalcunoparla = true;
        }
        }
        //2 se parla non si muovono
        //For-Each
        if(sequalcunoparla != true)
        {
        for(Cnpc n : npcs) {
        n.update();
        }
        }
        //seno li ricontrollo per vedere se ha smesso di parlare 
        for(Cnpc n : npcs) {
        if(!n.istlk)
        {
        sequalcunoparla = false;
        }
        }
    }

        public void draw(Graphics2D g2)
    {
        //For-Each
       for(Cnpc n : npcs) {
        n.draw(g2);
        }
    }

    
}
