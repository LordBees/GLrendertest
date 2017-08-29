package BeeStudios.ss13.Engine.DrawingStuff;

import BeeStudios.ss13.Engine.Tiles.Sheetdata;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Spartan 2 on 2017-08-28.
 */

//for actually drawing to the screen
public class DrawUtils {
    //

    //

    //
    public void Drawtex(float x,float y,int TextureID){


    }
    public void Drawtex(float x,float y,int TextureID,int[] tileXYcoords){

    }



    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    private void DrawQuad_Textured(float[] xy,float[] xytex,int TextureID){
        glBindTexture(GL_TEXTURE_2D, TextureID);
        // Draw a textured quad
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); glVertex3f(0+xy[0], 0+xy[1], 0);
        glTexCoord2f(0, 1); glVertex3f(0+xy[0], xytex[1]+xy[1], 0);
        glTexCoord2f(1, 1); glVertex3f(xytex[0]+xy[0], xytex[1]+xy[1], 0);
        glTexCoord2f(1, 0); glVertex3f(xytex[0]+xy[0], +xy[1], 0);
        glEnd();


    }

    private void DrawQuad_textured_FromTilesheet(Sheetdata sheetx, float tilex, float tiley, float x, float y) {//ortho test
        //
        int TextureID = sheetx.TextureID;
        float[] SdimXY = {sheetx.texturewidth, sheetx.textureheight};
        float[] TdimXY = {sheetx.tilewidth, sheetx.tileheight};

        //int texoff;
        //int[] x1y1 = new int[2];
        //int[] x1y2 = new int[2];
        //int[] x2y1 = new int[2];
        //int[] x2y2 = new int[2];
        float[] x1y1 = new float[2];
        float[] x1y2 = new float[2];
        float[] x2y1 = new float[2];
        float[] x2y2 = new float[2];

        x1y1[0] = x;//(TdimXY[0])+x;
        x1y1[1] = y;//(TdimXY[1])+y;

        x1y2[0] = x;
        x1y2[1] = (TdimXY[1]) + y;

        x2y1[0] = (TdimXY[0]) + x;
        x2y1[1] = y;

        x2y2[0] = (TdimXY[0]) + x;
        x2y2[1] = (TdimXY[1]) + y;

        /*
        x1y1[0] = (TdimXY[0]+(tilex))+x;
        x1y1[1] = (TdimXY[1]+(tiley))+y;

        x1y2[0] = (TdimXY[0]+(tilex))+x;
        x1y2[1] = (TdimXY[1]+(tiley+TdimXY[1]))+y;

        x2y1[0] = (TdimXY[0]+(tilex+TdimXY[0]))+x;
        x2y1[1] = (TdimXY[1]+(tiley))+y;

        x2y2[0] = (TdimXY[0]+(tilex+TdimXY[0]))+x;
        x2y2[1] = (TdimXY[1]+(tiley+TdimXY[1]))+y;
         */

        //defines
        float TexScaleUpper_S;
        float TexScaleUpper_T;
        float TexScaleLower_S;
        float TexScaleLower_T;

        /*
         //tex upper bounds
        TexScaleUpper_S = ((tilex * TdimXY[0]) / SdimXY[0]);//+offsetratioxy[0];//; flipped division
        TexScaleUpper_T = ((tiley * TdimXY[1]) / SdimXY[1]);//+offsetratioxy[1];//;

        //tex lower bounds
        TexScaleLower_S = (((tilex - 1) * TdimXY[0]) / SdimXY[0]);//+offsetratioxy[0];//flipped division
        TexScaleLower_T = (((tiley - 1) * TdimXY[1]) / SdimXY[1]);//+offsetratioxy[1];
         */

        //tactical flip
        //tex upper bounds
        TexScaleLower_S = ((tilex * TdimXY[0]) / SdimXY[0]);//+offsetratioxy[0];//; flipped division
        TexScaleLower_T = ((tiley * TdimXY[1]) / SdimXY[1]);//+offsetratioxy[1];//;

        //tex lower bounds
        TexScaleUpper_S = (((tilex - 1) * TdimXY[0]) / SdimXY[0]);//+offsetratioxy[0];//flipped division
        TexScaleUpper_T = (((tiley - 1) * TdimXY[1]) / SdimXY[1]);//+offsetratioxy[1];


        //
        glBindTexture(GL_TEXTURE_2D, TextureID);
        glBegin(GL_QUADS);

        glTexCoord2f(TexScaleLower_S, TexScaleLower_T);
        //glTexCoord2f(x2y2[0],x2y2[1]);//1,1
        glVertex3f(x2y2[0], x2y2[1], 0);

        glTexCoord2f(TexScaleUpper_S, TexScaleLower_T);
        //glTexCoord2f(x1y2[0],x1y2[1]);//0,1
        glVertex3f(x1y2[0], x1y2[1], 0);

        glTexCoord2f(TexScaleUpper_S, TexScaleUpper_T);
        //glTexCoord2f(x1y1[0],x1y1[1]);//0,0
        glVertex3f(x1y1[0], x1y1[1], 0);

        glTexCoord2f(TexScaleLower_S, TexScaleUpper_T);
        //glTexCoord2f(x2y1[0],x2y1[1]);//1,0
        glVertex3f(x2y1[0], x2y1[1], 0);


        glEnd();
    }
}
