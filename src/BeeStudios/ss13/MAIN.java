package BeeStudios.ss13;

import BeeStudios.ss13.Tiles.TileManager;

/**
 * Created by Spartan 2 on 2017-07-31.
 */
public class MAIN {
    public static void main(String[] args) {
        TileManager tx = new TileManager();
        tx.loadtile("");
        //return 1010;
        Game_Main gamex = new Game_Main();
        gamex.run(0);

    }
}
