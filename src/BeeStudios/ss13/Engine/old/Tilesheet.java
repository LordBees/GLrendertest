package BeeStudios.ss13.Engine.old;

import java.awt.image.BufferedImage;

/**
 * Created by Spartan 2 on 2017-08-03.
 */
public class Tilesheet {
    private int TileID          = -1;//could merge the 2
    private int TexID           = -1;
    private String Name_sheet   = "";
    private int[]tilesheetdimensionsxy = {-1,-1};
    //
    public Tilesheet(String Sheetname){
        //Texmanager

    }
    public Tilesheet(BufferedImage sheetdata){

    }

    public Tilesheet (int TexID,String name,int xdimension,int ydimension){
        int[] temp ={xdimension,ydimension};
        init_sheet(TexID,name,temp);

    }
    public Tilesheet (int TexID,String name,int[] dimensionxy){
        init_sheet(TexID,name,dimensionxy);

    }

    //
    public void init_sheet(int TexID,String name,int[] dimensionxy){
        setTexID(TexID);
        setName_sheet(name);
        setTilesheetdimensionsxy(dimensionxy);
    }

    //#
    public int getTexID() {
        return TexID;
    }

    public void setTexID(int texID) {
        TexID = texID;
    }

    public String getName_sheet() {
        return Name_sheet;
    }

    public void setName_sheet(String name_sheet) {
        Name_sheet = name_sheet;
    }

    public int[] getTilesheetdimensionsxy() {
        return tilesheetdimensionsxy;
    }

    public void setTilesheetdimensionsxy(int[] tilesheetdimensionsxy) {
        this.tilesheetdimensionsxy = tilesheetdimensionsxy;
    }
    public int getTileID() {
        return TileID;
    }

    public void setTileID(int tileID) {
        TileID = tileID;
    }
}
