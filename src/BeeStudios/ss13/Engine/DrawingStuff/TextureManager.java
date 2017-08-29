package BeeStudios.ss13.Engine.DrawingStuff;

import BeeStudios.ss13.Engine.IO.File_IO_EXT;
import BeeStudios.ss13.Engine.IO.File_IO_JAR;

import java.awt.image.BufferedImage;

/**
 * Created by Spartan 2 on 2017-08-29.
 */
//for managing the adding ,uploading and drawing of items
public class TextureManager {
    //
    TextureGPU_utils xGPU = new TextureGPU_utils();
    File_IO_JAR Jload = new File_IO_JAR();
    File_IO_EXT Eload = new File_IO_EXT();

    //|
    int DEFAULT_MAX_TEXTURES = 255;
    //|

    TextureObject[] XTextures;  //textures in data
    TextureIDManager XTextureID_DB;//textures in gpu memory by id

    //
    public TextureManager(){
        //
        initsystem(DEFAULT_MAX_TEXTURES);
    }

    public TextureManager(int texlimit){
        initsystem(texlimit);
    }

    //

    //public void addtexture(BufferedImage tex2add){}
    public void addtexture(TextureObject Xtexture){
        if (XTextureID_DB.CheckTextureLoaded(Xtexture.gettexid())){

        }
    }

    public void addtexture(int texlimit){
        initsystem(texlimit);
    }

    public void deletetexture(){//delete tex by id in loaded,textureobject,idpos,name etc...

    }
    public void drawtexture(float x,float y,int TextureID){

    }
    public void drawtexture(){

    }
    public void addtoGPU_byposinarray(int ArrayPos){//Xtexture.gettexid()
        //GPU_ADD_2mem(XTextures[ArrayPos]);
        GPU_ADD_2mem(ArrayPos);
    }

    public void removefromGPU(){

    }
    private void GPU_ADD_2mem(int Xtex_pos){//TextureObject uploader
        TextureObject temptex = XTextures[Xtex_pos];//local copy for quicker access


        if (!temptex.Isuploaded()){
            //load the image form source
            BufferedImage ximage;
            if (temptex.isInjar()){
                ximage = Jload.loadimage(temptex.getTexturePath());
            }
            else {
                ximage = Eload.loadImage(temptex.getTexturePath());
            }

            //upload to gpu
            int temp_TextureID = xGPU.uploadtoGPU(ximage);
            //update loaded ids table
            XTextureID_DB.AddTexture(temp_TextureID);

            //update texture object
            temptex.uploadtoGPU(temp_TextureID);
            //assign updated texture info
            XTextures[Xtex_pos] = temptex;

            //end

        }

        //XTextureID_DB.AddTexture(XTextures[Xtex_pos].)
    }

    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    private void initsystem(int maxtextures){
        XTextureID_DB = new TextureIDManager(maxtextures);
        XTextures = new TextureObject[maxtextures];
    }
}
