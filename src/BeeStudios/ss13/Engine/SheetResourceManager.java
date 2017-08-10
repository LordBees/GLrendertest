package BeeStudios.ss13.Engine;

import BeeStudios.ss13.Engine.Texture_io.TextureLoader;
import BeeStudios.ss13.Engine.Tiles.Sheetdata;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Spartan 2 on 2017-08-10.
 */
public class SheetResourceManager {
    // master datasheet loader
    //takes bound textures atm
    //deletes them when removed
    //public int[] texturesheets_loaded = new int [255];
    private Sheetdata[] texturesheets_loaded;// = new int [255];
    private TextureLoader lda = new TextureLoader();
    //Sheetdata


    //
    public SheetResourceManager(int sheets){
        //Sheetdata
        this.texturesheets_loaded = new Sheetdata[sheets];//allocate memory then populate with blank structs
        for (int x = 0; x < this.texturesheets_loaded.length; x++){
            //this.texturesheets_loaded[x] = new Sheetdata(-1,"BLANK_UNUSED",0,0,0,0);
            this.addblank(x);
        }
    }
    //

    public void cleartexturebuffer(){
        for (int x = 0; x < this.texturesheets_loaded.length; x++){
            GL11.glDeleteTextures(this.texturesheets_loaded[x].TextureID);
            //this.texturesheets_loaded[x] = new Sheetdata(-1,"BLANK_UNUSED",0,0,0,0);
        }
    }

    public int addtexturetobuffer(Sheetdata xSheet){
        for (int x = 0; x < this.texturesheets_loaded.length; x++){
            if(this.texturesheets_loaded[x].TextureID == xSheet.TextureID){
                return 0;//already added
            }
            else if (this.texturesheets_loaded[x].TextureID == -1){
                this.texturesheets_loaded[x] = xSheet;
                //break;
                return 1;//successfull add
            }
            else{
                //this.texturesheets_loaded.
                throw new RuntimeException("no more textures to allocate");
                //return -1;
            }
        }
        return -1;
    }
    public int removefromtexturebuffer(int TexID){
        for (int x = 0; x < this.texturesheets_loaded.length; x++) {
            if (this.texturesheets_loaded[x].TextureID == TexID) {
                //this.texturesheets_loaded[x] = 0;
                this.addblank(x);
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }
    public int removefromtexturebuffer(Sheetdata xTexture){
        for (int x = 0; x < this.texturesheets_loaded.length; x++) {
            if (this.texturesheets_loaded[x].TextureID == xTexture.TextureID) {//check for texture name and id, also check for everything else(later)
                if (this.texturesheets_loaded[x].texturename == xTexture.texturename) {
                //this.texturesheets_loaded[x] = 0;
                this.addblank(x);
                return 1;
            }} else {
                return 0;
            }
        }
        return -1;
    }
    public int removefromtexturebuffer(String  TexName){//was going to do name+id but id=primary
        for (int x = 0; x < this.texturesheets_loaded.length; x++) {
            if (this.texturesheets_loaded[x].texturename == TexName) {
                //this.texturesheets_loaded[x] = 0;
                this.addblank(x);
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }

    private void addblank(int xpos){
        this.texturesheets_loaded[xpos] = new Sheetdata(-1,"BLANK_UNUSED",0,0,0,0);
    }

    public void addtexturebyfilename(String xjpath,int th,int tw,String name){//temp name until can xtract from fn
        // name resolution
        if (name == ""){
            //create name form filename path -extension
        }
        BufferedImage idat = lda.loadImage2(xjpath);//load image +get idat then process into texture sheet
        int itexID = lda.loadTexture(idat);
        this.addtexturetobuffer(new Sheetdata(itexID,name,idat.getWidth(),idat.getHeight(),th,tw));
        //x =new Sheetdata();
    }
}
