package BeeStudios.ss13.Engine.DrawingStuff;

import org.lwjgl.opengl.GL11;

/**
 * Created by Spartan 2 on 2017-08-28.
 */
//manages loaded textures and cleanup of said textures
public class TextureIDManager {
    //
    public int[] texids_loaded;
    private int freeslots;
    private int TexturesMAX;
    //
    public TextureIDManager(int TexturesMAX){
       texids_loaded = new int [TexturesMAX];
       freeslots = TexturesMAX;
       this.TexturesMAX = TexturesMAX;
       initarray();
    }
    //
    public void cleanuptextures(){
        for (int i=0;i<texids_loaded.length;i++){
            DelTexture(texids_loaded[i]);
        }
    }

    public boolean DelTexture(int TextureID){//quick deletion
        GL11.glDeleteTextures(TextureID);
        freeslots ++;
        return true;//should always return true
    }

    public boolean DelTexture_Safe(int TextureID){//safe deletion
        if (CheckTextureLoaded(TextureID)){
            return DelTexture(TextureID);
        }
        return false;
    }

    public boolean AddTexture(int TextureID){
        if (freeslots != 0){
            //int freeindex = findfree();
            texids_loaded[findfree()] = TextureID;
            freeslots --;
            return true;
        }
        //if (findfree() != =1){//(!CheckTextureLoaded(TextureID)){}
        return false;
    }

    public boolean CheckTextureLoaded(int TextureID){
        for (int i=0;i<texids_loaded.length;i++){
            if (texids_loaded[i] == TextureID){
                return true;
            }
        }
        return false;
    }

    public int FindTexturePos(int TextureID){//iterates and finds position in array,returns index
        for (int i=0;i<texids_loaded.length;i++){
            if (texids_loaded[i] == TextureID){
                return i;
            }
        }
        return -1;
    }

    private void initarray(){
        for (int i=0;i<texids_loaded.length;i++){
            texids_loaded[i] = -1;
        }
    }



    private int findfree(){//iterates and returns index for next free slot
        if (freeslots == 0){
            return -1;
        }
        for (int i=0;i<texids_loaded.length;i++){
            if (texids_loaded[i] == -1){
                return i;
            }
        }
        return -1;//can comment out probably
    }
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
}
