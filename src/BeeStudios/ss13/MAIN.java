package BeeStudios.ss13;

import BeeStudios.ss13.Engine.Menus.LauncherMenuDBG;
import BeeStudios.ss13.GAME_MAIN.Game_Main;

/**
 * Created by Spartan 2 on 2017-07-31.
 */
public class MAIN {
    public static void main(String[] args) {
        //TileManager tx = new TileManager();
        //tx.loadtile("");
        //return 1010;
        boolean insetup = true;//false;//true;

        if (insetup){
            //SetupMenu launchmenu = new SetupMenu();
            LauncherMenuDBG launcherMenuDBG = new LauncherMenuDBG();
            launcherMenuDBG.showEventDemo();
        }
        else {
            Game_Main gamex = new Game_Main();
            gamex.run(0);
        }

    }
}
