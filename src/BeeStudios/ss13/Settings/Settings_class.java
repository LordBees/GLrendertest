package BeeStudios.ss13.Settings;

/**
 * Created by Spartan 2 on 2017-07-31.
 */
///engine configs
public class Settings_class {
    public boolean Fullscreen = false;

    public int Disp_xres = 1480;// allows for 40tiles//640;//800;//300;//width
    public int Disp_yres = 1480;//600;//300;//height
    public int Disp_ratiox;
    public int Disp_ratioy;
    public int Disp_tilesondisplayratiox;
    public int Disp_tilesondisplayratioy;

    public int Expect_Tile_xsizeof = 32;
    public int Expect_Tile_ysizeof = 32;

    public String WinTitle = "hello world!";
    //                                 red    blue   green  alpha
    public float[] Disp_clearcolour = {1.0f,  1.0f,  0.0f,  0.0f};

    public int TARG_FPS = 60;

    public boolean ORTHOMAIN = true;

    ////
    public Settings_class(){//this is for initialising engine config variables
        //setting up ratio
        this.Disp_tilesondisplayratiox = this.Disp_xres/this.Expect_Tile_xsizeof;
        this.Disp_tilesondisplayratioy = this.Disp_yres/this.Expect_Tile_ysizeof;
    }
}
