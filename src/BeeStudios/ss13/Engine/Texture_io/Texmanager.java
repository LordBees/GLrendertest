package BeeStudios.ss13.Engine.Texture_io;

import org.lwjgl.opengl.GL11;

/**
 * Created by Spartan 2 on 2017-07-31.
 */
//unused - see SheetResourceManager
public class Texmanager {
    public int[] texids_loaded = new int [255];

    public void cleartexturebuffer(){
        for (int x = 0; x < this.texids_loaded.length; x++){
            GL11.glDeleteTextures(this.texids_loaded[x]);
            this.texids_loaded[x] = 0;
        }
    }

    public int addtexturetobuffer(int TexID){
        for (int x = 0; x < this.texids_loaded.length; x++){
            if(this.texids_loaded[x] == TexID){
                return 0;
            }
            else if (this.texids_loaded[x] == 0){
                this.texids_loaded[x] = TexID;
                //break;
                return 1;
            }
            else{
                //this.texids_loaded.
                throw new RuntimeException("no more textures to allocate");
                //return -1;
            }
        }
        return -1;
    }
    public int removefromtexturebuffer(int TexID){
        for (int x = 0; x < this.texids_loaded.length; x++) {
            if (this.texids_loaded[x] == TexID) {
                this.texids_loaded[x] = 0;
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }
}
