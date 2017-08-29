package BeeStudios.ss13.Engine.DrawingStuff;

/**
 * Created by Spartan 2 on 2017-08-28.
 */
//texture object for texture data
public class TextureObject {
    //
    private String texlocpath;      //path to texture
    private String texname;
    private int texid = -1;              //texture id in gpu
    private int[] pixelXYsize;      //size in pixels of image
    private boolean istiled;        // is a tilesheet
    private int[] tileXYpixels;     //tile dimensions in pixels (ex [32,32] for a 32 pixel by 32 pixel tile)
    private int[] tileXYtilenumber; //number of tiles in sheet (ex [12,24] is 12 tiles long and 24 tiles deep)
    private boolean isuploaded = false;
    private boolean injar;

    //
    public TextureObject(int te){
        initialiseclass();
        preparetexture();

    }
    //
    public void draw(float x,float y){

    }
    public void draw(float x,float y,int[]tileXYpos){

    }
    public void preparetexture(){


    }
    //private int uploadtoGPU(){
    //    return 0;
    //
    //}
    public void uploadtoGPU(int TextureID){//changes state for uploading to gpu //was boolean not void
        texid = TextureID;
        isuploaded = true;

    }
    private void initialiseclass(){

    }

    public int gettexid(){
        return texid;
    }
    public boolean Isuploaded(){
        return isuploaded;
    }

    public boolean isInjar() {
        return injar;
    }
    public String getTexturePath(){
        return texlocpath + "\\" + texname;
    }
}
