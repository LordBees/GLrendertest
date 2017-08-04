package BeeStudios.ss13;

/**
 * Created by Spartan 2 on 2017-07-31.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthEditorPaneUI;

import BeeStudios.ss13.Settings.Settings_class;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader {
    private static final int BYTES_PER_PIXEL = 4;//3 for RGB, 4 for RGBA

    public static int loadTexture(BufferedImage image) {

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }

        buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

        // You now have a ByteBuffer filled with the color data of each pixel.
        // Now just create a texture ID and bind it. Then you can load it using
        // whatever OpenGL method you want, for example:

        int textureID = glGenTextures(); //Generate texture ID
        glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID
        //added this here
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);//gl order

        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        //added this here
        //set linear gl
        //glTexParameteri(GL_TEXTURE_2D,GL);//done above as filtering

        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        //Return the texture ID so we can bind it later again
        return textureID;
    }

    public static BufferedImage loadImage(String loc) {
        try {
            //return ImageIO.read(MainClass.class.getResource(loc));
            //ImageIO.read(BeeStudios.ss13.getResource(loc));
            String current = new java.io.File(".").getCanonicalPath();
            System.out.println(current + "/Res/" + loc);
            //System.out.println(ImageIO.read(new File(current + "\\Res\\" + loc)));
            return ImageIO.read(new File(current + "/Res/" + loc));
        } catch (IOException e) {
            //Error Handling Here
            System.out.println("error! image not loaded!");
            System.out.println("/Res/" + loc);
        }
        //System.out.println("xnull");
        return null;
    }
    public BufferedImage loadImage2(String loc) {
        System.out.println("L2");
        try {
            //String current = new java.io.File(".").getCanonicalPath();
            System.out.println(this.getClass().getResource(loc));
            System.out.println(loc);
            //System.out.println(getClass().getResourceAsStream("BeeStudios/ss13/Res/"+loc));
            BufferedImage image =ImageIO.read(getClass().getResourceAsStream(loc));
            return image;
        } catch (IOException e) {
            //Error Handling Here
            System.out.println("error! image not loaded!");
            System.out.println("ERROR: "+loc);
        }
        //System.out.println("xnull");
        return null;
    }
    /*
     public static BufferedImage loadImage(String loc) {
        try {
            //return ImageIO.read(MainClass.class.getResource(loc));
            //ImageIO.read(BeeStudios.ss13.getResource(loc));
            String current = new java.io.File(".").getCanonicalPath();
            System.out.println(current + "\\Res\\" + loc);
            //System.out.println(ImageIO.read(new File(current + "\\Res\\" + loc)));
            return ImageIO.read(new File(current + "\\Res\\" + loc));
        } catch (IOException e) {
            //Error Handling Here
            System.out.println("error! image not loaded!");
            System.out.println("\\Res\\" + loc);
        }
        //System.out.println("xnull");
        return null;
    }
     */
    //public static int BindResourceTex(String XtexPath) {//### gets resource then binds it and returns id
    //    return (loadTexture(loadImage(XtexPath)));//quick load
    //}
    public int BindResourceTex(String XtexPath) {//### gets resource then binds it and returns id
        return (loadTexture(loadImage2(XtexPath)));//quick load
    }

    //shouldent be here but is for time being as easy to call
    public static void drawtex_QUAD(int[] texpair, int[] texcoords, int TextureID, float x, float y) {
        //tex is
        glBindTexture(GL_TEXTURE_2D, TextureID);


        glBegin(GL_QUADS);

        glTexCoord2f(0.0f, 0.0f);
        glVertex3f(0.0f + x, 0.0f + y, 0.0f);

        glTexCoord2f(1.0f, 0.0f);
        glVertex3f(1.0f + x, 0.0f + y, 0.0f);

        glTexCoord2f(1.0f, 1.0f);
        glVertex3f(1.0f + x, 1.0f + y, 0.0f);

        glTexCoord2f(0.0f, 1.0f);
        glVertex3f(0.0f + x, 1.0f + y, 0.0f);//##changed form y

        glEnd();

    }

    public static void drawtex_QUAD_args(int[] texpair, int[] texcoords, int TextureID, float x, float y, float x_iscale, float y_iscale) {//,float imagexpercent,float imeageypercent
        // temp maths
        //float x_iscale;
        //float y_iscale;
        //tex is
        glBindTexture(GL_TEXTURE_2D, TextureID);


        glBegin(GL_QUADS);

        glTexCoord2f(0.0f, 0.0f);
        glVertex3f(0.0f + x, 0.0f + y, 0.0f);

        glTexCoord2f(x_iscale, 0.0f);
        glVertex3f(1.0f + x, 0.0f + y, 0.0f);

        glTexCoord2f(x_iscale, y_iscale);
        glVertex3f(1.0f + x, 1.0f + y, 0.0f);

        glTexCoord2f(0.0f, y_iscale);
        glVertex3f(0.0f + x, 1.0f + y, 0.0f);//##changed form y

        glEnd();

    }

    //public static void drawtex_QUAD_args2(int TextureID,float texsizex,float texsizey,float tilenox,float tilenoy,float tilewidth){//(int[]texpair,int[]texcoords,int TextureID,float x, float y,float x_iscale,float y_iscale){//,float imagexpercent,float imeageypercent
    public static void drawtex_QUAD_args2(int TextureID, float[] texsize, float tileno[], float tilewidth, float[] texscalexy, boolean truescaleraw, Settings_class Xsettings, float[] renderxyz, boolean calcxyratios) {
        // temp maths
        //float x_iscale;
        //float y_iscale;
        //tex is
        //sheetsize


        //float texscale_s = texsize[0] / (tileno[0]*tilewidth);
        //float texscale_t = texsize[1] / (tileno[1]*tilewidth);

        //minus texture width
        //float texscale_s_mtw = texsizex/((tilenox*tilewidth)-tilewidth);
        //float texscale_t_mtw = texsizey/((tilenoy*tilewidth)-tilewidth);

        //float texscale_s_mtw = 0.0f;
        //float texscale_t_mtw = 0.0f;

        //float Vscalex;
        //float Vscaley;

        //defs
        //float
        //if(!truescaleraw) {
        //    Vscalex = texscalexy[0];
        //    Vscaley = texscalexy[1];
        //    //Vscalex=0.5f;
        //    //Vscaley=0.5f;
        //}
        //else {
        //    Vscalex = tilewidth/texsize[0];
        //    Vscaley = tilewidth/texsize[1];
        //}

        //Vscalex = (texsize[0] / (tileno[0]*tilewidth-1))- (texsize[0]/tilewidth);
        //Vscaley = (texsize[1] / (tileno[1]*tilewidth-1))- (texsize[1]/tilewidth);//here figure out scaling xpos of data is also start calc inverse of data to work out other point on xy for other opint


        //defines
        float TexScaleUpper_S;
        float TexScaleUpper_T;
        float TexScaleLower_S;
        float TexScaleLower_T;

        float TexScale_X1;
        float TexScale_Y1;
        float TexScale_X2;
        float TexScale_Y2;

        //cfg
        //tilewidth = tilewidth+1;
        float[] offsetratioxy = {0,0};
        if (calcxyratios) {
            offsetratioxy[0] = (renderxyz[0] / Xsettings.Disp_xres);
            offsetratioxy[1] = (renderxyz[1] / Xsettings.Disp_yres);
        }
        else {
            offsetratioxy[0] = renderxyz[0];
            offsetratioxy[1]= renderxyz[1];
        }
        //tex upper bounds
        TexScaleUpper_S = ((tileno[0] * tilewidth) / texsize[0]);//+offsetratioxy[0];//; flipped division
        TexScaleUpper_T = ((tileno[1] * tilewidth) / texsize[1]);//+offsetratioxy[1];//;

        //tex lower bounds
        TexScaleLower_S = (((tileno[0] - 1) * tilewidth) / texsize[0]);//+offsetratioxy[0];//flipped division
        TexScaleLower_T = (((tileno[1] - 1) * tilewidth) / texsize[1]);//+offsetratioxy[1];

        //TexScaleLower_S=0;
        //TexScaleLower_T=0;

        //texture sizing
        //texture scaling
        //x,y


        //x,y 1 upper
        if (truescaleraw) {
            TexScale_X1 = (tilewidth / texsize[0])+offsetratioxy[0];//+(renderxyz[0]/Xsettings.Disp_xres);
            TexScale_Y1 = (tilewidth / texsize[1])+offsetratioxy[1];//+(renderxyz[1]/Xsettings.Disp_yres);
            //Vscalex=0.5f;
            //Vscaley=0.5f;
        } else {
            //TexScale_X1 = texscalexy[0];
            //TexScale_Y1 = texscalexy[1];

            TexScale_X1 = ((tilewidth / texsize[0])*texscalexy[0])+offsetratioxy[0];//+(renderxyz[0]/Xsettings.Disp_xres);
            TexScale_Y1 = ((tilewidth / texsize[1])*texscalexy[1])+offsetratioxy[1];//+(renderxyz[1]/Xsettings.Disp_yres);
        }
        //x,y 2lower
        if (truescaleraw) {
            TexScale_X2 = 0+offsetratioxy[0];//tilewidth / texsize[0];
            TexScale_Y2 = 0+offsetratioxy[1];//tilewidth / texsize[1];
            //Vscalex=0.5f;
            //Vscaley=0.5f;
        } else {
            TexScale_X2 = 0+offsetratioxy[0];//texscalexy[0];
            TexScale_Y2 = 0+offsetratioxy[1];//texscalexy[1];
        }

        //testing


        glBindTexture(GL_TEXTURE_2D, TextureID);


        glBegin(GL_QUADS);

        glTexCoord2f(TexScaleLower_S, TexScaleLower_T);
        glVertex3f(TexScale_X2, TexScale_Y2, 0.0f);

        glTexCoord2f(TexScaleUpper_S, TexScaleLower_T);
        glVertex3f(TexScale_X1, TexScale_Y2, 0.0f);

        glTexCoord2f(TexScaleUpper_S, TexScaleUpper_T);
        glVertex3f(TexScale_X1, TexScale_Y1, 0.0f);

        glTexCoord2f(TexScaleLower_S, TexScaleUpper_T);
        glVertex3f(TexScale_X2, TexScale_Y1, 0.0f);//##changed form y

        glEnd();
    }


    /*
    public static void drawtex_QUAD_args2(int TextureID,float[] texsize,float tileno[],float tilewidth,float[] texscalexy,boolean truescaleraw){
        // temp maths
        //float x_iscale;
        //float y_iscale;
        //tex is
        //sheetsize



        float texscale_s = texsize[0] / (tileno[0]*tilewidth);
        float texscale_t = texsize[1] / (tileno[1]*tilewidth);

        //minus texture width
        //float texscale_s_mtw = texsizex/((tilenox*tilewidth)-tilewidth);
        //float texscale_t_mtw = texsizey/((tilenoy*tilewidth)-tilewidth);

        float texscale_s_mtw = 0.0f;
        float texscale_t_mtw = 0.0f;

        float Vscalex;
        float Vscaley;
        if(!truescaleraw) {
            Vscalex = texscalexy[0];
            Vscaley = texscalexy[1];
            //Vscalex=0.5f;
            //Vscaley=0.5f;
        }
        else {
            Vscalex = tilewidth/texsize[0];
            Vscaley = tilewidth/texsize[1];
        }

        //Vscalex = (texsize[0] / (tileno[0]*tilewidth-1))- (texsize[0]/tilewidth);
        //Vscaley = (texsize[1] / (tileno[1]*tilewidth-1))- (texsize[1]/tilewidth);//here figure out scaling xpos of data is also start calc inverse of data to work out other point on xy for other opint


        //tex upper bounds
        float TexScaleUpper_S = texsize[0] / (tileno[0]*tilewidth);//;
        float TexScaleUpper_T = texsize[1] / (tileno[1]*tilewidth);//;
         TexScaleUpper_S = (tileno[0]*tilewidth)/texsize[0] ;//;
         TexScaleUpper_T = (tileno[1]*tilewidth)/ texsize[1];//;

        //tex lower bounds
        float TexScaleLower_S = texsize[0] / ((tileno[0]-1)*tilewidth);
        float TexScaleLower_T = texsize[1] / ((tileno[1]-1)*tilewidth);
         TexScaleLower_S =((tileno[0]-1)*tilewidth)/texsize[0];
         TexScaleLower_T =((tileno[1]-1)*tilewidth)/texsize[1];

        //TexScaleLower_S=0;
        //TexScaleLower_T=0;

        //texture sizing
        //texture scaling
        //x,y


        //x,y 1 upper
        float TexScale_X1;
        float TexScale_Y1;
        //x,y 2lower
        float TexScale_X2;
        float TexScale_Y2;

        //testing


        glBindTexture(GL_TEXTURE_2D,TextureID);


        glBegin (GL_QUADS);

        glTexCoord2f (TexScaleLower_S, TexScaleLower_T);
        glVertex3f (TexScale_X2, TexScale_Y2, 0.0f);

        glTexCoord2f (TexScaleUpper_S, TexScaleLower_T);
        glVertex3f (TexScale_X1, TexScale_Y2, 0.0f);

        glTexCoord2f (TexScaleUpper_S, TexScaleUpper_T);
        glVertex3f (TexScale_X1, TexScale_Y1, 0.0f);

        glTexCoord2f (TexScaleLower_S, TexScaleUpper_T);
        glVertex3f (TexScale_X2, TexScale_Y1, 0.0f);//##changed form y

        glEnd ();
        /*
        glBindTexture(GL_TEXTURE_2D,TextureID);


        glBegin (GL_QUADS);

        glTexCoord2f (texscale_s_mtw, texscale_t_mtw);
        glVertex3f (0.0f, 0.0f, 0.0f);

        glTexCoord2f (texscale_s, texscale_t_mtw);
        glVertex3f (Vscalex, 0.0f, 0.0f);

        glTexCoord2f (texscale_s, texscale_t);
        glVertex3f (Vscalex, Vscaley, 0.0f);

        glTexCoord2f (texscale_s_mtw, texscale_t);
        glVertex3f (0.0f, Vscaley, 0.0f);//##changed form y

        glEnd ();
         */

//}


    public float gettilefloat(float tilesheet){
    return 0.0f;}
    /*
     public static void drawtex_QUAD(int[]texpair,int[]texcoords,int TextureID,int x,int y){
        //tex is
        glBindTexture(GL_TEXTURE_2D,TextureID);


        glBegin (GL_QUADS);

        glTexCoord2f (0.0f, 0.0f);
        glVertex3f (0.0f, 0.0f, 0.0f);

        glTexCoord2f (1.0f, 0.0f);
        glVertex3f (1.0f, 0.0f, 0.0f);

        glTexCoord2f (1.0f, 1.0f);
        glVertex3f (1.0f, 1.0f, 0.0f);

        glTexCoord2f (0.0f, 1.0f);
        glVertex3f (0.0f, 1.0f, 0.0f);

        glEnd ();

    }
     */
}
//ImageIO.read(getClass().getResource("/path/to/resource"));
//ImageIO.read(new File("path/to/resource");
