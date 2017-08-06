package BeeStudios.ss13.UI;

import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Spartan 2 on 2017-08-04.
 */
public class UI_ButtonClickable {
    private int[] buttoncolour = {0,0,0};
    private int[] buttoncolour_a = {0,0,0};

    private boolean clicked = false;
    private boolean enabled = false;
    private boolean visible = false;
    private boolean useimages = true;
    private boolean useimagewidth = true;

    private int clickstate = 0;

    private String imagelocation_none       = "";
    private String imagelocation_held       = "";
    private String imagelocation_clicked    = "";
    private float[][] positionaldata = new float[2][2];//can be 2,4 later for better boxes or polygonal boxes
    // ->
    private BufferedImage[] buttonimagedata = new BufferedImage[3];
    //

    public UI_ButtonClickable(int[] buttoncolour,String[] imagelocations,float[][] posdat,boolean useimages,boolean useimagewidth){
        this.buttoncolour =             buttoncolour;
        this.imagelocation_none =       imagelocations[0];
        this.imagelocation_held =       imagelocations[1];
        this.imagelocation_clicked =    imagelocations[2];
        this.useimages =                useimages;
        this.useimagewidth =            useimagewidth;
        this.positionaldata = posdat;

        //initialise button
        this.init_button();
    }

    //
    public void init_button(){//if button is colour only, load for colour block
        if(!this.useimages){//if plain button
            for (int i=0; i < buttoncolour.length; i++){
                if (buttoncolour[i] < 10){
                    buttoncolour_a[i] = 0;
                }
                else{
                    buttoncolour_a[i] = buttoncolour[i] -10;
                }
            }
        }

        else{
            try{
                this.loadimages();
            }
            catch (Exception e){
                System.out.println("failed button creation, image not found!  |switching to blocked mode");
                print_exception(e);
                this.useimages = false;
                init_button();
            }
        }

    }

    public void XRender(){
        GL11.glColor3f(0.5f,0.5f,1.0f);

// draw quad
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(-100,100);
        GL11.glVertex2f(-100+200,100);
        GL11.glVertex2f(100+200,100+200);
        GL11.glVertex2f(100,100+200);
        GL11.glEnd();
        float[][] posdata = this.makesquarecoords(this.positionaldata);

        //glVertex3f(0.0f + x, 0.0f + y, 0.0f);
        if(this.clicked) {
            glColor3f(buttoncolour_a[0], buttoncolour_a[1], buttoncolour_a[2]);
        }
        else{
            glColor3f(buttoncolour[0],buttoncolour[1],buttoncolour[2]);
        }
        glBegin(GL_QUADS);
        for(int x=0;x<3;x++){
            glVertex2f(posdata[0][x],posdata[1][x]);
        }
        glEnd();

    }

    public void loadimages(){

    }

    public void click(){
        if((this.enabled)&&(this.visible)){

        }

    }
    public void print_exception(Exception e){
        System.out.println("|EXCEPTION|");
        System.out.println(e);
        System.out.println("|   END   |");
    }
    public float[][] makesquarecoords(float[][] posdata){
        float[][] out = new float[2][4];

        out[0][0] = posdata[0][0];//0,0 (x1,y1)
        out[1][0] = posdata[0][1];

        out[0][1] = posdata[1][0];//0,1 (x2,y1)
        out[1][1] = posdata[0][1];

        out[0][2] = posdata[0][0];//1,0 (x1,y2)
        out[1][2] = posdata[1][1];

        out[0][3] = posdata[1][0];//1,1 (x2,y2)
        out[1][3] = posdata[1][1];
        return out;

    }
    //##
    public int[] getButtoncolour() {
        return buttoncolour;
    }

    public boolean isClicked() {
        return clicked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getImagelocation_none() {
        return imagelocation_none;
    }

    public String getImagelocation_held() {
        return imagelocation_held;
    }

    public String getImagelocation_clicked() {
        return imagelocation_clicked;
    }
}
