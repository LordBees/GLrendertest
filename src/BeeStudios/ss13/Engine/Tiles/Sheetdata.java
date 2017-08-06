package BeeStudios.ss13.Engine.Tiles;

/**
 * Created by Spartan 2 on 2017-08-06.
 */
public class Sheetdata {
    public int     TextureID       = 0;
    public String  texturename     = "";
    public int     texturewidth    = 0;
    public int     textureheight   = 0;
    public int     tileheight = 0;
    public int     tilewidth = 0;
    public int     numtiles_x;
    public int     numtiles_y;
    //sub tiles
    TileSub[] tilesinsheet;
    //

    public Sheetdata(int textureID, String texturename, int texturewidth, int textureheight, int tileheight, int tilewidth) {
        TextureID = textureID;
        this.texturename = texturename;
        this.texturewidth = texturewidth;
        this.textureheight = textureheight;
        this.tileheight = tileheight;
        this.tilewidth = tilewidth;
        //

        //post
        this.numtiles_x = (texturewidth/tilewidth);
        this.numtiles_y = (textureheight/tileheight);

        //
        this.tilesinsheet = new TileSub[(this.numtiles_x*this.numtiles_y)];
    }

    public void loadtilemeta(){
        int totaltiles = (this.numtiles_x*this.numtiles_y);
        int tilecounter = 0;
        String[] Ndat = getnamedata();
        //for(int i=0;i<totaltiles;i++){}
        for(int x=0;x<this.numtiles_x;x++){
            for(int y=0;y<this.numtiles_y;y++){
                //remember offset by 1
                this.tilesinsheet[tilecounter] = new TileSub(x+1,y+1);
                tilecounter++;
            }
        }

        for(int i=0;i<totaltiles;i++){
            this.tilesinsheet[i].setName(Ndat[i]);
        }
    }
    public String[] getnamedata(){
        //use texnamedata
        return new String[(this.numtiles_x*this.numtiles_y)];//temp
    }
    public int[] gettilecoordsbyname(String xname){
        int totaltiles = (this.numtiles_x*this.numtiles_y);
        int[] Dz = {0,0};

        //int[] coordsreturner = new int[2];
        for(int i=0;i<totaltiles;i++){
            if(this.tilesinsheet[i].getName() == xname){
                return this.tilesinsheet[i].GetCoords();
            }

        }
        return Dz;//return DoubleZeroes
    }

    //
}
