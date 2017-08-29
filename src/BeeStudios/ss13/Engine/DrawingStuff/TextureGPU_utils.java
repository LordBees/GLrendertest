package BeeStudios.ss13.Engine.DrawingStuff;

//import org.lwjgl.BufferUtils;
//import org.lwjgl.opengl.GL12;

//import java.awt.image.BufferedImage;
//import java.nio.ByteBuffer;

//import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.GL11.GL_RGBA;
//import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;

//??;;\\

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import BeeStudios.ss13.Settings.Settings_class;
import BeeStudios.ss13.Engine.Tiles.Sheetdata;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.*;
/**
 * Created by Spartan 2 on 2017-08-28.
 */
//for manipulating and Uploading to gpu
public class TextureGPU_utils {
    //
    private static final int BYTES_PER_PIXEL = 4;//3 for RGB, 4 for RGBA
    //



    //
    public int uploadtoGPU(BufferedImage teximage){//uploads texture to gpu and returns texture id
        return processGPUStuff(teximage,glGenTextures());


    }
    public int uploadtoGPU(BufferedImage teximage,int textureID){//uploads texture to gpu with id specified then returns texture id
        return processGPUStuff(teximage,textureID);

    }

    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//
    //\\//\\//\\//\\//

    private static int processGPUStuff(BufferedImage image,int textureID){

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

        //int textureID = glGenTextures(); //Generate texture ID
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
}
